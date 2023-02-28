import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, M, x, y, K;
	static int Arr[][], dice[] = new int[7];
	static int deltas[][] = { { 0, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

	//유효한 위치인가?
	static boolean isValid(int nextX, int nextY) {
		return (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 맵 크기, 주사위 위치, 쿼리 개수
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Arr = new int[N][M];

		// 맵 맵핑
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; ++j) {
				Arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 쿼리 입력 받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; ++i) {
			int dir = Integer.parseInt(st.nextToken());
			int nextX = x + deltas[dir][0];
			int nextY = y + deltas[dir][1];

			if (!isValid(nextX, nextY))
				continue;

			//주사위 굴리는중..
			int tmp = dice[1];
			if (dir == 1) {
				dice[1] = dice[3]; dice[3] = dice[6]; dice[6] = dice[4]; dice[4] = tmp;
			}
			else if(dir == 2) {
				dice[1] = dice[4]; dice[4] = dice[6]; dice[6] = dice[3]; dice[3] = tmp;
			}
			else if(dir == 3) {
				dice[1] = dice[2]; dice[2] = dice[6]; dice[6] = dice[5]; dice[5] = tmp;
			}
			else {
				dice[1] = dice[5]; dice[5] = dice[6]; dice[6] = dice[2]; dice[2] = tmp;
			}
			
			//바닥 글자 확인중..
			if(Arr[nextX][nextY] == 0) Arr[nextX][nextY] = dice[1];
			else {
				dice[1] = Arr[nextX][nextY];
				Arr[nextX][nextY] = 0;
			}
			
			//주사위 위치 변경 완료!
			x = nextX;
			y = nextY;
			
			//주사위 윗면 글자 출력
			System.out.println(dice[6]);
		}

	}

}
