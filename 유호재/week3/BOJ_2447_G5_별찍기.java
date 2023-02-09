import java.util.Scanner;

public class BJ_2447_별찍기 {
	// 스태틱변수 그래프 선언, n입력받은 후 초기화 예정
	static String[][] graph;
	static StringBuilder sb;
	private static void star(int i, int j, int n) {
		// n이 한칸으로 더이상 나눌 수 없는 경우 그래프를 별로 바꿈
		if (n == 1) {
			graph[i][j] = "*";
			return;
		}
		
		// n이 3보다 큰경우 그래프를 3등분 하고 한부분씩 넘어감 총 9부분으로 나눔
		for(int x = 0; x<3; x++) {
			for(int y = 0; y<3; y++) {
				// 가운데가 빈공간인 경우 연산에 포함시키지 않음(별 찍을일이 없어짐)
				if(!(x==1 && y==1)) {
					star(i+x*n/3, j+y*n/3,n/3);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		graph= new String[n][n];
		// 그래프 변환 시작
		star(0,0,n);
		// 변환된 그래프를 sb변수에 담아줌
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if (graph[i][j] == null) {
					sb.append(" ");
				}else {
					sb.append("*");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());;
	}
	
}
