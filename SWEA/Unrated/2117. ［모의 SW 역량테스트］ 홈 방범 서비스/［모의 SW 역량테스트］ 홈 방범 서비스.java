import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, M, K, answer;
	static boolean flag;
	static int[][] map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 도시의 크기
			M = Integer.parseInt(st.nextToken());	// 한 집이 지불하는 비용
			
			map = new int[N][N];	// 1: 집, 0: 나머지
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = 0;
			flag = false;
			K = (N%2 == 0) ? N+1 : N;	// 도시크기가 짝수라면 마름모라서 N+1 해야함 
			
			for (int k=K; k>0; k--) {
				int cost = k*k + (k-1)*(k-1);	// 운영 비용
				int dist = k-1;
				
				for (int i=0; i<N; i++) {
					for (int j=0; j<N; j++) {
						int cnt = homeCnt(i, j, dist);	// 집 갯수
						
						if (cnt*M - cost >= 0) {	// 손해 없음
							answer = Math.max(answer, cnt);
							flag = true;
						}
					}
				}
				if (flag)	break;
			}
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	static int homeCnt(int x, int y, int dist) {
		int count = 0;
		for (int i = x-dist; i <= x+dist; i++) {
			for (int j = y-dist; j <= y+dist; j++) {
				
				if (!inRange(i, j))	continue;
				if (!isMRM(i, j, x, y, dist))	continue;
				
				if (map[i][j] == 1)	count++;
			}
		}
		
		return count;
	}
	
	static boolean isMRM(int i, int j, int x, int y, int dist) {
		return Math.abs(i-x) + Math.abs(j-y) <= dist;
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<N;
	}
}
