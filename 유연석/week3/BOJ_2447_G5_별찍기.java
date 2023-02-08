package week3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2447_G5_별찍기 {
	static char[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// input
		int N = Integer.parseInt(br.readLine());
		br.close();
		map = new char[N][N];

		// solving
		stamp(N, 0, 0);

		// output
		for (int i = 0; i < N; i++) {
			bw.write(String.copyValueOf(map[i]) + "\n");
		}
		bw.close();
	}

	private static void stamp(int n, int x, int y) {
		// n*n크기의 공간을 3*3 등분해서 divide
		int partSize = n / 3;
		if (n == 1)
			return;

		// 작은 공간 (9개 중 한 덩어리)
		for (int k = 0; k < 9; k++) {
			// x, y는 내가 호출된 지역 (블록의 인덱스)
			// j, i는 이번에 내가 가야될 지역
			int j = k % 3;
			int i = k / 3;
			// 가운데 일 때 whitespace 출력
			if (k == 4) {
				stampWhite(partSize, x, y);
				continue;
			}
			// 가운데 아닐 땐,
			// 최소 크기면 *찍고 아니면 더 작은 재귀 호출
			if (n == 3) {
				map[x + i][y + j] = '*';
			} else {
				stamp(partSize, x + partSize * j, y + partSize * i);
			}

		}
	}

	// 공백 찍기
	private static void stampWhite(int n, int x, int y) {
		for (int i = n + y; i < n + y + n; i++) {
			for (int j = n + x; j < n + x + n; j++) {
				map[i][j] = ' ';
			}
		}
	}

}