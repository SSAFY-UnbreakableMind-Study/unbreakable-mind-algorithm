import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {
    int x, y;

    Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class BOJ_1012_S2_유기농배추 {

    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int T, n, m, k, answer;
    static int[][] map;
    static Queue<Pos> q;

    static boolean isValidPos(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m)
            return false;
        return true;
    }

    static void bfs(int x, int y) {
        q = new LinkedList<>();
        q.offer(new Pos(x, y));
        while (!q.isEmpty()) {
            Pos now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (isValidPos(nx, ny) && map[nx][ny] == 1) {
                    map[nx][ny] = 2;
                    q.offer(new Pos(nx, ny));
                }
            }
        }
        answer++;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            map = new int[n][m];
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                map[r][c] = 1;
            }

            answer = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 1) {
                        map[i][j] = 2;
                        bfs(i, j);
                    }
                }
            }
            System.out.println(answer);
        }
    }

}