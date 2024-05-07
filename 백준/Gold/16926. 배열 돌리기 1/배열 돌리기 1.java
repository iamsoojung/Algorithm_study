import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, R;
	static int[][] map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i=0; i<R; i++) {
			rotate();
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
	
	static void rotate() {
		int xSide = 0, ySide = 0;	// 겉에서부터속까지..알싸~한마늘치킨
		
		while(true) {
			if (xSide >= N/2 || ySide >= M/2)	return;
			
			int tmp = map[xSide][ySide];
			
			// <-
			for (int y=ySide; y<M-ySide-1; y++) {
				map[xSide][y] = map[xSide][y+1];
			}
			 
			// 위로
			for (int x=xSide; x<N-xSide-1; x++) {
				map[x][M-1-ySide] = map[x+1][M-1-ySide];
			}
			
			// ->
			for (int y=M-1-ySide; y>ySide; y--) {
				map[N-1-xSide][y] = map[N-1-xSide][y-1];
			}
			
			// 아래로
			for (int x=N-1-xSide; x>xSide; x--) {
				map[x][ySide] = map[x-1][ySide];
			}
			
			map[xSide+1][ySide] = tmp;
					
			xSide++;
			ySide++;
		}
	}
}
