import java.util.Stack;

class Solution {
    
	static Stack<Integer> stack = new Stack<>();
	
    public int solution(int[] order) {
        int answer = 0;
        int size = order.length;
        int now = 0;
        int i = 1;
        while(i<=size) {
        	if(i != order[now]) {
        		if(!stack.isEmpty() && order[now] == stack.peek()) {
        			stack.pop();
        			answer++;
        			now++;
        			continue;
        		}
        		stack.push(i);
                i++;
        	}
        	else {
                i++;
        		now++;
        		answer++;
        	}
        }
        while(!stack.isEmpty()) {
        	if(order[now] != stack.pop()) {
        		break;
        	}
        	now++;
        	answer++;
        }
        return answer;
    }
}