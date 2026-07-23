class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> wordSet = new HashSet<>(wordList);

        // If endWord doesn't exist, transformation is impossible
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int steps = 1;

        while (!queue.isEmpty()) {

            int size = queue.size();

            // Process one BFS level
            for (int i = 0; i < size; i++) {

                String word = queue.poll();

                if (word.equals(endWord)) {
                    return steps;
                }

                char[] chars = word.toCharArray();

                // Try changing every character
                for (int j = 0; j < chars.length; j++) {

                    char originalChar = chars[j];

                    for (char c = 'a'; c <= 'z'; c++) {

                        chars[j] = c;

                        String newWord = new String(chars);

                        if (wordSet.contains(newWord)) {

                            queue.offer(newWord);

                            // Mark as visited
                            wordSet.remove(newWord);
                        }
                    }

                    // Restore original character
                    chars[j] = originalChar;
                }
            }

            steps++;
        }

        return 0;
    }
}