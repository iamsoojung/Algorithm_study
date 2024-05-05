import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Main {
	
	static class Town implements Comparable<Town>{
		int end, weight;

		public Town(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Town o) {
			return this.weight - o.weight;
		}
	}
	
	static int N, M, X, answer=0;
	static ArrayList<Town>[] goList, backList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// N명의 학생
		M = Integer.parseInt(st.nextToken());	// M개의 도로
		X = Integer.parseInt(st.nextToken());	// X번 마을에서 파티
		
		goList = new ArrayList[N+1];
		backList = new ArrayList[N+1];
		for (int i=0; i<N+1; i++) {
			goList[i] = new ArrayList<>();
			backList[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			goList[start].add(new Town(end, weight));
			backList[end].add(new Town(start, weight));
		}
		
		int[] goDist = dijkstra(goList);
		int[] backDist = dijkstra(backList);
		
		for (int i=1; i<=N; i++) {
			answer = Math.max(answer, goDist[i] + backDist[i]);
		}
		System.out.print(answer);
	}
	
	static int[] dijkstra(ArrayList<Town>[] list) {
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<Town> pq = new PriorityQueue<>();
		pq.add(new Town(X, 0));
		
		while(!pq.isEmpty()) {
			Town cur = pq.poll();
			
			if (dist[cur.end] < cur.weight)	continue;
			
			dist[cur.end] = cur.weight;
			for (Town next : list[cur.end]) {
				pq.add(new Town(next.end, cur.weight + next.weight));
			}
		}
		return dist;
	}
}
