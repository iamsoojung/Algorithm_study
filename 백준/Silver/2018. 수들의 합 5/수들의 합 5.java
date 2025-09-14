import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int answer = 1;		
		int sum = 1;
		int start = 1, end = 1;		// two pointer
		
		while (start < N) {
			if (sum == N)	answer++;
			
			if (sum < N) {
				end++;
				sum += end;
			} else {
				sum -= start;
				start++;
			}
		}
		
		System.out.println(answer);	
	}
}