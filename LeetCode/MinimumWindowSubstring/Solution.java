/**
* Minimum Window Substring
* @author: Phani Kiran Thaticharla
*/


class Solution {
    public String minWindow(String s, String t) {
	// Integer Array to keep track of the occurences of each character of T
        int[] charCountOfT = new int[128];
	// Update the count of characters seen in T
        for(char c : t.toCharArray()) {
            charCountOfT[c]++;
        }

        int i = 0, minLength = Integer.MAX_VALUE, countOfTinS = 0;
        String minSubString = "";
        for(int j = 0; j < s.length(); j++) {
	    // Decrement the count of character pointed by index j, if >= 0 means this was incremented earlier, so it is a character seen in T
            if(--charCountOfT[s.charAt(j)] >= 0) {
                countOfTinS++;
            }

	    // When all characters of T are seen, try to shrink the window
            while(countOfTinS == t.length()) {
                int curLength = j - (i - 1);
		// If a shorter window is found, update the minLength and minSubstring
                if (minLength > curLength) {
                    minLength = curLength;
                    minSubString = s.substring(i, j + 1);
                }
		// When the character pointed by i is a character seen in T, update countofTinS since this is a required character
                if (++charCountOfT[s.charAt(i)] > 0) {
                    countOfTinS--;
                }
                i++;
            }
        }
        return minSubString;
    }
}


