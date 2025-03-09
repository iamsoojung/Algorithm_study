import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, answer = Integer.MIN_VALUE;	// 최대 인원
	static int[] schedule, dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());

		schedule = new int[N+1];
		dp = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			
			// schedule 배열에 인원수 저장
			schedule[i] = cnt;
		}
		
		dp[1] = schedule[1];
		
		if (N >= 2) {	// 회의가 2개 이상인 경우에만
			dp[2] = Math.max(schedule[1], schedule[2]);	// 1번 회의, 2번 회의 중 인원수 많은거 저장 (겹치기 때문)
		}
		
		for (int i=3; i<=N; i++) {
			// i-1까지의 최대 인원수 vs (i-2까지의 최대 인원수 + 현재 인원수)
			dp[i] = Math.max(dp[i-1], dp[i-2] + schedule[i]);
		}
		
		System.out.print(dp[N]);
	}
}