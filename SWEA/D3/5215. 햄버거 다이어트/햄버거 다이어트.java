import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, L, answer;
	static int[][] topping;
	static boolean[] select;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 재료의 수 N
			L = Integer.parseInt(st.nextToken());	// 제한 칼로리 L
			
			topping = new int[N][2];	// 재료 정보 (0:점수, 1:칼로리)
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				topping[i][0] = Integer.parseInt(st.nextToken());
				topping[i][1] = Integer.parseInt(st.nextToken());
			}
			
			select = new boolean[N];
			answer = Integer.MIN_VALUE;
			// 제한 칼로리 이하, 최대 점수
			subset(0, 0, 0);
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}		
		System.out.println(sb.toString());
	}
	
	static void subset(int depth, int idx, int cal) {
		if (cal > L)	return;		// 제한 칼로리 넘은 경우 바로 종료
		
		if (depth == N) {
			answer = Math.max(answer, getScore());
			return;
		}
		
		select[idx] = true;
		subset(depth+1, idx+1, cal + topping[idx][1]);
		select[idx] = false;
		subset(depth+1, idx+1, cal);
	}
	
	static int getScore() {
		int cnt = 0;
		for (int i=0; i<N; i++) {
			if (select[i]) {
				cnt += topping[i][0];
			}
		}
		return cnt;
	}
}