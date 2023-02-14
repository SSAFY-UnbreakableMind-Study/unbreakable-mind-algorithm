package week3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import java.util.StringTokenizer;

public class BOJ_1759_G5_암호만들기 {
	static char[] charRange;
	static int L, C;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken()); // r
		C = Integer.parseInt(st.nextToken()); // n
		charRange = new char[C];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < C; i++) {
			charRange[i] = st.nextToken().charAt(0);
		}

		// sorting
		Arrays.sort(charRange);
		// 순열로 출력
		permutation(new char[L], 0, 0);

		// output
		bw.write(sb.toString());
		// close
		bw.close();
		br.close();
	}

	private static void permutation(char[] output, int cnt, int idx) throws IOException {
		// output 개수 만족하면 재귀 탈출
		if (cnt == L) {
			// 정답에 추가하려면 자음, 모음 수 체크
			if (isValid(output))
				sb.append(String.valueOf(output) + "\n");
			return;
		}
		// 재귀 구현부
		for (int i = idx; i < C; i++) {
			output[cnt] = charRange[i];
			permutation(output, cnt + 1, i + 1);
		}
	}

	// 순열로 만든 문자열이 모음 1개, 자음 2개 조건 성립하는지
	private static boolean isValid(char[] str) {
		int vowelCnt = 0; // 모음 개수
		for (char ch : str) {
			// 문자열에서 하나의 문자가 모음인지 체크
			switch (ch) {
				case 'a':
				case 'e':
				case 'i':
				case 'o':
				case 'u':
					vowelCnt++;
					break;
			}
		}
		if (vowelCnt >= 1 && L - vowelCnt >= 2)
			return true;
		else
			return false;
	}

}
