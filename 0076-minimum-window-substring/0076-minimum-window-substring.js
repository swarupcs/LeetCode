/**
 * @param {string} s
 * @param {string} t
 * @return {string}
 */
var minWindow = function(s, t) {
    let minLen = Infinity;

    let sIndex = -1;

    let hash = new Array(256).fill(0);

    for(let c of t) {
        hash[c.charCodeAt(0)]++;
    }

    let count = 0;
    let l = 0; 
    let r = 0;

    while(r < s.length) {
        if(hash[s.charCodeAt(r)] > 0) {
            count++;
        }

   hash[s.charCodeAt(r)]--;
                
            /* If all characters from t 
            are found in current window */
            while (count === t.length) {
                    
                /* Update minLen and sIndex
                if current window is smaller */
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    sIndex = l;
                }
                
                // Remove leftmost character from window
                hash[s.charCodeAt(l)]++;
                if (hash[s.charCodeAt(l)] > 0) {
                    count--;
                }
                l++;
            }
            r++;
        }
        
        // Return minimum length substring from s
        return (sIndex === -1) ? "" : s.substr(sIndex, minLen);
    
};