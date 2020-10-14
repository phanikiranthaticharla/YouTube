/**
 * Program to compute the number of meeting rooms required
 * for a given list of meetings
 * @author Phani Kiran Thaticharla
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRooms {

    static class compareStartTime implements Comparator<int[]> {
        public int compare(int[] t1, int[] t2) {
            return t1[0] - t2[0];
        }
    }

    static class compareEndTime implements Comparator<int[]> {
        public int compare(int[] t1, int[] t2) {
            return t1[1] - t2[1];
        }
    }

    private void printIntervals(int[][] intervals) {
        for(int i = 0; i < intervals.length; i++) {
            System.out.print("(" + intervals[i][0] + "," + intervals[i][1] + ")");
        }
        System.out.println("");
    }

    /**
     * Function that checks if any of the meetings clash
     * @param intervals Given unsorted list of meetings
     * @return
     */
    private boolean meetingsClash(int[][] intervals) {
        int prevMeetingEndTime = intervals[0][1];
        for(int i = 1; i < intervals.length; i++) {
            if(prevMeetingEndTime > intervals[i][0]) {
                return true;
            } else {
                prevMeetingEndTime = intervals[i][1];
            }
        }
        return false;
    }

    /**
     * Function that calculates the number of meeting rooms required
     * @param pq Priority Queue that keeps track of meetings based on end time
     * @param intervals List of meeting intervals sorted based on start time
     * @return
     */
    private int numRoomsRequired(PriorityQueue<int[]> pq, int[][] intervals) {
        pq.offer(intervals[0]);
        for(int i = 1; i < intervals.length; i++) {
            // If start time of next meeting is less than end time of meeting in root node of pq,
            // add a new meeting
            if(intervals[i][0] < pq.peek()[1]) {
                pq.offer(intervals[i]);
            } else {
                pq.poll();
                pq.offer(intervals[i]);
            }
        }
        return pq.size();
    }

    /**
     * Driver
     * @param args
     */
    public static void main(String[] args) {
        MeetingRooms m = new MeetingRooms();
        int[][] intervals = {{0, 60}, {10,30}, {50,80}, {30,70}, {20, 50}};
        m.printIntervals(intervals);
        Arrays.sort(intervals, new compareStartTime());
        System.out.println("Sorted Time Intervals based on Start Time are : ");
        m.printIntervals(intervals);
        boolean meetingsClash = m.meetingsClash(intervals);
        System.out.println("Any Meetings Clash? : " + meetingsClash);
        PriorityQueue<int[]> pq = new PriorityQueue<>(new compareEndTime());
        int numRooms = m.numRoomsRequired(pq, intervals);
        System.out.println("Number of rooms required to accommodate all meetings : " + numRooms);
    }

}
