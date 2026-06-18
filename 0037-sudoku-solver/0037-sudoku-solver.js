/**
 * @param {character[][]} board
 * @return {void} Do not return anything, modify board in-place instead.
 */
var solveSudoku = function(board) {
    const isValid = (row, col, digit) => {
        for (let i = 0; i < 9; i++) {
            if (board[row][i] === digit) return false;
            if (board[i][col] === digit) return false;

            const boxRow = 3 * Math.floor(row / 3) + Math.floor(i / 3);
            const boxCol = 3 * Math.floor(col / 3) + (i % 3);

            if (board[boxRow][boxCol] === digit) return false;
        }

        return true;
    };

    const solve = () => {
        for (let row = 0; row < 9; row++) {
            for (let col = 0; col < 9; col++) {
                if (board[row][col] === '.') {
                    for (let digit = 1; digit <= 9; digit++) {
                        const ch = digit.toString();

                        if (isValid(row, col, ch)) {
                            board[row][col] = ch;

                            if (solve()) {
                                return true;
                            }

                            board[row][col] = '.';
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    };

    solve();
};