import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	// 시간과 방향을 나타내는 페어 클래스
	static class Pair {
		int timdD;
		char Dir;

		Pair(int timeD, char Dir) {
			this.timdD = timeD;
			this.Dir = Dir;
		}
	}

	static int Arr[][];
	static int dx[] = { 0, 1, 0, -1 }, dy[] = { 1, 0, -1, 0 };
	static int N, K, L, Time, direction;

	static Queue<Pair> chngD = new LinkedList<>();
	static Queue<int[]> body = new LinkedList<>();
	static Queue<int[]> q = new LinkedList<>();

	//유효한 위치인가?
	static boolean isValid(int x, int y) {
		return (x > 0 && x <= N && y > 0 && y <= N);
	}

	//탐색 시작
	static void snake(int start_x, int start_y) {
		//초기 위치 세팅 -> 1.뱀, 2.사과, 3.아무고토없음
		Arr[start_x][start_y] = 1;
		q.add(new int[] { start_x, start_y });
		body.add(new int[] { start_x, start_y });
		direction = 0;

		//큐가 비어있지 않을 때까지 탐색
		while (!q.isEmpty()) {
			
			//현재 위치
			int x = q.peek()[0];
			int y = q.poll()[1];

			//방향 바꿀 타이밍인가?
			if (!chngD.isEmpty() && chngD.peek().timdD == Time) {
				if (chngD.poll().Dir == 'D') {
					direction = (direction + 1) % 4;
				} else {
					direction = (direction + 3) % 4;
				}
			}

			//다음 위치
			int next_x = x + dx[direction];
			int next_y = y + dy[direction];

			//게임이 끝나지 않을 조건인가?
			if (isValid(next_x, next_y) && Arr[next_x][next_y] != 1) {
				
				//다음위치 넣기
				q.add(new int[] { next_x, next_y });
				body.add(new int[] { next_x, next_y });

				//아무고토 없는 공간이명 몸 길이 줄임
				if (Arr[next_x][next_y] == 0) {
					Arr[next_x][next_y] = 1;
					Arr[body.peek()[0]][body.poll()[1]] = 0;
				}
				
				//사과가 있는 공간이면 몸 길이 안 줄임
				else{
					Arr[next_x][next_y] = 1;
				}

			}

			//시간 증가
			Time++;

		}

		//출력
		sb.append(Time);
	}

	public static void main(String[] args) throws Exception {

		// 맵 크기, 사과의 개수
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		Arr = new int[N + 1][N + 1];

		// 사과 위치 맵핑
		for (int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			Arr[x][y] = 2;
		}

		L = Integer.parseInt(br.readLine());

		// 바꿀 방향 맵핑
		for (int i = 0; i < L; ++i) {
			st = new StringTokenizer(br.readLine());
			int timeD = Integer.parseInt(st.nextToken());
			char Dir = st.nextToken().charAt(0);

			chngD.add(new Pair(timeD, Dir));
		}

		//탐색 시작
		snake(1, 1);

		System.out.println(sb.toString());

	}

}