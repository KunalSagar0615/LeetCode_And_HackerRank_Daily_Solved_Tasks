class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int ones = 0;
        for (int i = 0; i < n; i++) if (s.charAt(i) == '1') ones++;

        // groups: each row = {start, length} of a maximal run of '0'
        List<int[]> groupsList = new ArrayList<>();
        int[] zgi = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (!groupsList.isEmpty()) {
                    int[] last = groupsList.get(groupsList.size() - 1);
                    if (last[0] + last[1] == i) {
                        last[1]++;
                    } else {
                        groupsList.add(new int[]{i, 1});
                    }
                } else {
                    groupsList.add(new int[]{i, 1});
                }
            }
            zgi[i] = groupsList.size() - 1;
        }

        List<Integer> ans = new ArrayList<>();

        if (groupsList.isEmpty()) {
            for (int[] q : queries) ans.add(ones);
            return ans;
        }

        int[][] groups = groupsList.toArray(new int[0][]);
        int m = groups.length;

        // arr[i] = length(group i) + length(group i+1)
        int[] arr = new int[Math.max(0, m - 1)];
        for (int i = 0; i < m - 1; i++) {
            arr[i] = groups[i][1] + groups[i + 1][1];
        }

        // sparse table for range-max on arr
        int[][] sparse = null;
        int[] log2 = null;
        if (arr.length > 0) {
            int len = arr.length;
            int LOG = 1;
            while ((1 << LOG) <= len) LOG++;
            sparse = new int[LOG][len];
            sparse[0] = arr.clone();
            for (int k = 1; k < LOG; k++) {
                int half = 1 << (k - 1);
                for (int j = 0; j + (1 << k) <= len; j++) {
                    sparse[k][j] = Math.max(sparse[k - 1][j], sparse[k - 1][j + half]);
                }
            }
            log2 = new int[len + 1];
            for (int i = 2; i <= len; i++) log2[i] = log2[i / 2] + 1;
        }

        final int[][] sp = sparse;
        final int[] lg = log2;

        for (int[] query : queries) {
            int l = query[0], r = query[1];
            int best = ones;

            int gl = zgi[l], gr = zgi[r];
            int left = -1, right = -1;
            if (s.charAt(l) == '0') {
                left = groups[gl][1] - (l - groups[gl][0]);
            }
            if (s.charAt(r) == '0') {
                right = r - groups[gr][0] + 1;
            }

            int A = gl + 1;
            int B = (s.charAt(r) == '1') ? gr : gr - 1;

            if (s.charAt(l) == '0' && s.charAt(r) == '0' && gl + 1 == gr) {
                best = Math.max(best, ones + left + right);
            } else if (A <= B - 1 && sp != null) {
                int lo = A, hi = B - 1;
                int k = lg[hi - lo + 1];
                int rmqVal = Math.max(sp[k][lo], sp[k][hi - (1 << k) + 1]);
                best = Math.max(best, ones + rmqVal);
            }

            if (s.charAt(l) == '0' && A <= B) {
                best = Math.max(best, ones + left + groups[A][1]);
            }
            if (s.charAt(r) == '0' && A <= B) {
                best = Math.max(best, ones + right + groups[B][1]);
            }

            ans.add(best);
        }

        return ans;
    }
}