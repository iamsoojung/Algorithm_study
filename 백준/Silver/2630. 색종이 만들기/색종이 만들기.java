import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] paper;
	static int white=0, blue=0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		paper = new int[N][N];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		checkPaint(N, 0, 0);
		System.out.print(white + "\n" + blue);
	}
	
	static void checkPaint(int N, int row, int col) {
		
		// 기저조건 : N이 더이상 나누어질 수 없을 때 (==1)
		if (N==1) {
			if (paper[row][col] == 1) {	// 색칠 O
				blue++;
			} else {	// 색칠 X
				white++;
			}
			return;	
		}
		
		// 기저조건 : 사각형이 모두 같은 색일 때
		int sum = 0;
		for (int i=row; i<row+N; i++) {
			for (int j=col; j<col+N; j++) {
				sum += paper[i][j];
			}
		}
		if (sum == N*N) {
			blue++;
			return;
		} else if (sum == 0) {
			white++;
			return;
		}

		// 1 (왼위)
		checkPaint(N/2, row, col);
		
		// 2 (오위)
		checkPaint(N/2, row, col + N/2);
		
		// 3 (왼밑)
		checkPaint(N/2, row + N/2, col);

		// 4 (오밑)
		checkPaint(N/2, row + N/2, col + N/2);

		
	}
}