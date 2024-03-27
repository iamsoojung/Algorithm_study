import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        
        dp[1] = 0;
        for (int i=2; i<=N; i++) {
        	dp[i] = dp[i-1] + 1;	// 빼기 1 연산
        	
        	if (i%3 == 0) {	// 나누기 3 연산
        		dp[i] = Math.min(dp[i], dp[i/3] + 1);
        	}
        	if (i%2 == 0) {	// 나누기 2 연산
        		dp[i] = Math.min(dp[i], dp[i/2] + 1);
        	}
        }
        System.out.print(dp[N]);
    }
}