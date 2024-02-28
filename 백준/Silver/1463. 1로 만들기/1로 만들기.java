import java.util.Scanner;

public class Main {
	static int N; //입력받은 수
	static int res = Integer.MAX_VALUE;
	static int[] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dp = new int[N + 1];
		
		dp[1] = 0; // 기저값 입력

		for(int i = 2; i <= N; i++) {
			
			//1을 뺀다.
			dp[i] = dp[i-1] + 1;
			//X가 3으로 나누어 떨어지면, 3으로 나눈다.
			if(i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i/3]+1); 
			}
			//X가 2로 나누어 떨어지면, 2로 나눈다.
			if(i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i/2]+1); 
			}
		}

		System.out.println(dp[N]);
	}
}
 