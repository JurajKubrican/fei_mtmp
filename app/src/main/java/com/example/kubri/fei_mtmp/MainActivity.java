package com.example.kubri.fei_mtmp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import static com.example.kubri.fei_mtmp.Calculations.*;


public class MainActivity extends AppCompatActivity {

    public static final String PARABOLA_DATA = "com.example.myfirstapp.PARABOLA_DATA";
    public static final String PARABOLA_ANGLE = "com.example.myfirstapp.PARABOLA_ANGLE";

    private ArrayList<ParabolaPoint> parabolaData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final SeekBar angleSeek = findViewById(R.id.seekBarAngle);
        final TextView angleText = findViewById(R.id.editTextAngle);
        final SeekBar veloSeek = findViewById(R.id.seekBarVelocity);
        final TextView veloText = findViewById(R.id.editTextVelocity);

        final Preview preview = findViewById(R.id.mainPreview);


        angleSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int angle = seekBar.getProgress();
                int velocity = veloSeek.getProgress();
                angleText.setText(String.valueOf(angle));
                setParabolaData(parabola(angle, velocity));
                preview.reDrawPreview(getParabolaData());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        veloSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int velocity = seekBar.getProgress();
                int angle = angleSeek.getProgress();
                veloText.setText(String.valueOf(velocity));
                setParabolaData(parabola(angle, velocity));
                preview.reDrawPreview(getParabolaData());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }


        });


        int angle = angleSeek.getProgress();
        int velocity = veloSeek.getProgress();

        setParabolaData(parabola(angle, velocity));
        preview.reDrawPreview(getParabolaData());


        // NAV buttons
        Button ButtonAnimate = findViewById(R.id.buttonAnimate);
        ButtonAnimate.setOnClickListener(v -> switchView(AnimateActivity.class));

        Button ButtonList = findViewById(R.id.buttonList);
        ButtonList.setOnClickListener(v -> switchView(ListActivity.class));

        Button ButtonGraph = findViewById(R.id.buttonGraph);
        ButtonGraph.setOnClickListener((View v) -> switchView(GraphActivity.class));


    }


    ArrayList<ParabolaPoint> getParabolaData() {
        return parabolaData;
    }

    void setParabolaData(ArrayList<ParabolaPoint> parabolaData) {
        this.parabolaData = parabolaData;
    }

    boolean isOnline() {
        Switch s = findViewById(R.id.onlineSwitch);
        return s.isChecked();
    }


    void toggleLoading(boolean isLoading) {

        findViewById(R.id.progress_overlay).setVisibility(isLoading ? View.VISIBLE : View.GONE);
        findViewById(R.id.buttonAnimate).setEnabled(!isLoading);
        findViewById(R.id.buttonGraph).setEnabled(!isLoading);
        findViewById(R.id.buttonList).setEnabled(!isLoading);

    }

    void switchView(Class<?> cls) {
        Intent intent = new Intent(getApplicationContext(), cls);

        if (isOnline()) {
            toggleLoading(true);
            int angle = ((SeekBar) findViewById(R.id.seekBarAngle)).getProgress();
            int velocity = ((SeekBar) findViewById(R.id.seekBarVelocity)).getProgress();

            String url = "http://relay.knet.sk?url=https%3A%2F%2Fwt-kubrican_juraj-gmail_com-0.sandbox.auth0-extend.com%2Fmtmp_server%3Fangle%3D" + angle + "%26velocity%3D" + velocity;
            RequestQueue queue = Volley.newRequestQueue(this);
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
                ArrayList<ParabolaPoint> data = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject o = response.getJSONObject(i);
                        data.add(new ParabolaPoint(o.getDouble("x"), o.getDouble("y"), o.getDouble("t")));

                    } catch (JSONException e) {
                        toggleLoading(false);
                        e.printStackTrace();
                    }
                }
                intent.putExtra(PARABOLA_DATA, data);
                getApplicationContext().startActivity(intent);
                toggleLoading(false);

            }, e -> {
                toggleLoading(false);
                e.printStackTrace();
            });

            queue.add(jsonObjectRequest);


        } else {
            ArrayList<ParabolaPoint> data = getParabolaData();
            intent.putExtra(PARABOLA_DATA, data);
            getApplicationContext().startActivity(intent);
        }

    }

}
