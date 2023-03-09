import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	// 봉우리 시작 끝 좌표
	static class Pos implements Comparable<Pos> {
		int x, y;

		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pos o) {
			// TODO Auto-generated method stub
			return y - o.y;
		}
	}

	static int N, startX, startY, prevX, prevY, curX, curY, nonInclude;
	static Stack<Integer> upStack = new Stack<>();
	static Stack<Integer> downStack = new Stack<>();
	static Stack<Pos> sweepingStack = new Stack<>();
	static ArrayList<Pos> peak = new ArrayList<>();

	//봉우리 찾기
	static void findPeak() {
		if (prevY > 0 && curY < 0) {
			if (upStack.isEmpty()) {
				downStack.add(curX);
			} else {
				int upX = upStack.pop();
				if (upX > curX) {
					peak.add(new Pos(curX, upX));
				} else {
					peak.add(new Pos(upX, curX));
				}
			}
		} else if (prevY < 0 && curY > 0) {
			upStack.add(curX);
		}
	}

	public static void main(String[] args) throws Exception {

		//봉우리 개수
		N = Integer.parseInt(br.readLine());

		//맨 처음 시작 점 
		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken());
		startY = Integer.parseInt(st.nextToken());

		prevX = startX;
		prevY = startY;

		//두번째 점부터 마지막 점까지
		for (int i = 1; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			curX = Integer.parseInt(st.nextToken());
			curY = Integer.parseInt(st.nextToken());

			findPeak();

			prevX = curX;
			prevY = curY;
		}

		//마지막 점과 처음 점 비교
		curX = startX;
		curY = startY;

		findPeak();

		if (!downStack.isEmpty()) {
			int upX = upStack.pop();
			int downX = downStack.pop();

			if (upX > downX)
				peak.add(new Pos(downX, upX));
			else
				peak.add(new Pos(upX, downX));
		}

		Collections.sort(peak);

		//답 도출
		for (Pos pos : peak) {
			boolean tk = true;

			while (!sweepingStack.isEmpty()) {
				if (pos.x < sweepingStack.peek().y) {
					sweepingStack.pop();
					tk = false;
				} else {
					break;
				}
			}

			if (tk)
				nonInclude++;
			sweepingStack.add(pos);
		}

		sb.append(sweepingStack.size()).append(" ").append(nonInclude);
		System.out.println(sb);
	}

}
 
