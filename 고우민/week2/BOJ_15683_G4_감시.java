import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
    int x, y, id;

    Node(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
}

public class BOJ_15683_G4_감시 {

    static int n, m;
    static int[][] map;
    static int[][] temp;
    static int[][] cctv = {
            { 0 },
            { 0 },
            { 0, 2 },
            { 0, 1 },
            { 0, 1, 2 },
            { 0, 1, 2, 3 }
    };

    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    static List<Node> nodes = new ArrayList<>();
    static int ans = Integer.MAX_VALUE;

    static boolean oob(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m)
            return true;
        return false;
    }

    static void check(int x, int y, int d, int[][] tmp) {
        while (true) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (oob(nx, ny))
                break;
            if (tmp[nx][ny] == 6)
                break;
            else if (tmp[nx][ny] == 0) {
                tmp[nx][ny] = 7;
            }
            x = nx;
            y = ny;
        }
    }

    static int count(int[][] temp) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 0)
                    cnt++;
            }
        }
        return cnt;
    }

    static int[][] copy(int[][] temp) {
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = temp[i][j];
            }
        }
        return res;
    }

    static void dfs(int idx, int[][] temp) {
        if (idx == nodes.size()) {
            ans = Math.min(ans, count(temp));
            return;
        }
        Node node = nodes.get(idx);
        if (node.id == 1) {
            for (int d = 0; d < 4; d++) {
                int[][] tmp = copy(temp);
                check(node.x, node.y, d, tmp);
                dfs(idx + 1, tmp);
            }
        } else if (node.id == 2) {
            for (int d = 0; d < 2; d++) {
                int[][] tmp = copy(temp);
                check(node.x, node.y, d, tmp);
                check(node.x, node.y, d + 2, tmp);
                dfs(idx + 1, tmp);
            }
        } else if (node.id == 3) {
            for (int d = 0; d < 4; d++) {
                int[][] tmp = copy(temp);
                check(node.x, node.y, d, tmp);
                int d2 = d + 1 > 3 ? 0 : d + 1;
                check(node.x, node.y, d2, tmp);
                dfs(idx + 1, tmp);
            }
        } else if (node.id == 4) {
            for (int d = 0; d < 4; d++) {
                int[][] tmp = copy(temp);
                check(node.x, node.y, d, tmp);
                int d2 = d + 1 > 3 ? 0 : d + 1;
                check(node.x, node.y, d2, tmp);
                int d3 = d2 + 1 > 3 ? 0 : d2 + 1;
                check(node.x, node.y, d3, tmp);
                dfs(idx + 1, tmp);
            }
        } else {
            int[][] tmp = copy(temp);
            for (int d = 0; d < 4; d++) {
                check(node.x, node.y, d, tmp);
            }
            dfs(idx + 1, tmp);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) {
                    nodes.add(new Node(i, j, map[i][j]));
                }
            }
        }
        dfs(0, map);
        System.out.println(ans);
    }
}