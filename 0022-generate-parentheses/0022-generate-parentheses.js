/**
 * @param {number} n
 * @return {string[]}
 */
var generateParenthesis = function(n) {
    let results = [];
    let current = "";
    generate(0, 0, current, n, results);
    return results;    
};

function generate(open, close, current, n, results) {
 // base case: if length is 2*n, we have a valid sequence
    if(current.length == 2*n) {
        results.push(current);
        return;
    }
    if (open < n) {
        generate(open + 1, close, current + "(", n, results);
    }

    // if we can add a close parenthesis
    if (close < open) {
        generate(open, close + 1, current + ")", n, results);
    }

}

/*
1. result[] array will be stored the final results list of parenthesis.
2. Initialize a current = " ", which will be store individual parenthisis combination.
3. Call  generate(0, 0, current, n, results); [open = 0, close = 0]
4. return results;  

3.1. If (current.length == 2*n) , then add current into results list and return
3.2. If (open < n) , then generate(open + 1, close, current + "(", n, results);
3.3 If (close < open), then generate(open, close + 1, current + ")", n, results);

*/