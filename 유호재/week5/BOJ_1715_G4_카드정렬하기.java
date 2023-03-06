import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int n = Integer.parseInt(br.readLine());
		Queue<Integer> queue = new PriorityQueue<Integer>(); 
		for(int i = 0; i < n; i++) {
			int t = Integer.parseInt(br.readLine());
			queue.add(t);
		}
		int tempsum;
		int listsum = 0;
		while(queue.size() > 1) {
			tempsum = queue.poll()+queue.poll();
			listsum += tempsum;
			queue.add(tempsum);
		}
		System.out.println(listsum);
	}

}
