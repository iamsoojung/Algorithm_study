import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 정점의 갯수
		int M = Integer.parseInt(st.nextToken());	// 간선의 갯수
		
		ArrayList<Integer>[] list = new ArrayList[N+1];	
		for (int i=0; i<N+1; i++) {
			list[i] = new ArrayList<Integer>();
		}

		int[] inDegree = new int[N+1];
		int x, y;
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			list[x].add(y);
			inDegree[y]++;		// 진입차수 관리 (y로 진입하고 있다!)
		}
		
		// 구현 (위상정렬)
		// 0. 진입차수 관리하기
		ArrayList<Integer> res = new ArrayList<>();
		// 1. 진입차수가 0인 애들은 모두 큐에 삽입한다.
		Queue<Integer> q = new ArrayDeque<>();
		for (int i=1; i<N+1; i++) {
			if (inDegree[i] == 0) {
				q.offer(i);		// 정점 번호 삽입
			}
		}
		// 2-1. q.size()가 0이면 실패
		// 2-2. 큐가 공백 상태가 될 때까지, 임의의 정점을 얻어와서 리스트에 넣고,
		//		나와 연결된 모든 정점의 진입차수를 1씩 감소하고, 그 정점이 0이면 큐에 삽입하기
		int cnt = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();		// 현재 정점
			res.add(cur);	// 리스트에 넣고
			cnt++;
			
			for (int idx : list[cur]) {	// 나와 연결된 모든 정점
				inDegree[idx]--;	// 진입차수 1씩 감소
				if (inDegree[idx] == 0) {	// 그 정점이 0이면
					q.offer(idx);	// 큐에 삽입
				}
			}
		}
		
		// res.size()가 N이 아니면 위상정렬 실패
		// = cnt가 N이 아니면 위상정렬 실패
		
		// 출력
		for (Integer i : res) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
