/*
 * 2차원 좌표평면, x 오른쪽 / y 아래
 * 끝 점 : 시작 점에서 선분을 타고 이동했을때, 가장 먼 거리에 있는 점
 * K 세대 드커 = K-1 세대 드커를 끝점 기준으로 90도 시계방향 회전 후 끝점에 붙인것
 * 
 * 100*100 맵 위 드커 N개 존재 => 1*1 정사각형의 네 꼭짓점이 모두 드커 일부인 정사각형 개수 구하기
 * 1) 드커 만들기 (어떻게 드커를 그릴것인가...) -> 2) 정사각형 갯수 체크 (4개의 꼭짓점이 true)
 * 오, 위, 왼, 아 (0, 1, 2, 3)라고 잡아두면
 * 0세대 : 0 (1개)
 * 1세대 : 0 1 (2개)
 * 2세대 : 0 1 2 1 (4개)
 * 3세대 : 0 1 2 1 2 3 2 1 (8개)
 * 그리고 이전 세대 뒤집어서 다 +1하고 붙이면 됨 <- 규칙데스네
 * 그럼 일단 방향들을 다 구해서 넣어두고 그리면 될듯
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static ArrayList<Integer> dirList;
	static boolean[][] map;
	static int[] dx = {1, 0, -1, 0};	// 오 위 왼 아
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new boolean[101][101];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());	// 시작 방향
			int g = Integer.parseInt(st.nextToken());	// 세대
			
			dirList = new ArrayList<>();
			getAllDirList(d, g);
			drawDragonCurve(x, y);
		}
		System.out.print(checkSquare());
	}
	
	// 각 드래곤커브의 모든 방향 리스트 구하는 함수
	static void getAllDirList(int d, int g) {
		dirList.add(d);	// 0세대 방향

		for (int i=1; i<=g; i++) {
			for (int j=dirList.size()-1; j>=0; j--) {				
				dirList.add((dirList.get(j)+1) % 4);
			}
		}
	}
	
	// 드래곤커브 그리는 함수
	static void drawDragonCurve(int x, int y) {
		map[x][y] = true;
		
		int nx = x;
		int ny = y;
		for (int i=0; i<dirList.size(); i++) {
			int d = dirList.get(i);
			
			nx += dx[d];
			ny += dy[d];
			map[nx][ny] = true;
		}
	}
	
	// 크기가 1*1인 정사각형 갯수 구하는 함수
	static int checkSquare() {
		int cnt = 0;
		for (int i=0; i<100; i++) {
			for (int j=0; j<100; j++) {
				if (map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}