
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main
{
    static int N, M;	// 3 ≤ N, M ≤ 8
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 세로
        M = Integer.parseInt(st.nextToken());   // 가로
        
        map = new int[N][M];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0);
        System.out.print(answer);
   	}
    
    // 벽(1)을 꼭 3개를 세워야 한다. (빈칸(0)에 설치 가능) - DFS
    static void dfs(int depth) {
    	if (depth == 3) {	// 벽 설치 끝
    		bfs();	// 퍼진 바이러스와 안전 영역 구하러 가기
    		return;
    	}
    	
    	for (int i=0; i<N; i++) {
    		for (int j=0; j<M; j++) {
    			if (map[i][j] == 0) {	// 벽 설치 가능
    				map[i][j] = 1;
    				dfs(depth+1);
    				map[i][j] = 0;	// 부분집합 할거니까 다시 리셋
    			}
    		}
    	}
    }
    
    // 바이러스가 퍼져 나간다. (안전 영역 크기의 최댓값 구하기) - BFS
    static void bfs() {
    	Queue<int[]> q = new ArrayDeque<>();
    	for (int i=0; i<N; i++) {	// 바이러스들을 큐에 넣기 (퍼짐을 보기 위해)
    		for (int j=0; j<M; j++) {
    			if (map[i][j] == 2) {
        			q.add(new int[] {i, j});
    			}
    		}
    	}
    	
    	// int[][] copyMap = map.clone();		// 얕은 복사 (주소값 참조 및 변경)
    	int[][] copyMap = new int[N][M];		// 깊은 복사 (요소 복사 및 독립적 존재)
    	for (int i=0; i<N; i++) {
    		copyMap[i] = map[i].clone();
    	}
    	
    	while(!q.isEmpty()) {
    		int[] cur = q.poll();
    		int cx = cur[0];
    		int cy = cur[1];
    		
    		for (int d=0; d<4; d++) {
    			int nx = cx + dx[d];
    			int ny = cy + dy[d];
    			
    			if (0>nx || nx>=N || 0>ny || ny>=M || copyMap[nx][ny] != 0)	continue;
    			
    			copyMap[nx][ny] = 2;	// 바이러스 퍼짐
    			q.offer(new int[] {nx, ny});
    		}
    	}
    	
    	int cnt = 0;
    	for (int i=0; i<N; i++) {
    		for (int j=0; j<M; j++) {
    			if (copyMap[i][j] == 0)	cnt++;
    		}
    	}
    	
    	if (answer < cnt) {
    		answer = cnt;
    	}
    }
}