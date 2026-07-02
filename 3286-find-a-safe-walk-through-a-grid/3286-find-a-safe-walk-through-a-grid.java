class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {

        int m = grid.size();
        int n = grid.get(0).size();

        int[][] best = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(best[i], -1);
        }

        int startHealth = health - grid.get(0).get(0);

        if (startHealth <= 0) {
            return false;
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, startHealth});

        best[0][0] = startHealth;

        int[][] dirs = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        while (!q.isEmpty()) {

            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];
            int hp = curr[2];

            if (r == m - 1 && c == n - 1) {
                return true;
            }

            for (int[] dir : dirs) {

                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                int newHp = hp - grid.get(nr).get(nc);

                if (newHp <= 0) {
                    continue;
                }

                if (newHp > best[nr][nc]) {
                    best[nr][nc] = newHp;
                    q.offer(new int[]{nr, nc, newHp});
                }
            }
        }

        return false;
    }
}