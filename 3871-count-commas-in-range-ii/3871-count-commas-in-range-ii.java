class Solution {
    public long countCommas(long n) {
         long ans = 0;

        long[] start = {
            1_000L,                 
            1_000_000L,             
            1_000_000_000L,         
            1_000_000_000_000L,     
            1_000_000_000_000_000L  
        };

        for (long x : start) {
            if (n >= x) {
                ans += n - x + 1;
            }
        }

        return ans;
        
    }
}