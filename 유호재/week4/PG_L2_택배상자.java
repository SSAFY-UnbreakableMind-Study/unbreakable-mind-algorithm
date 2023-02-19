import java.util.Stack;

public class PG_L2_택배상자 {
	public static void main(String[] args) {
		int[] order = {4, 3, 1, 2, 5};
		int answer = 0;
        // 전달받을 택배 리스트
		Stack<Integer> boxlist = new Stack<>();
        // 보관할 장소
		Stack<Integer> container = new Stack<>();
		for(int i = order.length; i >= 1; i--) {
			boxlist.add(i);
		}
		boolean[] ordercheck = new boolean[order.length];
		int orderindex = 0;
		int temp = 0;
        // 무한루프
		while(true) {
            // 보관함에 물건이 있고 그것을 꺼낼차례인 경우 처리
			if(!container.isEmpty()) {
				if(container.peek() == order[orderindex]) {
					container.pop();
					answer++;
					orderindex++;
				}
			}
            // 받은 상자번호가 처리할 번호인 경우 처리
			if(!boxlist.isEmpty()) {
				if(order[orderindex] == boxlist.peek()) {
					boxlist.pop();
					answer++;
					orderindex++;
					continue;
				}
				container.add(boxlist.pop());
			}
            // 모두 처리했을 경우 종료
			if(orderindex == order.length) break;
            // 더이상 처리가 불가능할경우 종료
			if(boxlist.isEmpty() && temp == container.peek()) break;
			temp = container.peek();	
			
		}
		System.out.println(answer);
	}
}
