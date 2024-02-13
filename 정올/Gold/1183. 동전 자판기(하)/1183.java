import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 1183 {
	
	static int sum=0;
	static int[] nums = {500, 100, 50, 10, 5, 1};	// 동전 크기
	static int[] coins = new int[6];	// 동전 수
	static int[] result = new int[6];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int W = Integer.parseInt(br.readLine());	// 자판기에서 구입하려는 물건의 값
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i=0; i<6; i++) {
			coins[i] = Integer.parseInt(st.nextToken());
			sum += coins[i] * nums[i];
		}
		sum -= W;
		
		for (int i=0; i<6; i++) {
			for (int j=coins[i]; j>=0; j--) {
				if (sum >= j*nums[i]) {
					sum -= j*nums[i];
					result[i] = coins[i] - j;
					break;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(Arrays.stream(result).sum() + "\n");
		for (int i=0; i<6; i++) {
			sb.append(result[i]);
			if (i != 5) {
				sb.append(" ");
			}
		}
		
		System.out.print(sb);
	}
}
