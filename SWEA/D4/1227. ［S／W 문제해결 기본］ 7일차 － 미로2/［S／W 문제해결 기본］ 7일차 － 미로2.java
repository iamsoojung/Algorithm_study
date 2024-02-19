import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 0 길 / 1 벽 / 2 출발점 / 3 도착점
public class Solution {
	static int startX, startY, endX, endY;
	static int[][] map = new int[100][100];
	static boolean[][] visit;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int testCase=1; testCase<=10; testCase++) {
			br.readLine();
			for (int i=0; i<100; i++) {
				String s = br.readLine();
				for (int j=0; j<100; j++) {
					map[i][j] = s.charAt(j) - 48;
					if (map[i][j] == 2) {	// 출발점
						startX = i;	startY = j;
					} else if (map[i][j] == 3) {
						endX = i; endY = j;
					}
				}
			}
			
			visit = new boolean[100][100];
			sb.append("#").append(testCase).append(" ").append(bfs()).append("\n");
		}
		System.out.print(sb);
		br.close();
	}
	static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {startX, startY});
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			visit[tmp[0]][tmp[1]] = true;
			if (tmp[0] == endX && tmp[1] == endY)	return 1;
			
			for (int i=0; i<4; i++) {
				int nx = tmp[0] + dx[i];
				int ny = tmp[1] + dy[i];
				
				if (0<=nx && nx<100 && 0<=ny && ny<100 && !visit[nx][ny] && map[nx][ny]!=1) {
					visit[nx][ny] = true;
					q.add(new int[] {nx, ny});
				}
			}
		}
		return 0;
	}
}
