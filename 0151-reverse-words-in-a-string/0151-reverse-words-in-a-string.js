/**
 * @param {string} s
 * @return {string}
 */
var reverseWords = function(s) {
    const arr = s.split("");
    const n = arr.length;

    const reverse = (l, r) => {
        while (l < r) {
            [arr[l], arr[r]] = [arr[r], arr[l]];
            l++;
            r--;
        }
    };

    reverse(0, n - 1);

    let i = 0;
    let j = 0;

    while (j < n) {
        while (j < n && arr[j] === " ") {
            j++;
        }

        if (j === n) {
            break;
        }

        const start = i;

        while (j < n && arr[j] !== " ") {
            arr[i++] = arr[j++];
        }

        reverse(start, i - 1);

        if (j < n) {
            arr[i++] = " ";
        }
    }

    if (i > 0 && arr[i - 1] === " ") {
        i--;
    }

    return arr.slice(0, i).join("");
};