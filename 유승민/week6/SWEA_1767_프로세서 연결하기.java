import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static class Core {
		int x, y;

		Core(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int T, N, conn, ans, arr[][], deltas[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static ArrayList<Core> al;

	static boolean DFS(int chng[][], int x, int y, int dir) {
		if (x == 0 || x == N - 1 || y == 0 || y == N - 1) {
			return true;
		}

		int nextX = x + deltas[dir][0];
		int nextY = y + deltas[dir][1];

		if (chng[nextX][nextY] == 0) {
			chng[nextX][nextY] = 2;
			return DFS(chng, nextX, nextY, dir);
		}

		return false;
	}

	static void combi(int prev[][], int cur, int sel, int dep, int isConn) {

		if (sel == dep) {
			int line = 0;

			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (prev[i][j] == 2)
						line++;
				}
			}

			if (isConn > conn) {
				ans = line;
				conn = isConn;
			} else if (isConn == conn) {
				ans = Math.min(ans, line);
			}

			return;
		}
		
		if(dep - sel + isConn < conn) return;

		int copyArr[][] = new int[N][N];
		int chngArr[][] = new int[N][N];

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				copyArr[i][j] = prev[i][j];
			}
		}

		for (int i = cur; i < al.size(); ++i) {
			for (int j = 0; j < 4; ++j) {
				for (int k = 0; k < N; ++k) {
					for (int l = 0; l < N; ++l) {
						chngArr[k][l] = copyArr[k][l];
					}
				}

				if (DFS(chngArr, al.get(i).x, al.get(i).y, j)) {
					combi(chngArr, i + 1, sel + 1, dep, isConn + 1);
				}
				else {
					combi(copyArr, i + 1, sel + 1, dep, isConn);
				}

			}
		}

	}

	public static void main(String[] args) throws Exception {

		// 테스트 케이스 개수
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; ++t) {
			N = Integer.parseInt(br.readLine());

			al = new ArrayList<>();
			arr = new int[N][N];
			conn = 0;
			ans = 0;

			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; ++j) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] == 1 && (i != 0 && i != N - 1) && (j != 0 && j != N - 1)) {
						al.add(new Core(i, j));
					}
				}
			}

			combi(arr, 0, 0, al.size(), 0);

			sb.append("#").append(t).append(" ").append(ans).append("\n");

		}

		System.out.println(sb);
	}

}
