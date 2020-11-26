/**
 * Solution to Word Ladder 1 and 2
 * YouTube Video Links below:
 * Word Ladder Part 1 - https://youtu.be/LzJxIM696bo
 * Word Ladder Part 2 - https://youtu.be/OTT8Ua3bmvU
 * @author: Phani Thaticharla
 */

import java.util.*;

public class WordLadder {
    Map<String, HashSet<String>> wordMap;
    private int level = 0;

    /**
     * Function that finds the length of shortest transformation sequence between begin and end word
     * by Breadth First Search using Queue Data Structure
     * @param beginWord
     * @param endWord
     * @param wordSet
     */
    private void bfs(String beginWord, String endWord, Set<String> wordSet) {
        Queue<String> wordQueue = new LinkedList<>();
        wordQueue.offer(beginWord);
        wordMap = new HashMap<>();
        boolean endWordFound = false;
        while(!wordQueue.isEmpty() && !endWordFound) {
            Set<String> visitedWords = new HashSet<>();
            int size = wordQueue.size(); // Determine the size of the queue. This gives us number of words at a particular level.
                                        //  Once we process all words of that size, we will move to the next level.
            for(int i = 0; i < size; i++) {
                String curWord = wordQueue.poll();
                if(curWord == endWord && wordSet.isEmpty()) {
                    return;
                }
                char[] charArray = curWord.toCharArray();
                for(int j = 0; j < charArray.length; j++) {
                    char curChar = charArray[j];
                    for(char ch = 'a'; ch <= 'z'; ch++) {
                        if(ch == curChar)
                            continue;
                        charArray[j] = ch;
                        String newWord = new String(charArray);
                        if(wordSet.contains(newWord)) {
                            wordQueue.offer(newWord);
                            visitedWords.add(newWord);
                            if(wordMap.get(curWord) == null) {
                                wordMap.put(curWord, new HashSet<String>());
                            }
                            wordMap.get(curWord).add(newWord);
                        }
                        if(newWord.equals(endWord)) {
                            endWordFound = true;
                        }
                    }
                    charArray[j] = curChar;
                }
            }
            wordSet.removeAll(visitedWords);
            level++;
        }
    }

    /**
     * Function to build the actual transformation sequence using Depth First Search and BackTracking
     * @param curWord
     * @param endWord
     * @param transSequence
     * @param output
     */
    private void dfs(String curWord, String endWord, List<String> transSequence, List<List<String>> output) {
        if(curWord.equals(endWord)) {
            output.add(new ArrayList<>(transSequence)); // Deep Copy
            return;
        }

        HashSet<String> transformedWords = wordMap.get(curWord);
        if(transformedWords == null)
            return;
        for(String word: transformedWords) {
            transSequence.add(word);
            dfs(word, endWord, transSequence, output);
            transSequence.remove(transSequence.size() - 1);
        }
    }

    /**
     * Driver method that calls:
     * bfs() for building the HashMap and finding length of shortest transformation sequence
     * dfs() for building all the shortest transformation sequences
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        bfs(beginWord, endWord, wordSet);
        List<List<String>> allTransSequences = new ArrayList<>();
        List<String> transSequence = new ArrayList<>();
        transSequence.add(beginWord);
        dfs(beginWord, endWord, new ArrayList<>(transSequence), allTransSequences);
        return allTransSequences;
    }

    /**
     * Driver Main
     * @param args
     */
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cow";
        List<String> wordList = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog", "cow"));
        WordLadder wl = new WordLadder();
        List<List<String>> allTransSequences = wl.findLadders(beginWord, endWord, wordList);
        System.out.println("Number of transformations: " + wl.level);
        System.out.println("Length of the shortest transformation sequence: " + (wl.level + 1));
        System.out.println("All Shortest Transformation Sequences: " + allTransSequences);
    }
}
