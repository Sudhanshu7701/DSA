class Solution {
    Boolean[][] dp;
    boolean solve(int i, int j, String s) {
        if (i >= j)
            return true;
        if (dp[i][j] != null)
            return dp[i][j];

        if (s.charAt(i) == s.charAt(j))
            return dp[i][j] = solve(i + 1, j - 1, s);

        return dp[i][j] = false;
    }
    public String longestPalindrome(String s) {
        int n = s.length();
        dp = new Boolean[n][n];
        int maxLength = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (solve(i, j, s)) {
                    if (j - i + 1 > maxLength) {
                        maxLength = j - i + 1;
                        start = i;
                    }
                }
            }
        }

        return s.substring(start, start + maxLength);
    }
}