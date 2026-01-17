/**
 * @param {number[]} cardPoints
 * @param {number} k
 * @return {number}
 */
var maxScore = function(cardPoints, k) {
    let lSum = 0;
    let rSum = 0;
    let maxSum = 0;

    for(let i = 0; i < k; i++) {
        lSum += cardPoints[i];
        maxSum = lSum;
    }

    let rightIndex = cardPoints.length - 1;

    for(let i = k - 1; i >= 0; i--) {
        lSum -= cardPoints[i];
        rSum += cardPoints[rightIndex];
        rightIndex--;

        maxSum = Math.max(maxSum, lSum + rSum);
    }

    return maxSum;


};