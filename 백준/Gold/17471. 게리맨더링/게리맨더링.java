import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int num1, num2, sum1, sum2;
	static int N, answer;		// 구역의 개수
	static int[] person;	// 각 구역의 인구 수
	static boolean[] visit;		
	static ArrayList<Integer>[] arr;	// 구역 인접 리스트
	static boolean[] areaCheck;	// 어느 선거구로 나누어졌는지 체크

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		person = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<N+1; i++) {
			person[i] = Integer.parseInt(st.nextToken());
		}
		
		arr = new ArrayList[N+1];
		for (int i=0; i<N+1; i++) {
			arr[i] = new ArrayList<>();
		}
		int size = 0;
		for (int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			size = Integer.parseInt(st.nextToken());
			for (int j=0; j<size; j++) {
				arr[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		areaCheck = new boolean[N+1];
		answer = Integer.MAX_VALUE;
		dfs(1);	// 1번 구역부터
	
//		for (int i=0; i<N+1; i++) {
//			System.out.println(arr[i].toString());
//		}
		
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);			
		}
	}
	
	// 선거구 2개로 나누기
	static void dfs(int cnt) {
		if (cnt == N) {
			num1=0; num2=0;	// 두 선거구에 포함된 구역 수
			sum1=0; sum2=0;	// 두 선거구에 포함된 인구 수
			for (int i=1; i<N+1; i++) {
				if (areaCheck[i]) {
					num1++;
					sum1 += person[i];
				}
				else {
					num2++;
					sum2 += person[i];
				}
			}
			if (num1 == 0 || num2 == 0)	return;	// 구역을 적어도 하나 포함해야 함

			visit = new boolean[N+1];
//			for (int i=1; i<N+1; i++) {
//				System.out.print(visit[i] == true ? 'T' : 'F');
//			}System.out.println();
			
			// 구역 인접되어있는지 확인
			int result = 0;
			for (int i=1; i<N+1; i++) {
				if (!visit[i]) {
					checkConnect(i, areaCheck[i]);
					result++;
				}
			}
			
			if (result == 2) {
				answer = Math.min(answer, Math.abs(sum1 - sum2));
			}
			
			return;
		}
		
		areaCheck[cnt] = true;
		dfs(cnt+1);
		areaCheck[cnt] = false;
		dfs(cnt+1);
	}
	
	static void checkConnect(int start, boolean check) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		visit[start] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for (int next : arr[cur]) {
				if (areaCheck[next] == check && !visit[next]) {
					q.add(next);
					visit[next] = true;
				}
			}
		}
	}
}
