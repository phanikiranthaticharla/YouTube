import java.util.HashSet;

public class RobotImplementation implements Robot {

    private int initialAngle;
    private int initialRow;
    private int initialCol;
    private int angle;
    private int row;
    private int col;
    private HashSet<String> cellsCleaned = new HashSet();
    private int[][] grid;

    public RobotImplementation(int initialAngle, int initialRow, int initialCol, int[][] grid) {
        this.initialAngle = initialAngle;
        this.initialRow = initialRow;
        this.initialCol = initialCol;
        this.angle = initialAngle;
        this.row = initialRow;
        this.col = initialCol;
        this.grid = grid;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public int getInitialAngle() {
        return initialAngle;
    }

    public void setInitialAngle(int initialAngle) {
        this.initialAngle = initialAngle;
        this.angle = initialAngle;
    }

    public int getInitialRow() {
        return initialRow;
    }

    public void setInitialRow(int initialRow) {
        this.initialRow = initialRow;
        this.row = initialRow;
    }


    public int getInitialCol() {
        return initialCol;
    }

    public void setInitialCol(int initialCol) {
        this.initialCol = initialCol;
        this.col = initialCol;
    }



    @Override
    public boolean move() {
        switch(angle) {
            case 0:
                // Move Robot to next column i.e to the right
                if(col + 1 < grid[0].length && grid[row][col + 1] != 0) {
                    col++;
                    System.out.println("Moved to next column (" + row + "," + col + ")");
                    return true;
                } else {
                    System.out.println("Move failed. Wall or Obstacle detected to the right of (" + row + " " + col + ")");
                }
                break;
            case 90:
                // Move Robot to previous row i.e to the top
                if(row - 1 >= 0 && grid[row - 1][col] != 0) {
                    row--;
                    System.out.println("Moved to previous row (" + row + "," + col + ")");
                    return true;
                } else {
                    System.out.println("Move failed. Wall or Obstacle detected at the top of (" + row + "," + col + ")");
                }
                break;
            case 180:
                // Move Robot to previous col i.e to the left
                if(col - 1 >= 0 && grid[row][col - 1] != 0) {
                    col--;
                    System.out.println("Moved to previous column (" + row + "," + col + ")");
                    return true;
                } else {
                    System.out.println("Move failed. Wall or Obstacle detected to the left of (" + row + "," + col + ")");
                }
                break;
            case 270:
                if(row + 1  < grid.length && grid[row + 1][col] != 0) {
                    row++;
                    System.out.println("Moved to next row (" + row + "," + col + ")");
                    return true;
                } else {
                    System.out.println("Move failed. Wall or Obstacle detected at the bottom of (" + row + "," + col + ")");
                }
                break;
        }
        return false;
    }

    @Override
    public void turnLeft() {
        System.out.println("Current Orientation: " + angle);
        angle = (angle + 90) % 360;
        System.out.println("Turned Left. New Orientation: " + angle);
    }

    @Override
    public void turnRight() {
        System.out.println("Current Orientation: " + angle);
        if(angle == 0) {
            angle = 270;
            System.out.println("Turned Right. New Orientation: " + angle);
            return;
        }
        angle = (angle - 90) % 360;
        System.out.println("New Orientation: " + angle);
    }

    @Override
    public void clean() {
        String pair = "(" + row + "," + col + ")";
        System.out.println("Cleaning current cell " + pair);
        cellsCleaned.add(pair); // add (row, col) to hashset
    }

    public boolean visited() {
        String pair = "(" + row + "," + col + ")";
        if(cellsCleaned.contains(pair)) {
            System.out.println("Current cell (" + row + "," + col + ") is already cleaned. Move back to previous cell");
            return true;
        }
        return false;
    }

    public void moveBack() {
        turnRight();
        turnRight();
        move();
        turnRight();
        turnRight();
     }

    public void dfs() {

        // Check if the cell is already visited and cleaned
        if(visited()) {
            moveBack(); // Cell is already cleaned, move back to previous cell
            return;
        }

        System.out.println("Calling dfs on cell (" + row + "," + col + ")");
        clean();

        if(move()) // Initial angle = 90, move one step forward to explore cells to the top of 2D grid
            dfs();

        turnLeft(); // Now turn Left and explore cells to the left of 2D grid (angle = 180)
        if(move())
            dfs();

        turnLeft(); // Now turn Left and explore cells to the bottom (angle = 270)
        if(move())
            dfs();

        turnLeft(); // Now turn Left and explore cells to the right (angle = 360 or 0)
        if(move())
            dfs();

        turnLeft(); // Now once again turn left and
                    // we are back to the starting position of the robot in this cell
        if (angle == initialAngle && row == initialRow && col == initialCol) {
            System.out.println("Cleaned up all cells and back to starting point.");
            System.out.println(cellsCleaned.toString());
            System.out.println("Program Complete.");
            return;
        }
        System.out.println("Explored all directions for current cell (" + row + "," + col + ")");
        System.out.println("Moving back to previous cell");
        moveBack(); // Now move back to the previous cell

    }

    public static void main(String[] args) {

        int[][] grid = {
                {0,1,0,1},
                {1,1,1,1},
                {1,0,0,1},
                {1,1,1,1}}; // 1 indicates Dust and 0 indicates Obstacle
        RobotImplementation r = new RobotImplementation(90,1,1,grid);
        r.dfs();

    }
}
