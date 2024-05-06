import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, K, answer=Integer.MAX_VALUE;
	static int[][] map;
	static int[][] rotateInfo;
	
	static boolean[] visit;
	static int[] order;	// 순서

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		rotateInfo = new int[K][3];
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			rotateInfo[i][0] =Integer.parseInt(st.nextToken()) - 1;
			rotateInfo[i][1] =Integer.parseInt(st.nextToken()) - 1;
			rotateInfo[i][2] =Integer.parseInt(st.nextToken());
		}
		
		visit = new boolean[K];
		order = new int[K];
		perm(0);
		
		System.out.print(answer);
	}

	// 1. 회전 순서 정해주기
	static void perm(int cnt) {
		if (cnt == K) {
			rotate();
			return;
		}
		
		for (int i=0; i<K; i++) {
			if (!visit[i]) {
				visit[i] = true;
				order[cnt] = i;
				perm(cnt+1);
				visit[i] = false;
			}
		}
	}

	// 2. 순서 정해지면, 돌리기 (맵 카피해서)
	static void rotate() {
		// 맵 카피
		int[][] tmpMap = new int[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}
		
		for (int k=0; k<K; k++) {
			int r = rotateInfo[order[k]][0];
			int c = rotateInfo[order[k]][1];
			int S = rotateInfo[order[k]][2];
			
			for (int s=1; s<=S; s++) {
				
				int tmp = tmpMap[r-s][c-s];
				
				// 위로	
				for (int x=r-s; x<r+s; x++) {
					tmpMap[x][c-s] = tmpMap[x+1][c-s];
				}

				// 왼쪽으로 <-
				for (int y=c-s; y<c+s; y++) {
					tmpMap[r+s][y] = tmpMap[r+s][y+1];
				}
								
				// 아래로
				for (int x=r+s; x>r-s; x--) {
					tmpMap[x][c+s] = tmpMap[x-1][c+s];
				}

				// 오른쪽으로 ->
				for (int y=c+s; y>c-s; y--) {
					tmpMap[r-s][y] = tmpMap[r-s][y-1];
				}
				
				tmpMap[r-s][c-s+1] = tmp;
			}
		}
		
		// 3. 그 상태로 행 합 구하기 (최솟값 갱신)
		getScore(tmpMap);
		
	}
	
	static void getScore(int[][] tmpMap) {
		for (int i=0; i<N; i++) {
			int rowSum = 0;
			for (int j=0; j<M; j++) {
				rowSum += tmpMap[i][j];
			}
			answer = Math.min(answer, rowSum);
		}
	}
}
