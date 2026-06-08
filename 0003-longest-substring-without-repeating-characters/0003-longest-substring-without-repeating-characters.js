/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLongestSubstring = function(s) {
    let n = s.length;
        
        let HashLen = 256; 
        
        let hash = new Array(HashLen).fill(-1);

        for (let i = 0; i < HashLen; ++i) {
            hash[i] = -1;
        }

        let l = 0, r = 0, maxLen = 0;
        while (r < n) {

            if (hash[s.charCodeAt(r)] != -1) {
 
                l = Math.max(hash[s.charCodeAt(r)] + 1, l);
            }
            
            let len = r - l + 1;

            maxLen = Math.max(len, maxLen);
            
            hash[s.charCodeAt(r)] = r;

            r++;
        }
       
        return maxLen;
};