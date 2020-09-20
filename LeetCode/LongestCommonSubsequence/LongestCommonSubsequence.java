/**
 * Program to compute the Longest Common Subsequence between any two strings
 * @author Phani Kiran Thaticharla
 */
public class LongestCommonSubsequence {
    /**
     *
     * @param s1 firstString
     * @param s2 secondString
     */
    private void findLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        // Initialize the first row and first column of dp table to 0
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }
        for(int j = 0; j < n; j++) {
            dp[0][j] = 0;
        }
        // Iterate through every character in String s1 and String s2
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        printTable(dp, m, n);
        traceLCS(dp, s1, m, n);
    }

    /**
     *
     * @param dp Final DP Table
     * @param m length of First String
     * @param n length of Second String
     */
    private void printTable(int[][] dp, int m, int n) {
        for(int i = 0; i <= m; i++) {
            for(int j = 0; j <= n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Method to trace the LCS String
     * @param dp Computed DP Table
     * @param s1 Given String to find LCS
     * @param m Length of First String
     * @param n Length of Second String
     */
    public void traceLCS(int[][] dp, String s1, int m, int n) {
        StringBuilder sb = new StringBuilder();
        while(m > 0 && n >0) {
            if(dp[m][n] == dp[m - 1][n - 1] + 1) {
                sb.append(s1.charAt(m - 1));
                m--;
                n--;
            } else if (dp[m - 1][n] >= dp[m][n - 1]) {
                m--;
            } else {
                n--;
            }
        }
        System.out.println("LCS " + sb.reverse().toString());
    }

    /**
     * Driver
     * @param args
     */
    public static void main(String []args){
        String s1 = "portland";
        String s2 = "roland";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        lcs.findLCS(s1, s2);
    }
}
