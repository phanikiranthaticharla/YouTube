/**
 * FriendCircles
 * LeetCode Problem 549 - https://leetcode.com/problems/friend-circles/
 * @author: Phani Kiran Thaticharla
 */

class DisjointSets {
    private int friendCircles;
    private int[] representatives;

    /**
     * Constructor
     * @param n number of people
     */
    public DisjointSets(int n) {
        this.friendCircles = n;
        representatives = new int[n];
        for(int i = 0; i < n; i++) {
            representatives[i] = i;
        }
    }

    /**
     *
     * @param person
     * @return Representative of a given person
     */
    public int find(int person) {
        if (representatives[person] == person) {
            return person;
        }

        return representatives[person] = find(representatives[person]);
    }

    /**
     * Union of two Disjoint sets
     * @param a personA
     * @param b personB
     */
    public void Union(int a, int b) {
        int representativeA = find(a);
        int representativeB = find(b);

        if (representativeA != representativeB) {
            representatives[representativeB] = representativeA;
            representatives[b] = representativeA;
            friendCircles--;
        }
    }

    /**
     *
     * @return FriendCircle Count
     */
    public int getNumFriendCircles() {
        return this.friendCircles;
    }

}

public class FriendCircles {

    /**
     *
     * @param Matrix
     * @return FriendCircle Count
     */
    public int findCircleNum(int[][] Matrix) {
        if (Matrix == null || Matrix.length == 0) {
            return 0;
        }

        DisjointSets disjointSets = new DisjointSets(Matrix.length);
        for(int row=0; row < Matrix.length; row++) {
            for(int col=0; col < Matrix[0].length; col++) {
                if (Matrix[row][col] == 1 && row != col) {
                    disjointSets.Union(row, col);
                }
            }
        }
        return disjointSets.getNumFriendCircles();
    }

    public static void main(String[] args) {
        int[][] Matrix = {
                {1,1,0,0,0,1},
                {1,1,1,0,0,0},
                {0,1,1,0,0,0},
                {0,0,0,1,1,0},
                {0,0,0,1,1,0},
                {1,0,0,0,0,1}
        };
        FriendCircles fc = new FriendCircles();
        int friendCircles = fc.findCircleNum(Matrix);
        System.out.println("Number of FriendCircles = " + friendCircles);
    }

}

