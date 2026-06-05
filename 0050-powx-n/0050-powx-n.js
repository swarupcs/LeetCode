/**
 * @param {number} x
 * @param {number} n
 * @return {number}
 */
var myPow = function(x, n) {

    let num = n;

    if (num < 0) {
        return 1.0 / power(x, -num);
    }

    return power(x, num);
};

function power(x, n) {
    if (n === 0) return 1.0;

    if (n === 1) return x;

    if (n % 2 === 0) {
        return power(x * x, n / 2);
    }
    return x * power(x, n - 1);
}