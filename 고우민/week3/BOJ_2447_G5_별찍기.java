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

        //input
        int N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        //solving
        /**

        * 시작 크기는 N
        * 시작할 때 나를 호출한 더 큰 사각형은 없으니까 내 시작 인덱스는 (0, 0)
        */
        stamp(N, 0, 0);    
        
        //output
        for (int i=0; i<N; i++) {
            bw.write(String.copyValueOf(map[i])+"\n");
        }
        bw.close();
        br.close();
    }

    /**
     * 나를 전체 n*n 정사각형으로 보고, 9등분을 해서 작은 부분을 해결
     * @param n : 현재의 가로 길이 (시작은 N),
     * 
     * @param x : 내가 호출된 인덱스 (나를 호출한 이전의 큰 정사각형에서 내가 몇번째 였는지)
     * @param y
     * 
     */
    private static void stamp(int n, int x, int y) {    //xy는 큰변의 시작인덱스
        //n*n크기의 공간을 3*3 등분해서 divide
        int partSize = n/3;
        if (n == 1) return;
        
        //작은 공간 (9개 중 한 덩어리)
        for (int k=0; k<9; k++) {
            //j, i는 내가 9등분한 작은 블록들의 인덱스
            int j =k%3;    //k=5 인데, 2
            int i =k/3;    //1
            //작은 블럭 9개 중 가운데 (1,1)은 따로 재귀 안하고 공백 찍기
            if (k==4) {
                stampWhite(partSize, x, y);
                continue;
            } 
            // 가운데 아닐 땐,
            // 9등분한 작은 사각형이 별을 찍어야 할 최소 크기이면
            // *찍고 아니면 더 작은 재귀 호출 하면서 나를 계속 작게 분할
            if (n==3) {
                map[x+i][y+j] = '*';
            } else { 
                stamp(partSize, x+partSize*j, y+partSize*i);
            }
                
        }
    }
    //공백 찍기
    private static void stampWhite(int n, int x, int y) {
        // 작은 블록 9개 중에 공백은 항상 (1,1)이니까 (n*i)에서 i가 생략되어 내 인덱스가 (n,n),
        // 이전의 블록에서 나를 호출했던 내 시작 위치가 (x,y)
        for (int i=n+y; i<n+y+n; i++) {
            for (int j=n+x; j<n+x+n; j++) {
                map[i][j] = ' ';
            }
        }
    }
} 