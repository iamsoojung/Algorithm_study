import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K;
	static int[] weight, value;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 물건 N개의 무게 & 가치
		weight = new int[N+1];
		value = new int[N+1];

		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N+1][K+1];	
		
		// 어떤 물건도 담지 않는 경우
		for (int i=0; i<=K; i++) {
			dp[0][i] = 0;
		}
		
		// 배낭 용량이 0인 경우
		for (int i=0; i<=N; i++) {
			dp[i][0] = 0;
		}
		
		for (int i=1; i<=N; i++) {	// 물건 (1~i) 고려
			for (int w=1; w<=K; w++) {	// 물건의 무게 (1~k) 고려
				int wi = weight[i];
				int vi = value[i];
				
				// i번째 물건의 무게 > 현재 배낭 용량
				if (wi > w) {
					dp[i][w] = dp[i-1][w];
				} else {
					dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-wi] + vi);
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
