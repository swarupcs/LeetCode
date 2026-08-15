/**
 * @param {number} n
 * @return {string[][]}
 */
var solveNQueens = function(n) {
    let ans = [];

    let board = Array.from({ length: n}, () => Array(n).fill('.'));

    func(0, ans, board);
    return ans;
};

function func(row, ans, board) {
    if(row === board.length) {
        ans.push(board.map(r => r.join("")));
        return;
    }

    for(let col = 0; col < board[0].length; col++) {
        if(isSafe(board, row, col)) {
            board[row][col] = 'Q';

            func(row + 1, ans, board);

            board[row][col] = "."
        }
    }
}

function isSafe(board, row, col) {
    let r = row;
    let c = col;

    while(r >= 0 && c >= 0) {
        if(board[r][c] === "Q") return false;
        r--;
        c--;
    }

    r = row;
    c = col;

    while(r >= 0) {
        if(board[r][c] === 'Q') return false;
        r--;
    }

    r = row, c = col;

    while(r >= 0 && c < board[0].length) {
        if(board[r][c] === 'Q') return false;
        r--;
        c++;
    }

    return true;
}