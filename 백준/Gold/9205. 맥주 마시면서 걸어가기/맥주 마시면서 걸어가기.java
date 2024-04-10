import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int x, y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int n;
	static Node start, end, max;
	static Node[] gs25;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=t; testCase++) {
			n = Integer.parseInt(br.readLine());
			gs25 = new Node[n];
			
			max = new Node(0, 0);
			for (int i=0; i<n+2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				if (i == 0) {
					start = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				} else if (i == n+1) {
					end = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				} else {
					gs25[i-1] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				}
			}
			
			visit = new boolean[n];
			if (bfs())	sb.append("happy").append("\n");
			else	sb.append("sad").append("\n");
		}
		System.out.print(sb.toString());
	}
	
	static boolean bfs() {
		Queue<Node> q = new ArrayDeque<>();
		q.add(start);
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			if (getDist(cur, end))	return true;
			for (int i=0; i<n; i++) {
				if (!visit[i]) {
					Node next = gs25[i];
					if (getDist(cur, next)) {
						visit[i] = true;
						q.add(next);
					}
				}
			}
		}
		return false;
	}
	
	static boolean getDist(Node cur, Node target) {
		int dist = Math.abs(cur.x - target.x) + Math.abs(cur.y - target.y);
		return (dist <= 1000) ? true : false;
	}
}