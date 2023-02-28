import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 입력 버퍼
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// 출력용 스트링 빌더
	static StringBuilder sb = new StringBuilder();
	// 입력용 스트링토크니저
	static StringTokenizer st;

	// 스카프 개수, 둘레 길이, 탐색 방향
	static int N, Line, deltas[][] = new int[][] { { -1, 0 }, { 0, -1 } };
	// 영역 크기
	static boolean isSelect[][] = new boolean[102][102];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 스카프 개수 입력받기
		N = Integer.parseInt(br.readLine());

		// 스카프가 놓이는 위치 입력받기
		for (int i = 0; i < N; ++i) {
			// 한줄 입력받기
			st = new StringTokenizer(br.readLine());
			// 스카프의 x축 위치
			int x = Integer.parseInt(st.nextToken());
			// 스카프의 y축 위치
			int y = Integer.parseInt(st.nextToken());

			// 스카프가 놓인 영역 체크
			for (int j = 0; j < 10; ++j) {
				for (int k = 0; k < 10; ++k) {
					// 스카프가 놓여있다고 변경
					isSelect[x + j][y + k] = true;
				}
			}
		}

		// 둘레 구하는 부분
		for (int i = 1; i < 101; ++i) {
			for (int j = 1; j < 101; ++j) {

				// 2방탐색
				for (int k = 0; k < 2; ++k) {
					// 다음 탐색할 x좌표
					int nextX = i + deltas[k][0];
					// 다음 탐색할 y좌표
					int nextY = j + deltas[k][1];

					// 현재 위치와 다음 위치의 스카프 상태가 다른 경우(exclusive or) 둘레 더하기
					if (isSelect[i][j] ^ isSelect[nextX][nextY]) {
						Line++;
					}
				}
			}
		}

		// 정답 출력
		System.out.println(Line);

	}

}
