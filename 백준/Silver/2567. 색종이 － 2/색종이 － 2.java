import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static int N, x, y, result = 0;
	static int[][] paper = new int[100][100];
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			for (int j=x; j<x+10; j++) {
				for (int k=y; k<y+10; k++) {
					paper[j][k] = 1;
				}
			}
		}
		
		for (int i=0; i<100; i++) {
			for (int j=0; j<100; j++) {

				if (paper[i][j] == 0)	continue;
				
				for (int d=0; d<4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if (0<= nx && nx <100 && 0<= ny && ny < 100 && paper[nx][ny] == 0) {
						result++;
					}
					
					// 왼오위아 도화지 경계선인경우
					if (0 > nx || 0> ny || nx >= 100 || ny >= 100) {
						result++;
					}
				}
			}
		}
		System.out.println(result);
	}
}
