import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * n가지 종류의 동전으로, 합을 k원 만들기
 * n개의 동전 가치 주어짐
 * 
 * 	1) 1원으로 1원~10원 가짓수 구함 (1,1,1,..)
 * 	2) 2원으로 2원~10원 가짓수 구함 (1,2,2,3,3,4,4...)
 * 	5) 5원으로 5원~10원 가짓수 구함 (1,2,2,3,4,5,6,..)
 * 각 동전 가치부터 인덱스 잡고 돌리면 중복 피하기 가능
 * 
 */

public class Main {
	
	static int N, K;
	static int[] coins;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		coins = new int[N+1];
		for (int i=1; i<N+1; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		int[][] dp = new int[N+1][K+1];
		
		for (int n=1; n<=N; n++) {
			for (int k=1; k<=K; k++) {
				
				if (coins[n] == k) {
					dp[n][k] = dp[n-1][k] + 1;
				} else if (coins[n] > k) {
					dp[n][k] = dp[n-1][k];
				} else {
					dp[n][k] = dp[n][k-coins[n]] + dp[n-1][k];
				}
			}
		}
		
		System.out.println(dp[N][K]);
		
	}
}