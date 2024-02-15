import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static StringTokenizer st;
	static int N;
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		for (int i=0; i<N; i++) {
			String s = br.readLine();
			for (int j=0; j<N; j++) {
				arr[i][j] = (int)s.charAt(j) - '0';
			}
		}
		
		quadTree(N, 0, 0);
		System.out.println(sb);
	}
	
	static void quadTree(int N, int row, int col) {
		
		// 더이상 나눠지지 않는 경우
		if (N == 1) {
			sb.append(arr[row][col] + "");
			return;
		}
		
		// 압축가능한 경우
		int sum = 0;
		for (int i=row; i<row+N; i++) {
			for (int j=col; j<col+N; j++) {
				sum += arr[i][j];
			}
		}
		if (sum == 0) {
			sb.append(0 + "");
			return;
		} else if (sum == N*N) {
			sb.append(1 + "");
			return;
		}
		
		sb.append("(");
		quadTree(N/2, row, col);
		quadTree(N/2, row, col + N/2);
		quadTree(N/2, row + N/2, col);
		quadTree(N/2, row + N/2, col + N/2);
		sb.append(")");
		
	}
}