import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int end, weight;
	
	Node(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
	
	// 비용 오름차순 정렬
	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}
}

public class Main {
	static int N, M;
	static ArrayList<ArrayList<Node>> graph;
	static boolean[] visit;
	static int start, end, answer;
	static int[] hist;	// 경로에 포함된 도시 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 도시의 개수
		M = Integer.parseInt(br.readLine());	// 버스의 개수
		
		graph = new ArrayList<>();
		
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		StringTokenizer st;
		// 버스의 정보 (출발 도시, 도착 도시, 비용)
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(s).add(new Node(e, w));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		answer = search(start, end, graph, N);	// 최단 거리 탐색
		System.out.println(answer);
		br.close();
	}
	
	static int search(int start, int end, ArrayList<ArrayList<Node>> graph, int size) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		visit = new boolean[size+1];
		
		pq.add(new Node(start, 0));	// 시작 도시 저장
		
		// 최단 거리 탐색
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (cur.end == end) {	// 도착지 도착
				return cur.weight;
			}
			
			visit[cur.end]= true;	// 현재 도시 방문 처리
			for (Node next : graph.get(cur.end)) {
				if (!visit[next.end]) {		// 방문하지 않은 도시인 경우
					pq.add(new Node(next.end, cur.weight + next.weight));
				}
			}
		}
		return -1;	// 도달 못함
	}
}
