import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, R;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 배열 크기 N,M
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());	// 연산 수 R
		
		map = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<R; i++) {
			int num = Integer.parseInt(st.nextToken());
			rotate(num);
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
	}
	
	static void rotate(int order) {
		switch(order) {
		case 1:	upDown();
			break;
		case 2: leftRight();
			break;
		case 3: right90();
			break;
		case 4: left90();
			break;
		case 5:	partRight90();
			break;
		case 6: partLeft90();
			break;
		}		
	}
	
	static void upDown() {
		for (int j=0; j<M; j++) {
			for (int i=0; i<N/2; i++) {
				int tmp = map[i][j];
				map[i][j] = map[N-i-1][j];
				map[N-i-1][j] = tmp;
			}
		}
	}
	
	static void leftRight() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M/2; j++) {
				int tmp = map[i][j];
				map[i][j] = map[i][M-j-1];
				map[i][M-j-1] = tmp;
			}
		}
	}
	
	static void right90() {
		Queue<Integer> q = new ArrayDeque<>();
		
		int newN = M;
		int newM = N;
		int[][] tmpMap = new int[newN][newM];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				q.add(map[i][j]);
			}
		}
		
		for(int j=N-1; j>=0; j--) {
			for (int i=0; i<M; i++) {
				tmpMap[i][j] = q.poll();
			}
		}
		
		N = newN;
		M = newM;
		map = new int[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				map[i][j] = tmpMap[i][j];
			}
		}
	}
	
	static void left90() {
		Queue<Integer> q = new ArrayDeque<>();
		
		int newN = M;
		int newM = N;
		
		int[][] tmpMap = new int[newN][newM];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				q.add(map[i][j]);
			}
		}
		
		for (int j=0; j<N; j++) {
			for (int i=M-1; i>=0; i--) {
				tmpMap[i][j] = q.poll();
			}
		}

		N = newN;
		M = newM;
		map = new int[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				map[i][j] = tmpMap[i][j];
			}
		}
	}
	
	static void partRight90() {
		int[][] tmpMap = new int[N][M];
		int n = N/2;
		int m = M/2;
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				tmpMap[i][j+m] = map[i][j];
			}
		}
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				tmpMap[i+n][j+m] = map[i][j+m];
			}
		}
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				tmpMap[i+n][j] = map[i+n][j+m];
			}
		}
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				tmpMap[i][j] = map[i+n][j];
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				map[i][j] = tmpMap[i][j];
			}
		}
	}
	
	static void partLeft90() {
		int[][] tmpMap = new int[N][M];
		int n = N/2;
		int m = M/2;
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				tmpMap[i+n][j+m] = map[i+n][j];
			}
		}
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				tmpMap[i][j+m] = map[i+n][j+m];
			}
		}
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				tmpMap[i+n][j] = map[i][j];
			}
		}
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				tmpMap[i][j] = map[i][j+m];
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				map[i][j] = tmpMap[i][j];
			}
		}
	}
}
