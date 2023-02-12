import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int nxt, cost;
    boolean check;

    public Edge(int nxt, int cost) {
        this.nxt = nxt;
        this.cost = cost;
    }
    @Override
    public int compareTo(Edge o) {
        return cost - o.cost;
    }

}

public class BOJ_5719_P5_거의최단경로 {

    static int n, m, s, d, u, v, p;
    static ArrayList<Edge>[] graph;
    static PriorityQueue<Edge> q;
    static ArrayList<Integer>[] parent;
    static boolean[][] visited;

    static void findPath(int end) {
    	if(end == s) return; 
        for(int p : parent[end]) {
        	if(visited[p][end]) continue;
        	visited[p][end] = true;
        	findPath(p);
        }

    }

    static void dijkstra(int stt, int end) {

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[stt] = 0;

        q = new PriorityQueue<>();
        q.offer(new Edge(stt, 0));

        while(!q.isEmpty()) {
            Edge edge = q.poll();
            int now = edge.nxt;
            int cost = edge.cost;

            if(dist[now] < cost) continue;

            for(Edge e : graph[now]) {
                int nxt = e.nxt;
                int weight = e.cost;

                if(dist[nxt] == cost+weight) {
                	parent[nxt].add(now);
                }
                else if(dist[nxt] > cost+weight) {
                    dist[nxt] = cost+weight;
                    parent[nxt].clear();
                    parent[nxt].add(now);
                    q.offer(new Edge(nxt, dist[nxt]));
                }
            }
        }

    }

    static int dijkstra2(int stt, int end) {

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[stt] = 0;

        q = new PriorityQueue<>();

        q.offer(new Edge(stt, 0));
    
	    while(!q.isEmpty()) {
	        Edge edge = q.poll();
	        int now = edge.nxt;
	        int cost = edge.cost;
	        
	        if(dist[now] < cost) continue;
	        
	        for(Edge e : graph[now]) {
	            int nxt = e.nxt;
	            int weight = e.cost;
	            
	            if(visited[now][nxt]) continue;
	            
	            if(dist[nxt] > cost+weight) {
	                dist[nxt] = cost+weight;
	                q.offer(new Edge(nxt, dist[nxt]));
	            }
	        }
	    }
	    return dist[end];
    }

	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    while(true) {
	        st = new StringTokenizer(br.readLine());
	        n = Integer.parseInt(st.nextToken());
	        m = Integer.parseInt(st.nextToken());
	        if(n==0 && m==0) break;
	        
	        st = new StringTokenizer(br.readLine());
	        s = Integer.parseInt(st.nextToken());
	        d = Integer.parseInt(st.nextToken());
	        
	        graph = new ArrayList[n];
	        parent = new ArrayList[n];
	        visited = new boolean[n][n];
	        
	        for(int i=0; i<n; i++) {
	            graph[i] = new ArrayList<>();
	            parent[i] = new ArrayList<>();
	        }
	        
	        for(int i=0; i<m; i++) {
	            st = new StringTokenizer(br.readLine());
	            u = Integer.parseInt(st.nextToken());
	            v = Integer.parseInt(st.nextToken());
	            p = Integer.parseInt(st.nextToken());
	            
	            graph[u].add(new Edge(v, p));
	        }
	        
	        dijkstra(s, d);
	        findPath(d);
	        
	        int answer = dijkstra2(s, d);
	        if(answer == Integer.MAX_VALUE) answer = -1;
	        System.out.println(answer);
	    }
	    
	}
}