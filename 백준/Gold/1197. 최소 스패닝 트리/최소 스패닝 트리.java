import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	static int V, E;	// 정점, 간선 수
	static Edge[] edgeList;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edgeList = new Edge[E];
		
		// 간선 리스트 생성
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edgeList);	// 간선 리스트 정렬
		
		// make
		make();
		
		// 각 간선 꺼내어 신장트리 생성
		int cnt = 0;
		long weight = 0;
		for (Edge e : edgeList) {
			if (!union(e.from, e.to))	continue;	// 사이클 발생
			weight += e.weight;
			if (++cnt == V-1)	break;	// MST 완성
		}
		
		System.out.print(weight);
	}
	
	static void make() {
		parents = new int[V+1];
		for (int i=1; i<=V; i++) {
			parents[i] = i;
		}
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot)	return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	static int find(int a) {
		if (parents[a] == a)	return a;
		return parents[a] = find(parents[a]);
	}

}
