import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, maxCore, minWire;
	static int[][] map;
	static ArrayList<int []> coreList;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=T; testCase++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			coreList = new ArrayList<>();
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if (0==i || i==N-1 || 0==j || j==N-1)	continue;
					if (map[i][j] == 1) {
						coreList.add(new int[] {i, j});
					}
				}
			}
			
			maxCore = Integer.MIN_VALUE;
			minWire = Integer.MAX_VALUE;
			dfs(0, 0, 0);

			sb.append("#").append(testCase).append(" ").append(minWire).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void dfs(int idx, int coreCnt, int wireCnt) {
		if (idx == coreList.size()) {
			if (maxCore < coreCnt) {
				maxCore = coreCnt;
				minWire = wireCnt;
			} else if (maxCore == coreCnt && minWire > wireCnt) {
				minWire = wireCnt;
			}
			return;
		}
		
		int cx = coreList.get(idx)[0];
		int cy = coreList.get(idx)[1];
		
		for (int d=0; d<4; d++) {
			int nx=cx;
			int ny=cy;
			int count = 0;
			
			while(true) {
				nx += dx[d];
				ny += dy[d];
				
				if (0>nx || nx>=N || 0>ny || ny>=N)	break;
				
				if (map[nx][ny] != 0) {		// 어차피 연결 못함, 카운트 0으로
					count = 0;
					break;
				}
				count++;
			}
			
			int tmpX = cx;
			int tmpY = cy;
			for (int i=0; i<count; i++) {
				tmpX += dx[d];
				tmpY += dy[d];
				map[tmpX][tmpY] = 2;
			}
			
			if (count == 0) {
				dfs(idx+1, coreCnt, wireCnt);
			} else {
				dfs(idx+1, coreCnt+1, wireCnt+count);
				
				// 되돌리기
				tmpX = cx;
				tmpY = cy;
				for (int i=0; i<count; i++) {
					tmpX += dx[d];
					tmpY += dy[d];
					map[tmpX][tmpY] = 0;
				}
			}
		}
	}
}
