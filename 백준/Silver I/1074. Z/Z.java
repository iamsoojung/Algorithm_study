import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, r, c, num=0, answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		N = (int) Math.pow(2, N);
		Z(N, 0, 0);

		System.out.println(answer);
	}
	
	static void Z(int N, int row, int col) {
		
		// 기저조건  : 더이상 나눌 수 없는 경우 (N==1)
		if (N == 1)	return;
		
		int newN = N/2;
		
		if (r < row + newN && c < col + newN) {
			Z(N/2, row, col);			
		} 
		if (r < row + newN && c >= col + newN) {
			answer += (N*N/4);
			Z(N/2, row, col+N/2);		
		}
		if (r >= row + newN && c < col + newN) {
			answer += (N*N/4) * 2;
			Z(N/2, row+N/2, col);
		} 
		if (r >= row + newN && c >= col + newN) {
			answer += (N*N/4) * 3;
			Z(N/2, row+N/2, col+N/2);
		} 
		
	}
}