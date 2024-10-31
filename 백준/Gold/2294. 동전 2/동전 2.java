import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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
		
		int[] dp = new int[K+1];
		Arrays.fill(dp, 10001);
		dp[0] = 0;
		
		for (int n=1; n<=N; n++) {
			for (int k=coins[n]; k<=K; k++) {
				dp[k] = Math.min(dp[k-coins[n]]+1, dp[k]);
			}
		}
		
		System.out.println(dp[K] == 10001 ? -1 : dp[K]);
	}
}