import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	static int N;
	static int[] dp = new int[1001];	// 1 ~ 1000
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dp[1] = 0;	// 상근 win
		dp[2] = 1;	// 창영 win
		dp[3] = 0;
		
		for (int i=4; i<=N; i++) {
			dp[i] = Math.min(dp[i-1], dp[i-3]) + 1;
		}
		
		if (dp[N] % 2 == 0) {
			System.out.println("SK");
		} else {
			System.out.println("CY");
		}
	}
}