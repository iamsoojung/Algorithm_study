import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		System.out.print(pow(A, B));
	}
	
	static long pow (long A, long exp) {
		
		if (exp == 1) {		// 지수가 1일 경우
			return A % C;
		}
		
		long tmp = pow(A, exp/2);
		
		if (exp % 2 == 1) {
			return (tmp*tmp % C) * A % C;
		}
		return tmp*tmp % C;
	}
}