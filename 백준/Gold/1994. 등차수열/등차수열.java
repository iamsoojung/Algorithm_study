import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		for (int i=0; i<N; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(num);
		
		// 공차, 최대길이
		Map<Integer, Integer>[] dp = new HashMap[N];
		for (int i=0; i<N; i++) {
			dp[i] = new HashMap<>();
		}
		
		int maxLen = 1;	// 최대 길이 1
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<i; j++) {
				int diff = num[i] - num[j];		// 공차 diff
				int prevLen = dp[j].getOrDefault(diff, 0);
				int len = (prevLen == 0) ? 2 : prevLen + 1; // 새 등차수열이면 길이 2

				dp[i].put(diff, Math.max(dp[i].getOrDefault(diff, 0), len));
				maxLen = Math.max(maxLen, dp[i].get(diff));
			}
		}
		
		System.out.println(maxLen);
	}
}
