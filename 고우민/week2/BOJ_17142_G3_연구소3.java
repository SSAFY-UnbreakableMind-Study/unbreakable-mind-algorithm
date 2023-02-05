import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pos {
	int x, y;
	Pos(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class BOJ_17142_G3_연구소3 {
    
	static int n, m;
	static int[][] map;
	static int[][] tmp;
	static List<Pos> virus = new ArrayList<>();
	static Queue<Pos> q;
	static boolean[][] visited;
	static boolean[] check;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int room = 0;
	static int ans = Integer.MAX_VALUE;
	
	static boolean oob(int x, int y) {
		if(x<0||x>=n||y<0||y>=n) return true;
		return false;
	}
	
	static void bfs(Stack<Pos> actV) {
		q = new LinkedList<>();
		visited = new boolean[n][n];
		tmp = new int[n][n];
		int cnt = 0;
		int time = 0;
		for(Pos v : actV) {
			q.offer(new Pos(v.x, v.y));
			visited[v.x][v.y] = true;
		}
		while(!q.isEmpty()) {
			Pos now = q.poll();
			for(int i=0;i<4;i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if(oob(nx, ny)) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny] == 1) continue;
				
				visited[nx][ny] = true;
				tmp[nx][ny] = tmp[now.x][now.y]+1;
				time = Math.max(time, tmp[nx][ny]);
				q.offer(new Pos(nx, ny));
				
				if(map[nx][ny] == 0) {
					cnt++;
					if(room == cnt) {
						ans = Math.min(ans, time);
						return;
					}
				}
			}
		}
		
	}
	
	static void dfs(int cnt, Stack<Pos> actV, int idx) {
		if(cnt == m) {
			bfs(actV);
			return;
		}
		
		for(int i=idx;i<virus.size();i++) {
			check[i] = true;
			actV.push(virus.get(i));
			dfs(cnt+1, actV, i+1);
			check[i] = false;
			actV.pop();
		}
	}
	
    public static void main(String[] args) throws IOException {
    	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virus.add(new Pos(i, j));
				}
				if(map[i][j] == 0) {
					room++;
				}
			}
		}
		
		if(room == 0) {
			System.out.println(0);
			return;
		}
		
		check = new boolean[virus.size()];	
		dfs(0, new Stack<Pos>(), 0);
		if(ans == Integer.MAX_VALUE) ans = -1;
		System.out.println(ans);
	}
}