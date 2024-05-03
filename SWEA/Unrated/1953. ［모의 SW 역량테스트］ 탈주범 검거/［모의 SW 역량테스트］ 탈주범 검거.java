import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, M, R, C, L, answer;
	static int[][] map;
	static boolean[][] visit;
	static Queue<Node> q;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	
	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 세로 크기
			M = Integer.parseInt(st.nextToken());	// 가로 크기
			R = Integer.parseInt(st.nextToken());	// 맨홀 뚜껑의 세로 (시작점)
			C = Integer.parseInt(st.nextToken());	// 맨홀 뚜껑의 가로
			L = Integer.parseInt(st.nextToken());	// 탈출 후 소요된 시간
			
			map = new int[N][M];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = 0;
			visit = new boolean[N][M];
			q = new ArrayDeque<>();
			q.add(new Node(R, C));
			visit[R][C] = true;
			for (int i=0; i<L; i++) {
				bfs(R, C);
			}
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	static void bfs(int r, int C) {
		int size = q.size();
		for (int i=0; i<size; i++) {
			Node cur = q.poll();
			int cx = cur.x;
			int cy = cur.y;
			answer++;
			
			go(cx, cy, map[cx][cy]);
		}
	}
	
	// 2 -> 0, 2 / 3 -> 1, 3
	// 4 -> 0, 1 / 5 -> 1, 2 / 6 -> 2, 3 / 7 -> 0, 3
	static void go(int cx, int cy, int cmap) {
		switch(cmap) {
		case 1:
			for (int d=0; d<4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (!inRange(nx, ny))	continue;
				if (visit[nx][ny])	continue;
				if (map[nx][ny] == 0) continue;
				if(!check(d, map[nx][ny])) continue;

				q.add(new Node(nx, ny));
				visit[nx][ny] = true;
			}
			break;
		case 2:
			for (int d=0; d<4; d++) {
				if (d!=0 && d!=2)	continue;
				
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (!inRange(nx, ny))	continue;
				if (visit[nx][ny])	continue;
				if (map[nx][ny] == 0) continue;
				if(!check(d, map[nx][ny])) continue;

				q.add(new Node(nx, ny));
				visit[nx][ny] = true;
			}
			break;
		case 3:
			for (int d=0; d<4; d++) {
				if (d!=1 && d!=3)	continue;
				
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (!inRange(nx, ny))	continue;
				if (visit[nx][ny])	continue;
				if (map[nx][ny] == 0) continue;
				if(!check(d, map[nx][ny])) continue;

				q.add(new Node(nx, ny));
				visit[nx][ny] = true;
			}
			break;
		case 4:
			for (int d=0; d<2; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (!inRange(nx, ny))	continue;
				if (visit[nx][ny])	continue;
				if (map[nx][ny] == 0) continue;
				if(!check(d, map[nx][ny])) continue;

				q.add(new Node(nx, ny));
				visit[nx][ny] = true;
			}
			break;
		case 5:
			for (int d=1; d<3; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (!inRange(nx, ny))	continue;
				if (visit[nx][ny])	continue;
				if (map[nx][ny] == 0) continue;
				if(!check(d, map[nx][ny])) continue;

				q.add(new Node(nx, ny));
				visit[nx][ny] = true;
			}
			break;
		case 6:
			for (int d=2; d<4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (!inRange(nx, ny))	continue;
				if (visit[nx][ny])	continue;
				if (map[nx][ny] == 0) continue;
				if(!check(d, map[nx][ny])) continue;
				
				q.add(new Node(nx, ny));
				visit[nx][ny] = true;
			}
			break;
		case 7:
			for (int d=0; d<4; d++) {
				if (d!=0 && d!=3)	continue;
				
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (!inRange(nx, ny))	continue;
				if (visit[nx][ny])	continue;
				if (map[nx][ny] == 0) continue;
				if(!check(d, map[nx][ny])) continue;
				
				q.add(new Node(nx, ny));
				visit[nx][ny] = true;
			}
			break;
		default:	// 0 (터널 없음)
			break;
		}
	}
	
	static boolean check(int d, int nextMap) {
		switch (d) {
		case 0:
			if (nextMap == 1 || nextMap == 2 || nextMap == 5 || nextMap == 6) {
				return true;
			}
			return false;
		case 1:
			if (nextMap == 1 || nextMap == 3 || nextMap == 6 || nextMap == 7) {
				return true;
			}
			return false;
		case 2:
			if (nextMap == 1 || nextMap == 2 || nextMap == 4 || nextMap == 7) {
				return true;
			}
			return false;
		case 3:
			if (nextMap == 1 || nextMap == 3 || nextMap == 4 || nextMap == 5) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<M;
	}
}
