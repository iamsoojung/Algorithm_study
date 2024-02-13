import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int result = 0;

		while (N>0) {
			if (N%5 == 0) {
				N = (int)N/5;
				result += N;
				System.out.print(result);
				break;
			}
			N -= 3;
			result++;
			
			if (N == 0) {
				System.out.print(result);
			} else if (N < 0) {
				System.out.print(-1);
			}
		}
		
		
	}
}