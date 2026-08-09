import java.util.*;

class Solution {
    int[][] dp;
    int[] suffix;
    int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;
        suffix = new int[n];

        suffix[n - 1] = piles[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = piles[i] + suffix[i + 1];
        }

        dp = new int[n][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solve(piles, 0, 1);
    }

    private int solve(int[] piles, int i, int M) {
        if (i >= n) {
            return 0;
        }

        if (2 * M >= n - i) {
            return suffix[i];
        }

        if (dp[i][M] != -1) {
            return dp[i][M];
        }

        int best = 0;
        for (int X = 1; X <= 2 * M; X++) {

            int nextM = Math.max(M, X);

            int current = suffix[i] - solve(piles, i + X, nextM);

            best = Math.max(best, current);
        }

        return dp[i][M] = best;
    }
}