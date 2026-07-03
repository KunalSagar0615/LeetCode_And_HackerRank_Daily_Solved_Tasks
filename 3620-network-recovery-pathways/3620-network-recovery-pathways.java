import java.util.*;

class Solution {
    List<int[]>[] g;
    long k;
    int n;

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        this.k = k;
        n = online.length;

        g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());

        int l = Integer.MAX_VALUE, r = 0;

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];

            if (online[u] && online[v]) {
                g[u].add(new int[]{v, w});
                l = Math.min(l, w);
                r = Math.max(r, w);
            }
        }

        if (l == Integer.MAX_VALUE) return -1;

        while (l < r) {
            int mid = (l + r + 1) / 2;

            if (check(mid)) l = mid;
            else r = mid - 1;
        }

        return check(l) ? l : -1;
    }

    boolean check(int minEdge) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq =
                new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        dist[0] = 0;
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];

            if (d > dist[u]) continue;

            if (u == n - 1) return d <= k;

            for (int[] e : g[u]) {
                int v = e[0], w = e[1];

                if (w < minEdge) continue;

                long nd = d + w;

                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.offer(new long[]{nd, v});
                }
            }
        }

        return false;
    }
}