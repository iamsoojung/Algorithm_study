import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int x1, x2, y1, y2;
	public static int[][] num;	// 숫자 배열
	public static int[][] numSum;	// 누적합 배열
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		num = new int[N][N];
		numSum = new int[N+1][N+1];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				num[i][j] = Integer.parseInt(st.nextToken());
				numSum[i+1][j+1] = numSum[i][j+1] + numSum[i+1][j] - numSum[i][j] + num[i][j];
			}
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
			int sum = numSum[x2][y2] - numSum[x1-1][y2] - numSum[x2][y1-1] + numSum[x1-1][y1-1];
			
//			for (int r1=0; r1<x2; r1++) {	// 큰 사각형 (x2, y2)
//				for (int r2=0; r2<y2; r2++) {
//					sum += num[r1][r2];
//				}
//			}
//			for (int r1=0; r1<x1-1; r1++) {	// 가로 (x1)
//				for (int r2=0; r2<y2; r2++) {
//					sum -= num[r1][r2];
//				}
//			}
//			for (int r1=0; r1<y1-1; r1++) {	// 세로 (y1)
//				for (int r2=0; r2<x2; r2++) {
//					sum -= num[r2][r1];
//				}
//			}
//			for (int r1=0; r1<x1-1; r1++) {	// 겹쳐진 부분 두번 빼져서 한번 더하기
//				for (int r2=0; r2<y1-1; r2++) {
//					sum += num[r1][r2];
//				}
//			}
			
			System.out.println(sum);
		}
	}
}

