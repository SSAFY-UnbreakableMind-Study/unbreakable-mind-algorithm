//22796KB	276ms
import java.util.Scanner; 

public class BOJ_9251_G5_LCS {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s1 = sc.next();
		String s2 = sc.next();
		
		String[] s1arr = s1.split("");
		String[] s2arr = s2.split("");
		int[][] answergraph = new int[s1arr.length][s2arr.length];
		
		for(int i = 0; i < s1arr.length; i++) {
			int max_value = 0;
			for(int j = 0; j < s2arr.length; j++) {
				if (i == 0) {
					if(s1arr[i].equals(s2arr[j])) {
						answergraph[i][j] = 1;
					}
				}
				else {
					if(j > 0) {
						max_value = Math.max(max_value, answergraph[i-1][j-1]);
					}
					if(s1arr[i].equals(s2arr[j])){
						answergraph[i][j] = max_value+1;
					}
					else {
						answergraph[i][j] = answergraph[i-1][j];
					}
				}
			}
		}
//		for (int x = 0; x < s1arr.length; x++) {
//			for (int y = 0; y < s2arr.length; y++) {
//				System.out.print(answergraph[x][y]);
//			}
//			System.out.println();
//		}
		int answer = 0;
		for(int q = 0; q < s2arr.length; q++) {
			answer = Math.max(answer, answergraph[s1.length()-1][q]);
		}
		System.out.println(answer);
	}
}
