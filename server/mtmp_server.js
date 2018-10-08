module.exports = (context, cb) => {
    const angle = context.query.angle;
    const velocity = context.query.velocity;

    const xV = Math.sin(angle * (Math.PI / 180)) * velocity;
    const yV = Math.cos(angle * (Math.PI / 180)) * velocity;

    let result = [];

    let t = 0;
    let y = 0;
    let x = 0;
    for (; y >= 0; t += .1) {
        y = xV * t - 9.81 * t * t / 2;
        x = yV * t;
        result.push({x, y, t});
    }

    cb(null, result);

};