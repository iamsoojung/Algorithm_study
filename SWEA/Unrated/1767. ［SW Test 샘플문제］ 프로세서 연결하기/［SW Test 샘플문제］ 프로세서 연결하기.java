import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
코어들을 리스트에 저장햇어
그럼? 이제 코어 하나씩 꺼내면서 봐야겠지?
하나씩 꺼내서 가장자리로 연결할 수 있는 길이를 알아내야해 (도중에 빈셀이 아니면 멈춰야겠지)
길이를 알아냈으면? 그길이, 그방향으로 쭉 전선으로 갱신해나가야돼
그렇게 해서 현재보는셀을 보는게 끝났다면 재귀로 다음 셀을 보면 돼. (인덱스와 최대코어갯수&최소전선길이)
자 해봐 김수정
 */

public class Solution {
	
	static int N, size, maxCoreCnt, minWireLength;
	static int[][] map;
	static ArrayList<int[]> coreList;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase=1;  testCase<=T; testCase++) {
			
			N = Integer.parseInt(br.readLine());
			
			coreList = new ArrayList<>();
			map = new int[N][N];
			
			for (int i=0; i<N; i++) {	// 0: 빈 셀, 1: 코어
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if (map[i][j] == 1 && 0!=i && i!=N-1 && 0!=j && j!=N-1) {
						coreList.add(new int[] {i, j});
					}
				}
			}
			
			size = coreList.size();
			maxCoreCnt = Integer.MIN_VALUE;
			minWireLength = Integer.MAX_VALUE;
			dfs(0, 0, 0);
			
			sb.append("#").append(testCase).append(" ").append(minWireLength).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	static void dfs(int idx, int coreCnt, int wireLength) {
		
		if (idx == size) {
			if (maxCoreCnt < coreCnt) {
				maxCoreCnt = coreCnt;
				minWireLength = wireLength;
			} else if (maxCoreCnt == coreCnt && minWireLength > wireLength) {
				minWireLength = wireLength;
			}			
			return;
		}
		
		int[] cur = coreList.get(idx);
		int cx = cur[0];
		int cy = cur[1];
		
		for (int d=0; d<4; d++) {

			int cnt = 0;
			int nx = cx, ny = cy;			
			while(true) {
				nx += dx[d];
				ny += dy[d];
				
				if (!inRange(nx, ny))	break;
				
				if (map[nx][ny] != 0) {
					cnt = 0;
					break;
				}
				cnt++;
			}
			
			int tmpX = cx, tmpY = cy;
			for (int i=0; i<cnt; i++) {
				tmpX += dx[d];
				tmpY += dy[d];
				
				map[tmpX][tmpY] = 2;	// 전선 놓음
			}
			
			if (cnt != 0) {
				dfs(idx+1, coreCnt + 1, wireLength + cnt);
				
				// 되돌리기
				tmpX = cx;	tmpY = cy;
				for (int i=0; i<cnt; i++) {
					tmpX += dx[d];
					tmpY += dy[d];
					
					map[tmpX][tmpY] = 0;
				}
			} else {
				dfs(idx+1, coreCnt, wireLength);
			}
		}
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<N;
	}
}