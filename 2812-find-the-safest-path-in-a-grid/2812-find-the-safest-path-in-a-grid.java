class Solution {

    class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px == py)
                return;

            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else if (rank[px] > rank[py]) {
                parent[py] = px;
            } else {
                parent[py] = px;
                rank[px]++;
            }
        }
    }

    public int maximumSafenessFactor(List<List<Integer>> grid) {

        int n = grid.size();

        int[][] dist = new int[n][n];

        for (int[] row : dist)
            Arrays.fill(row, -1);

        Queue<int[]> q = new LinkedList<>();

        // Multi-source BFS initialization
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    q.offer(new int[] { i, j });
                }
            }
        }

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        // Multi-source BFS
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                    continue;

                if (dist[nx][ny] != -1)
                    continue;

                dist[nx][ny] = dist[x][y] + 1;
                q.offer(new int[] { nx, ny });
            }
        }

        List<int[]> cells = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells.add(new int[] { dist[i][j], i, j });
            }
        }

        // Sort in descending order of distance
        cells.sort((a, b) -> b[0] - a[0]);

        boolean[][] active = new boolean[n][n];

        DSU dsu = new DSU(n * n);

        for (int[] cell : cells) {

            int d = cell[0];
            int x = cell[1];
            int y = cell[2];

            active[x][y] = true;

            int id = x * n + y;

            for (int k = 0; k < 4; k++) {

                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                    continue;

                if (!active[nx][ny])
                    continue;

                dsu.union(id, nx * n + ny);
            }

            if (active[0][0] && active[n - 1][n - 1]
                    && dsu.find(0) == dsu.find(n * n - 1)) {
                return d;
            }
        }

        return 0;
    }
}