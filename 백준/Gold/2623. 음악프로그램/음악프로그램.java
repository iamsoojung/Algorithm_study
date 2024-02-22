import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static ArrayList<Integer>[] list;
	static int[] inDegree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 가수의 수
		M = Integer.parseInt(st.nextToken());	// 보조 PD의 수
		
		list = new ArrayList[N+1];
		for (int i=0; i<N+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		inDegree = new int[N+1];
		// 보조 PD가 정한 순서들 입력 (1. 담당한 가수의 수 / 2. 해당 가수들의 순서)
		int[] tmp;	// 해당 가수들의 순서(2) 저장할 임시 배열
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());	// 담당한 가수의 수(1)
			
			tmp = new int[n];
			for (int j=0; j<n; j++) {
				tmp[j] = Integer.parseInt(st.nextToken());
			}
			
			for(int j=1; j<n; j++) {
				list[tmp[j-1]].add(tmp[j]);
				inDegree[tmp[j]]++;	// 진입 차수 관리
			}
		}
		
		// 위상 정렬 start
		ArrayList<Integer> answer = new ArrayList<>();
		Queue<Integer> q = new ArrayDeque<>();
		
		for (int i=1; i<N+1; i++) {
			if (inDegree[i] == 0) {		// 진입 차수 0이라면
				q.offer(i);		// 큐에 정점 삽입
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();	// 현재 정점
			answer.add(cur);
			
			for (int i : list[cur]) {	// 나랑 연결된 모든 정점
				inDegree[i]--;	// 진입 차수 1 감소
				if (inDegree[i] == 0) {		// 진입 차수 0이라면
					q.offer(i);		// 큐에 정점 삽입
				}
			}
		}
		if (answer.size() != N) {
			sb.append(0);
		} else {
			for (int a : answer) {
				sb.append(a).append("\n");
			}
		}
		
		System.out.print(sb.toString());
		br.close();
	}
}
