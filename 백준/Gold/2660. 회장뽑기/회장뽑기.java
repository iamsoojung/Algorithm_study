import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int v, cnt;

		public Node(int v, int cnt) {
			this.v = v;
			this.cnt = cnt;
		}
	}

	static int N, answer = Integer.MAX_VALUE;
	static ArrayList<Integer>[] list;
	static boolean[] visit;
	static int[] res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		list = new ArrayList[N+1];
		for (int i=0; i<N+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		int A, B;
		while(true) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			if (A == -1 && B == -1)	break;
			
			list[A].add(B);
			list[B].add(A);
		}
		
//		for (int i=0; i<N+1; i++) {
//			System.out.println(list[i].toString());
//		}
		
		res = new int[N];
		for (int i=1; i<=N; i++) {
			int result = bfs(i);
			res[i-1] = result;
			answer = Math.min(answer, result);
		}
		
		int cnt = 0;
		for (int i=0; i<N; i++) {
			if (answer == res[i]) {
				cnt++;
			}
		}
		sb.append(answer).append(" ").append(cnt).append("\n");
		
		for (int i=0; i<N; i++) {
			if (answer == res[i]) {
				sb.append(i+1).append(" ");
			}
		}
		System.out.println(sb.toString());
	}
	
	// 어느 회원이 다른 모든 회원과 친구 -> 1점
	// 다른 모든 회원이 친구이거나 친구의 친구 -> 2점
	// 다른 모든 회원이 친구이거나, 친구의 친구이거나, 친구의 친구의 친구 -> 3점
	
/*	[2]
	[1, 3, 4]
	[2, 4, 5]
	[3, 5, 2]
	[4, 3]
*/	
	static int bfs(int v) {
		int result = 0;
		visit = new boolean[N+1];
		
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(v, 0));
		visit[v] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for (int i=0; i<list[cur.v].size(); i++) {
				if (!visit[list[cur.v].get(i)]) {
					q.add(new Node(list[cur.v].get(i), cur.cnt+1));
					visit[list[cur.v].get(i)] = true;
				}
			}
			result = cur.cnt;
		}
		return result;
	}
}
