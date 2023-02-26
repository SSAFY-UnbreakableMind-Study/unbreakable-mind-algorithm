import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class PG_L2_택배상자 {
	public static int solution(int[] order) {
		int n = order.length;

		Queue<Integer> queue = new ArrayDeque<>(); // 출력할 정답
		Stack<Integer> stack = new Stack<>(); // 임시보관함

		int orderi = 0;
		// 벨트에 총 N개의 상자가 있음
		for (int belt = 1; belt <= n; belt++) {
			// 오더 순서와 벨트의 상자가 일치하면
			if (order[orderi] == belt) {
				queue.offer(belt);
				orderi++;
				continue;
			}
			// 오더 받은 박스랑 현재 벨트의 박스가 다를 경우
			else {
				if (order[orderi] > belt) { // 벨트 뒤편에 아직 원하는 상자가 있는 경우 => 현재 벨트의 상자는 임시 보관함
					stack.push(belt);
					continue;
				} else { // 벨트 뒤에 더 이상 찾는 상자 안오는 경우 => 임시 보관함 맨 앞만 보기
					// 스택 맨 앞에 보관중인 경우
					if ((!stack.isEmpty()) && (order[orderi] == stack.peek())) {
						queue.offer(stack.pop());
						orderi++;
						belt--;
						continue;
					} else
						return queue.size();
				}
			}
		}
		// 벨트에선 다 꺼냈는데 보관함에 남은 상자가 있을 경우
		// 임시 보관함에 오더 상자가 있으면 빼기
		while ((!stack.isEmpty()) && (order[orderi] == stack.peek())) {
			queue.offer(stack.pop());
			orderi++;
		}

		return queue.size();
	}

	public static void main(String[] args) {
		int[] order1 = { 4, 3, 1, 2, 5 };
		int[] order2 = { 5, 4, 3, 2, 1 };
		System.out.println(solution(order1));
		System.out.println(solution(order2));
	}

}
