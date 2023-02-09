import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static boolean star[][];

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

    //재귀 함수
	static void makeStar(int center_x, int center_y, int degree) {
		int boundary = degree / 2;

		//빈공간 찍기
		for (int i = center_x - boundary; i <= center_x + boundary; ++i) {
			for (int j = center_y - boundary; j <= center_y + boundary; ++j) {
				star[i][j] = true;
			}
		}
		
		//기저 조건
		if (boundary == 0)
			return;
		
		//재귀 실행
		for (int i = -1; i < 2; ++i) {
			for (int j = -1; j < 2; ++j) {
				makeStar(center_x + i * degree, center_y + j * degree, degree / 3);
			}
		}
		
	}

	public static void main(String[] args) throws Exception {

		// N 입력받는 부분
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		// 배열 모든공간 *로 세팅
		star = new boolean[N][N];

		//빈공간 찍기
		makeStar(N / 2, N / 2, N / 3);

		//출력하기
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (star[i][j])
					sb.append(" ");
				else
					sb.append("*");
			}
			sb.append("\n");
		}

		System.out.print(sb.toString());
	}
}