import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, Ans;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {

		// 카드 묶음 개수
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; ++i) {
			pq.add(Integer.parseInt(br.readLine()));
		}

		// 카드 뭉치가 1개가 될때까지 계속 실행
		while (pq.size() != 1) {
			int first = pq.poll();
			int second = pq.poll();

			// 가장 작은 묶음 두개를 빼서 더하기
			int sum = first + second;

			Ans += sum;
			pq.add(sum);

		}

		System.out.println(Ans);

	}

}
 
