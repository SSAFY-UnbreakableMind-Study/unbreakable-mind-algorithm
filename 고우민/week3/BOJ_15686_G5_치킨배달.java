import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15686_G5_치킨배달 {

    static int n, m;
    static int[][] map;
    static List<int[]> house = new ArrayList<int[]>();
    static List<int[]> chicken = new ArrayList<int[]>();
    static List<int[][]> combi = new ArrayList<int[][]>();

    static int getCityDist(List<int[]> house, int[][] chicken) {
        int cityDist = 0;
        for (int[] h : house) {
            int dist = Integer.MAX_VALUE;
            for (int[] c : chicken) {
                dist = Math.min(dist, Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]));
            }
            cityDist += dist;
        }
        return cityDist;
    }

    static void setCombi(int i, int j, int[][] arr) {
        if (i == m) {
            int[][] tmp = new int[m][2];
            for (int k = 0; k < m; k++) {
                tmp[k][0] = arr[k][0];
                tmp[k][1] = arr[k][1];
            }
            combi.add(tmp);
            return;
        }
        if (j == chicken.size())
            return;
        arr[i] = chicken.get(j);
        setCombi(i + 1, j + 1, arr);
        setCombi(i, j + 1, arr);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    house.add(new int[] { i, j });
                } else if (map[i][j] == 2) {
                    chicken.add(new int[] { i, j });
                }
            }
        }

        setCombi(0, 0, new int[m][2]);

        int cityDist = Integer.MAX_VALUE;
        for (int[][] c : combi) {
            cityDist = Math.min(cityDist, getCityDist(house, c));
        }

        System.out.println(cityDist);
    }
}