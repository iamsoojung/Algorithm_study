import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K, answer=0;
    static int[][] map;
    static boolean[][][] visit;    // 3차원 방문 체크
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        visit = new boolean[N][M][K+1];	// 0번 ~ K번 부순 경우
        
        for (int i=0; i<N; i++) {
            String s = br.readLine();
            for (int j=0; j<M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }
        
        answer = bfs(0, 0);
        
        if (answer == -1) {
        	System.out.println(-1);
        } else {
        	System.out.println(answer);
        }
    }
    
    static int bfs(int x, int y) {
    	Queue<Wall> q = new ArrayDeque<>();
    	
    	q.offer(new Wall(x, y, 1, 0));
    	visit[x][y][0] = true;
    	
    	while(!q.isEmpty()) {
    		Wall cur = q.poll();
    		
    		int cx = cur.x;
    		int cy = cur.y;
    		
    		if (cx == N-1 && cy == M-1)	{	// 도착
    			return cur.dist;
    		}
    		
    		for (int d=0; d<4; d++) {
    			int nx = cx + dx[d];	// 현 위치 기준으로 다음 위치 (nx = cx + dx[d])
    			int ny = cy + dy[d];
    			
    			if (0 > nx || nx >= N || 0 > ny || ny >= M)	continue;
    			
    			if (map[nx][ny] == 0) {		// 벽 X, 방문 X
    				if (!visit[nx][ny][cur.crushCnt]) {
    					q.offer(new Wall(nx, ny, cur.dist+1, cur.crushCnt));
    					visit[nx][ny][cur.crushCnt] = true;
    				}
    			}
    			else if (map[nx][ny] == 1) {	// 벽 O, 방문 X, k번 미만 부숨
    				if (cur.crushCnt < K && !visit[nx][ny][cur.crushCnt+1]) {
    					q.offer(new Wall(nx, ny, cur.dist+1, cur.crushCnt+1));
    					visit[nx][ny][cur.crushCnt+1] = true;
    				}
    			}
    		}
    	}
    	return -1;
    }
    
    static class Wall {
        int x;
        int y;
        int dist;
        int crushCnt;
        
		public Wall(int x, int y, int dist, int crushCnt) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.crushCnt = crushCnt;
		}
    }
}