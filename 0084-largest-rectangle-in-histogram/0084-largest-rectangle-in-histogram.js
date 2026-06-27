/**
 * @param {number[]} heights
 * @return {number}
 */
var largestRectangleArea = function(heights) {
    const stack = [];
    let maxArea = 0;
    const n = heights.length;

    for (let i = 0; i < n; i++) {
        while (
            stack.length &&
            heights[stack[stack.length - 1]] >= heights[i]
        ) {
            const index = stack.pop();
            const pse = stack.length ? stack[stack.length - 1] : -1;
            const nse = i;

            maxArea = Math.max(
                maxArea,
                heights[index] * (nse - pse - 1)
            );
        }

        stack.push(i);
    }

    while (stack.length) {
        const index = stack.pop();
        const pse = stack.length ? stack[stack.length - 1] : -1;
        const nse = n;

        maxArea = Math.max(
            maxArea,
            heights[index] * (nse - pse - 1)
        );
    }

    return maxArea;
};