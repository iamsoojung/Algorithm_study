import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {	
	
	static int N;
	static int min = Integer.MAX_VALUE;
	static int[] people;
	static ArrayList<Integer>[] adjList;
	static boolean[] visit;
	static int[] part;	// 선거구 (0 or 1)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());	// 구역의 개수
		
		people = new int[N+1];	// 구역의 인구수
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i=1; i<=N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		
		adjList = new ArrayList[N+1];
		for (int i=0; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int cnt = Integer.parseInt(st.nextToken());
			for (int j=0; j<cnt; j++) {
				adjList[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		part = new int[N+1];	// 나눠진 구역번호 저장
		dfs(1);
		
		if (min == Integer.MAX_VALUE) {
			System.out.print(-1);
		} else {
			System.out.print(min);
		}	
	}
	
	static void dfs(int depth) {
		if (depth == N+1) {
			int sum1 =0, sum0 = 0;
			for (int i=1; i<N+1; i++) {
				if (part[i] == 1) {
					sum1 += people[i];
				} else {
					sum0 += people[i];
				}
			}
			
			visit = new boolean[N+1];
			int cnt=0;	// 결과적으로 나온 구역 수 (2가 나와야 두 선거구로 나눠진 것)
			for (int i=1; i<N+1; i++) {
				if (!visit[i]) {
					bfs(i, part[i]);
					cnt++;
				}
			}
			if (cnt == 2) {
				min = Math.min(min, Math.abs(sum1-sum0));
			}
			return;
		}
		
		// 1선거구 or 0선거구 => 2가지 경우
		part[depth] = 1;
		dfs(depth+1);
		
		part[depth] = 0;
		dfs(depth+1);
	}
	
	static void bfs(int idx, int partNum) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(idx);
		visit[idx] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for (int next : adjList[cur]) {
				if (part[next] == partNum && !visit[next]) {
					q.offer(next);
					visit[next] = true;
				}
			}
		}
	}

}