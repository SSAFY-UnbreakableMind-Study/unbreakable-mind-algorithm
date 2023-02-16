import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_3190_G4_뱀 {
	static int[][] delta = { {0,1}, {1,0}, {0,-1}, {-1,0} };
	static int N;
	static int time=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[][] board;
		board = new int[N][N];
		
		// 사과 input
		for (int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			board[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 2;		//사과 있는 위치 2찍기
		}
		// 명령어 input
		int L = Integer.parseInt(br.readLine());
		Instruction[] inst = new Instruction[L];
		for (int i=0; i<L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			inst[i] = new Instruction(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
		}
		int instIdx = 0;	//몇 번째 명령어대로 해야 할 지
		
		//뱀 위치 초기화
		int di = 0;
		ArrayDeque<Pos> queue = new ArrayDeque<>();
		board[0][0] = 1;
		queue.offerLast(new Pos(0, 0));
		int time=0;
		while (time<10000) {
			/**
			 * 0~1초 동안 움직임
			 * 1초 끝나면 뱀 방향 바꾸기
			 */
			//이동하면 시간 증가
			time++;
			
			//뱀 이동
			int ni = queue.peekLast().y + delta[di][0];
			int nj = queue.peekLast().x + delta[di][1];
			if (!isValid(ni, nj)) break;		//경계선 걸리면 나가야 함
			if (board[ni][nj] == 1) break;		//뱀이 자기 몸을 만나면 멈추기
			
			if (board[ni][nj] != 2) {			//사과 없으면 원래 뱀 꼬리는 지워줌
				Pos tail = queue.pollFirst();
				board[tail.y][tail.x] = 0;
			}
			queue.offerLast(new Pos(nj, ni));	//몸길이를 늘려 머리를 다음 칸에 위치
			board[ni][nj] = 1;					//뱀 머리 이동
			
			//방향 바꿀 시간 되면 방향 바꾸기
			if (instIdx<L && time==inst[instIdx].time) {
				di = direction(di, inst[instIdx].direction);	//방향 설정, 다음 명령어로 가기 위해 명령어 인덱스 ++
				instIdx++;
			}
			
		}
		//output
		System.out.println(time);
	}
	//방향 바꾸기
	private static int direction(int di, char DIR) {
		switch(DIR) {
		case 'D':
			if (di!=3) di++;
			else di=0;
			break;
		case 'L' :
			if (di!=0) di--;
			else di=3;
			break;
		}
		return di;
	}
	//맵 벗어나는지 체크
	static boolean isValid(int i, int j) {
		return (i>=0 && i<N && j>=0 && j<N);
	}
}
//방향 바뀌는 명령어 시작 시간, 방향
class Instruction {
	int time;
	char direction;
	public Instruction(int time, char direction) {
		this.time = time;
		this.direction = direction;
	}
}
//좌표
class Pos {
	int x; int y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}