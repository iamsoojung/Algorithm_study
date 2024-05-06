import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node>{
		int end, weight;

		public Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	
	static int V, E, K;
	static int[] dist;
	static boolean[] visit;
	static ArrayList<Node>[] list;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());	// 정점의 개수
		E = Integer.parseInt(st.nextToken());	// 간선의 개수
		K = Integer.parseInt(br.readLine());	// 시작 정점의 번호
		
		list = new ArrayList[V+1];
		for (int i=1; i<V+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		int u, v, w;
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			list[u].add(new Node(v, w));
		}
		
		dist = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		visit = new boolean[V+1];
		dijkstra(K);
		
		for (int i=1; i<=V; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				sb.append("INF");
			} else {
				sb.append(dist[i]);
			} sb.append("\n");
		}
		
		System.out.print(sb.toString());
	}
	
	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (visit[cur.end])	continue;
			visit[cur.end] = true;
			
			for (Node next : list[cur.end]) {
				if (dist[next.end] > dist[cur.end] + next.weight) {
					dist[next.end] = dist[cur.end] + next.weight;
					pq.add(new Node(next.end, dist[next.end]));
				}
			}
		}
	}
}
