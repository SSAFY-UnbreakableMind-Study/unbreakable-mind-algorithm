import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int T, N, ans, maxx, even, odd, arr[];

	public static void main(String[] args) throws Exception {

		// 테스트 케이스 개수
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; ++t) {
			N = Integer.parseInt(br.readLine());

			ans = 0;
			maxx = 0;
			odd = 0;
			even = 0;
			arr = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; ++i) {
				arr[i] = Integer.parseInt(st.nextToken());
				maxx = Math.max(maxx, arr[i]);
			}

			for (int i = 0; i < N; ++i) {
				if(maxx % 2 == 0 && arr[i] % 2 != 0) {
					arr[i]++;
					odd++;
				}
				else if(maxx % 2 == 1 && arr[i] % 2 != 1) {
					arr[i]++;
					odd++;
				}
			}
			
			for(int i=0; i<N; ++i) {
				even += (maxx - arr[i]) / 2;
			}

			while(odd + 1 < even) {
				odd +=2;
				even--;
			}
			
			if(odd > even) ans = 2 * odd -1;
			else ans = 2 * even;
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");

		}

		System.out.println(sb);
	}

}
