package week1;

import java.util.Scanner;

public class BOJ_1012_S2_유기농배추 {

	public static void main(String[] args) {
		int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		Scanner sc = new Scanner(System.in);

		int TC = Integer.parseInt(sc.nextLine());

		String[] MNK = sc.nextLine().split(" ");
		int M = Integer.parseInt(MNK[0]);
		int N = Integer.parseInt(MNK[1]);
		int K = Integer.parseInt(MNK[2]);

		// 입력 인풋을 배열에 넣기

		// 하나 꺼내서 count ++

		// 꺼낸 애가 이제 갈 수 있는 애 4방향 탐색해서 배열에서 없애기.

		// 더이상 꺼내기 없으면

		// 다시 배열에서 count++ 하나꺼내기

		// count 출력

	}

}
