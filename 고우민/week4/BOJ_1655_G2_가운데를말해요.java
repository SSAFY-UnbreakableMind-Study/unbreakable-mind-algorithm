import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1655_G2_가운데를말해요 {
	
	static PriorityQueue<Integer> minPq = new PriorityQueue<>();
	static PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++) {
			int a = Integer.parseInt(br.readLine());
			if(i==0) {
				minPq.add(a);
				sb.append(a+"\n");
				continue;
			}
			if(i%2==0) {
				minPq.add(a);
				if(minPq.peek() < maxPq.peek()) {
					int p1 = minPq.poll();
					int p2 = maxPq.poll();
					minPq.add(p2);
					maxPq.add(p1);
					sb.append(p2+"\n");
				}
				else {
					sb.append(minPq.peek()+"\n");
				}
			}
			else {
				maxPq.add(a);
				if(minPq.peek() < maxPq.peek()) {
					int p1 = minPq.poll();
					int p2 = maxPq.poll();
					minPq.add(p2);
					maxPq.add(p1);
					sb.append(p1+"\n");
				}
				else {
					sb.append(maxPq.peek()+"\n");
				}
					
			}
			
			
		}
		
		bw.write(sb.toString());
		
		br.close();
		bw.flush();
		bw.close();
	}

}
