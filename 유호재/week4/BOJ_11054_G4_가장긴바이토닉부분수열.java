import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11054_가장긴바이토닉부분수열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[3][n+2];
		arr[0][0] = 0;
		arr[1][0] = 0;
		arr[2][0] = 0;
		int answer = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <=n; i++) {
			arr[0][i] = Integer.parseInt(st.nextToken());
			int max = 0;
			for(int j = i-1; j >= 0; j--) {
				if(arr[0][j] < arr[0][i]) {
					max = Math.max(max, arr[1][j]);
				}
			}
			arr[1][i] = max+1;
		}
		
		for(int i = n; i >= 1; i--) {
			int max = 0;
			for(int j = i+1; j <= n; j++) {
				if(arr[0][i] > arr[0][j]) {
					max = Math.max(max, arr[2][j]);
				}
			}
			arr[2][i] = max+1;
			answer = Math.max(answer, arr[1][i]+arr[2][i]);
		}
		
		for(int i = 0; i <= n+1; i++) {
			System.out.print(arr[0][i]+" ");
		}System.out.println();

		for(int i = 0; i <= n+1; i++) {
			System.out.print(arr[1][i]+" ");
		}System.out.println();
		
		for(int i = 0; i <= n+1; i++) {
			System.out.print(arr[2][i]+" ");
		}System.out.println();
		
		System.out.println(answer);
	}

}