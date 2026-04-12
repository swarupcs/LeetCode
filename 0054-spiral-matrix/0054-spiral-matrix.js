/**
 * @param {number[][]} matrix
 * @return {number[]}
 */


 /*
 top -> 0
[0][0] [0][1] [0][2]
[1][0] [1][1] [1][2]
[2][0] [2][1] [2][2]

 */
var spiralOrder = function(matrix) {
    let n = matrix.length;
    let m = matrix[0].length;
    
    let top = 0;
    let left = 0;

    let bottom = n - 1;
    let right = m - 1;

    let ans = [];

    while(top <= bottom && left <= right) {
        // left to right
        for(let i = left; i <=right; i++) {
            ans.push(matrix[top][i]);
        }
        top++;

        // top to bottom
        for(let i = top; i <=bottom; i++) {
            ans.push(matrix[i][right]);
        }
        right--;


        // right to left
        // check need
        if(top <= bottom) {
        for(let i = right; i >= left; i--) {
            ans.push(matrix[bottom][i]);
        }
            bottom--;
        }
        

        // bottom to top
        // check need
        if(left <= right) {
            for(let i = bottom; i >= top; i--){
                ans.push(matrix[i][left])
            }
            left++;
        }
        
        
    }

    return ans;

    
};