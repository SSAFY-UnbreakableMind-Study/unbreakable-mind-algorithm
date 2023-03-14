import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	// 간선과 가중치를 나타낼 Tuple 클래스
	static class Tuple implements Comparable<Tuple> {
		int a, b, c;

		Tuple(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public int compareTo(Tuple o) {
			return this.c - o.c;
		}

	}

	// 전역변수들
	static int N, M, ans, parent[];
	static PriorityQueue<Tuple> pq = new PriorityQueue<>();

	// 두 점 union 하기
	static void unionP(int node1, int node2) {
		if (node1 < node2) {
			parent[node1] = node2;
		}
		else {
			parent[node2] = node1;			
		}
	}

	// 주어진 점의 root 노드 찾기
	static int findP(int node) {
		if (parent[node] != node)
			return findP(parent[node]);

		return node;
	}

	public static void main(String[] args) throws Exception {

		//정점 간선 개수
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		parent = IntStream.iterate(0, i -> i + 1).limit(N + 1).toArray();

		//간선 초기화중..
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			pq.add(new Tuple(a, b, c));
		}

		//유니온 파인드 활용
		while (!pq.isEmpty()) {
			int a = pq.peek().a;
			int b = pq.peek().b;
			int c = pq.poll().c;

			int pa = findP(a);
			int pb = findP(b);

			if (pa != pb) {
				ans += c;
				unionP(pa, pb);
			}
		}

		System.out.println(ans);

	}
}