import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3190_뱀_유호재 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 보드의 크기 n 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[][] graph = new int[n+1][n+1];
		
		// 사과의 개수 k입력
		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		
		// 사과의 정보를 입력받아 보드 업데이트
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			graph[x][y] = 1;
		}
		
		// 뱀의 방향 변환 정보 개수 입력
		st = new StringTokenizer(br.readLine());
		int l = Integer.parseInt(st.nextToken());
		
		// 뱀의 방향 상세 정보 배열에 저장
		String[] dirinfo = new String[10001];
		for(int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			dirinfo[Integer.parseInt(st.nextToken())] = st.nextToken();
		}
		
		int[] dx = {0,1,0,-1};
		int[] dy = {1,0,-1,0};
		int dir = 0;
		int x = 1;
		int y = 1;
		int nx = 0;
		int ny = 0;
		int answer = 0;
		String temp;
		
		Queue<int[]> snake = new LinkedList<int[]>(); 
		snake.add(new int[] {x,y});
		graph[x][y] = 2;
		for(int time = 0; time <= 10000; time++) {
			// 현재 시간에 방향 전환정보 확인
			if(dirinfo[time] != null) {
				temp = dirinfo[time];
				if(temp.equals("D")) {
					dir = (dir +1) % 4;
				} else if(temp.equals("L")) {
					dir = (dir +3)%4;
				}
			}

			// 앞으로의 진행방향을 확인
			nx = x + dx[dir];
			ny = y + dy[dir];
			if(nx <= 0 || ny <= 0 || nx > n || ny > n || graph[nx][ny] == 2) {
				answer = time;
				break;
			}
			x = nx;
			y = ny;
			
			//사과를 만날지 확인
			if(graph[x][y] == 1) {
				graph[x][y] = 2;
				snake.add(new int[] {x,y});
			}else {
				graph[x][y] = 2;
				snake.add(new int[] {x,y});
				int[] out = snake.remove();
				graph[out[0]][out[1]] = 0;
			}
		}
		System.out.println(answer+1);
	}
}