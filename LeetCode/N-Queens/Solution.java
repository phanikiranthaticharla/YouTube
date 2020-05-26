/**
* N-Queens 
* Leetcode Problem #51
* @author: Phani Kiran Thaticharla  
*/

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> output = new ArrayList<>(); 
        solveNQueens(n, output, new ArrayList<>(), 0); 
        return output; 
    }
    
	/**
	* Recursive method that does all the work 
	* @param: n, number of rows
	* @param: output, Output List 
	* @param: solution, List of Integer, index of the list represents row, value represents column
	* @param: row, current row in recursion
	*/
    private void solveNQueens(int n, List<List<String>> output, List<Integer> solution, int row) {
		/* Found one of the solutions, add it to output */
        if (row == n) {
            output.add(helper(solution, n)); 
            return; 
        }
        
		/* Iterate through every column and place the next queen */
        for (int col=0; col < n; col++) {
            solution.add(col); 
            /* If the queen is safe in this column, move to next level and place the next queen */
			if (isQueenSafe(row, col, solution)) {
                solveNQueens(n, output, solution, row + 1); 
            }
			/* Once we explored the last row, backtrack and explore all possibilities for row - 1 */
            solution.remove(solution.size() - 1);
        }
    }
    
	/**
	* Method that checks if the current queen is safe in this position
	* For this, current Queen is compared to all other queens in the previous rows
	* @param curQueenRow
	* @param curQueenCol
	* @param solution, List of Integer containing queen placements
	*/
    private boolean isQueenSafe(int curQueenRow, int curQueenCol, List<Integer> solution) {
        for (int prevQueenRow = 0; prevQueenRow < curQueenRow; prevQueenRow++) {
            int prevQueenCol = solution.get(prevQueenRow); 
            if (curQueenCol == prevQueenCol || Math.abs(curQueenCol - prevQueenCol) == (curQueenRow - prevQueenRow)) {
                return false; 
            }
        }
        return true; 
    }
    
	/**
	* A helper method that converts List<Integer> to List<String> 
	*/
    private List<String> helper(List<Integer> solution, int n) {
        List<String> strList = new ArrayList<>(); 
        for (int row = 0; row < n; row++) {
            StringBuilder sb = new StringBuilder(); 
            for (int col = 0; col < n; col++) {
                if (col == solution.get(row)) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            strList.add(sb.toString());
        }
        return strList; 
    }
}

