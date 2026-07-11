class Solution {
    public int countCompleteComponents(int n, int[][] edges) {

        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] visited = new boolean[n];
        int completeCount = 0;

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                int[] result = dfs(i, graph, visited);

                int nodes = result[0];
                int edgeCount = result[1] / 2; // each edge counted twice

                if (edgeCount == nodes * (nodes - 1) / 2) {
                    completeCount++;
                }
            }
        }

        return completeCount;
    }

    private int[] dfs(int node, List<Integer>[] graph, boolean[] visited) {

        visited[node] = true;

        int nodes = 1;
        int edges = graph[node].size();

        for (int neighbor : graph[node]) {

            if (!visited[neighbor]) {
                int[] res = dfs(neighbor, graph, visited);

                nodes += res[0];
                edges += res[1];
            }
        }

        return new int[]{nodes, edges};
    }
}