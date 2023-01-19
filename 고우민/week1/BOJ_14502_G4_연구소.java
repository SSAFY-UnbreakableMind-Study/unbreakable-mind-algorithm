package 고우민.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14502_G4_연구소 { 

    static int n, m;
    static int[][] map = new int[8][8];
    static int answer;
    static int[][] temp = new int[8][8];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static boolean isValidPos(int x, int y) {
        if(x<0 || x>=n || y<0 || y>=m) return false;
        return true;
    }

    static void bfs(int x, int y) {
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(isValidPos(nx, ny) && temp[nx][ny] == 0){
                temp[nx][ny] = 2;
                bfs(nx, ny);
            }
        }
    }

    static void check() {
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                temp[i][j] = map[i][j];
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(temp[i][j] == 2){
                    bfs(i, j);
                }
            }
        }

        int cnt = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(temp[i][j] == 0){
                    cnt++;
                }
            }
        }
        answer = Math.max(answer, cnt);
    }

    static void setWall(int cnt) {
        if(cnt == 3) {
            check();
            return;
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j] == 0){
                    map[i][j] = 1;
                    setWall(cnt+1);
                    map[i][j] = 0;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        setWall(0);
        System.out.println(answer);

    }
}
