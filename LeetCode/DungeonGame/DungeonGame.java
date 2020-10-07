/**
 * Program to compute the Minimum Health Required by Knight to save the Queen
 * @author Phani Kiran Thaticharla
 */
class DungeonGame {

    /**
     * Function that prints the calculated DP Table
     * @param dp
     */
    private void printDP(int[][] dp) {
        System.out.println("Printing the calculated DP Table");
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Function to calculate MHR by Knight by using Bottom Up DP
     * @param dungeon
     * @return Min Health Required by Knight to save the Queen
     */
    public int calculateMinimumHealth(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m + 1][n + 1];

        // Fill the last row with Max Value
        for(int i = 0; i <= m; i++) {
            dp[i][m] = Integer.MAX_VALUE;
        }

        // Fill the last column with Max Value
        for(int j = 0; j <= n; j++) {
            dp[m][j] = Integer.MAX_VALUE;
        }

        /* The Minimum Health Required in last cell depends on the dungeon value at the cell.
           If the dungeon value is greater than zero, the minimum value required is just 1 to enter this cell.
           If the dungeon value is negative or zero, the minimum value required to enter the cell is absolute value at the cell + 1.
        * */
        dp[m - 1][n - 1] = dungeon[m - 1][n - 1] > 0 ? 1 : Math.abs(dungeon[m - 1][n - 1]) + 1;

        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                if(i == m - 1 && j == n - 1) {
                    continue;
                }
                // Calculate the minimum between right cell and bottom cell
                int min = Math.min(dp[i][j + 1], dp[i + 1][j]);
                if(dungeon[i][j] >= 0) {
                    dp[i][j] = dungeon[i][j] > min ? 1 : min - dungeon[i][j];
                } else {
                    dp[i][j] = min + Math.abs(dungeon[i][j]);
                }
            }
        }

        printDP(dp);

        return dp[0][0];
    }

    public static void main(String[] args) {
        DungeonGame dg = new DungeonGame();
        // Negative Integers are demons that reduce the health of the Knight
        // Positive Integers are magical orbs that increase the health of the Knight
        /*int[][] dungeon = {{-2, -3 , 3},
                           {-5, -10, 1},
                           {10, 30, -5}}; */

        int[][] dungeon = {{1, -2 , 4, 5},
                           {-3, 6, 7, -2},
                           {4, 5, -8, -3},
                           {6, 9, 7, -6}};

        int minHealthRequired = dg.calculateMinimumHealth(dungeon);
        System.out.println("\nMinimum Health Required by the Knight to save the Queen is " + minHealthRequired);
    }

}
