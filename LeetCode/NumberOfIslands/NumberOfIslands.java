/**
 * Number of Islands
 * LeetCode Problem 200 - https://leetcode.com/problems/number-of-islands/
 * @author: Phani Kiran Thaticharla
 */

public class NumberOfIslands {

    private int numOfIslands;

    public int getNumOfIslands() {
        return numOfIslands;
    }

    public void incNumOfIslands() {
        this.numOfIslands++;
    }

     /**
     * dfs - depth first search on all 4 sides to identify neighboring lands
     * @param row currentRow
     * @param col currentColumn
     * @param matrix inputMatrix
     */
    public void dfs(int row, int col, int[][] matrix) {
        // If row or column is invalid, return
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            return;
        }

        // If the cell value is 0 (either water or visited cell), return
        if (matrix[row][col] == 0)
            return;

        // Modify the value of current row and column to 0 to keep track of visited elements
        // and stop infinite loops
        matrix[row][col] = 0;

        // Do a depth first search on all 4 directions from this cell
        dfs(row, col - 1, matrix); // Left Column
        dfs(row, col + 1, matrix); // Right Column
        dfs(row - 1, col, matrix); // Top Row
        dfs(row + 1, col, matrix); // Bottom Row
    }

    public static void main(String[] args) {

        NumberOfIslands numberOfIslands = new NumberOfIslands();

        int[][] matrix = {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,1,1,0},
                {0,0,1,0,1},
        };

        // Check for edge cases
        if (matrix == null || matrix.length == 0) {
            return;
        }

        // Iterate through every element in the matrix
        for(int row=0; row < matrix.length; row++) {
            for(int col=0; col < matrix[0].length; col++) {
                // Whenever a land is encountered, increment number of islands and trigger dfs
                // to find neighboring lands part of the same island
                if (matrix[row][col] == 1) {
                    numberOfIslands.incNumOfIslands();
                    numberOfIslands.dfs(row, col, matrix);
                }
            }
        }
        System.out.println("Number of Islands = " + numberOfIslands.getNumOfIslands());
    }

}


