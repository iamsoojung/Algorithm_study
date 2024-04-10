import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 출발 : 상근이네 집 (맥주 한박스(20개) 들고)
 * 도착 : 페스티벌
 * 
 * 50미터 가기 전에 한병 마셔야 함
 * 
 * 편의점 만나면 => 빈병 버리고 새병 살 수 있음 
 * but, 박스에 들어있는 맥주는 20개 넘을  수 X
 * => (20으로 채울 수 있음)
 */

public class Main {
	
	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int n;	// 편의점의 개수
	static Node start, end, max;	// 집(출발지), 페스티벌(도착지), 맵 만들기 위한 최대 좌표
	static Node[] gs25;	// 편의점
	static boolean[] visit;	// 편의점 방문 여부
	
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
				// 상근이네 집, 편의점, 페스티벌 좌표
				if (i == 0) {
					start = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				} else if (i == n+1) {
					end = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				} else {
					gs25[i-1] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				}
			}
			
			visit = new boolean[n];
			if (bfs()) {
				sb.append("happy").append("\n");
			} else {
				sb.append("sad").append("\n");
			}
		}
		System.out.print(sb.toString());
	}
	
	// 맥주 20병 있고 한병당 50미터이기 때문에 현재~(어딘가)까지 1000미터 이내여야 이동 가능
	static boolean bfs() {
		Queue<Node> q = new ArrayDeque<>();
		q.add(start);
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			// 현재~출발지 갈 수 있음
			if (getDist(cur, end))	return true;
			
			// 편의점 가보기
			for (int i=0; i<n; i++) {
				if (!visit[i]) {	// 안가본 편의점
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