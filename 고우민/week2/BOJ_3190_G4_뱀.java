import java.util.*;
import java.io.*;

class Command {
	int time;
	String dir;
	Command(int time, String dir) {
		this.time = time;
		this.dir = dir;
	}
}

class Pos {
	int x, y;
	Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_3190_G4_뱀 {
	
	static int n, k, l;
	static int[] dx = new int[] {0, 1, 0, -1};
	static int[] dy = new int[] {1, 0, -1, 0};
	static int[][] board;
	
	static boolean isValidPos(int x, int y) {
		if(x<0 || x>=n || y<0 || y>=n) {
			return false;
		}
		return true;
	}
	
	static int turn(int d, String dir) {
		if(dir.equals("D")) {
			return (d+1) % 4;
		}
		else {
			if(d == 0) return 3;
			else return (d-1) % 4;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		board = new int[n][n];
		ArrayList<Command> comms = new ArrayList<>();
		Queue<Pos> q = new LinkedList<>();
		
		int a, b;
		for(int i=0;i<k;i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			board[a-1][b-1] = 2;
		}
		
		l = Integer.parseInt(br.readLine());
		int time;
		String dir;
		for(int i=0;i<l;i++) {
			st = new StringTokenizer(br.readLine());
			time = Integer.parseInt(st.nextToken());
			dir = st.nextToken();
			comms.add(new Command(time, dir));
		}
		
		int x = 0;
		int y = 0;
		int d = 0;
		int t = 0;
		board[0][0] = 1;
		q.offer(new Pos(x, y));
		
		while(true) {			
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(!isValidPos(nx, ny)) break;
			if(board[nx][ny] == 1) break;
			
			else if(board[nx][ny] == 0) {
				Pos tail = q.poll();
				board[tail.x][tail.y] = 0;
			}
			q.offer(new Pos(nx, ny));
			board[nx][ny] = 1;
			x = nx;
			y = ny;
			
			t++;
			for(Command com : comms) {
				if(t < com.time) break;
				else if(t == com.time) {
					d = turn(d, com.dir);
				}
			}
		}
		t++;
		System.out.println(t);
	}
}