import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D2_14510_나무높이_유호재{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		int testcase = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= testcase; tc++) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] trees = new int[n];
			int sum = 0;
			int maxtree = 0;
			for(int i = 0; i < n; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				maxtree = maxtree < trees[i] ? trees[i] : maxtree;
			}
			int even = 0;
			int odd = 0;
			for(int i = 0; i < n; i++) {
				int temp = maxtree - trees[i];
				even += temp / 2;
				odd += temp % 2;
			}
			
			if(even > odd) {
				while(Math.abs(even - odd) > 1) {
					even--;
					odd += 2;
				}
			}
			int result = 0;
			if(odd > even) {
				result = odd * 2 -1;
			}else if(even > odd) {
				result = even * 2;
			}else {
				result = odd+even;
			}
			System.out.println("#"+tc+" "+result);
		}
	}
}
