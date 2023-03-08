import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, M, rx, ry, bx, by, ans = Integer.MAX_VALUE, deltas[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static char arr[][];

	static boolean bfs(char chngArr[][], int dir, int dep) {
		boolean tk = (dir == 0 && rx > bx) || (dir == 1 && ry < by) || (dir == 2 && rx < bx) || (dir == 3 && ry > by);
		boolean goal = false;

		chngArr[rx][ry] = '.';
		chngArr[bx][by] = '.';

		if (tk) {
			while (chngArr[bx][by] == '.') {
				bx += deltas[dir][0];
				by += deltas[dir][1];
			}

			if (chngArr[bx][by] == 'O')
				return true;
			else {
				bx -= deltas[dir][0];
				by -= deltas[dir][1];
				chngArr[bx][by] = 'B';
			}

			while (chngArr[rx][ry] == '.') {
				rx += deltas[dir][0];
				ry += deltas[dir][1];
			}

			if (chngArr[rx][ry] == 'O') {
				ans = Math.min(ans, dep);
				return true;
			} else {
				rx -= deltas[dir][0];
				ry -= deltas[dir][1];
				chngArr[rx][ry] = 'R';
			}

		} else {
			while (chngArr[rx][ry] == '.') {
				rx += deltas[dir][0];
				ry += deltas[dir][1];
			}

			if (chngArr[rx][ry] == 'O') {
				goal = true;
			} else {
				rx -= deltas[dir][0];
				ry -= deltas[dir][1];
				chngArr[rx][ry] = 'R';
			}

			while (chngArr[bx][by] == '.') {
				bx += deltas[dir][0];
				by += deltas[dir][1];
			}

			if (chngArr[bx][by] == 'O')
				return true;
			else {
				bx -= deltas[dir][0];
				by -= deltas[dir][1];
				chngArr[bx][by] = 'B';
			}
		}

		if (goal) {
			ans = Math.min(ans, dep);
			return true;
		}
		return false;
	}

	static void permu(char prevArr[][], int dep, boolean flag) {
		if (flag || dep == 10 || dep >= ans - 1) {
			return;
		}

		char copyArr[][] = new char[N][M];
		char chngArr[][] = new char[N][M];

		for (int i = 0; i < N; ++i) {
			copyArr[i] = prevArr[i].clone();
		}

		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < N; ++j) {
				for (int k = 0; k < M; ++k) {
					chngArr[j][k] = copyArr[j][k];
					if (chngArr[j][k] == 'R') {
						rx = j;
						ry = k;
					} else if (chngArr[j][k] == 'B') {
						bx = j;
						by = k;
					}
				}
			}

			permu(chngArr, dep + 1, bfs(chngArr, i, dep + 1));
		}
	}

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new char[N][M];

		for (int i = 0; i < N; ++i) {
			String s = br.readLine();
			for (int j = 0; j < M; ++j) {
				arr[i][j] = s.charAt(j);
			}
		}

		permu(arr, 0, false);

		if (ans == Integer.MAX_VALUE)
			ans = -1;
		System.out.println(ans);
	}

}
