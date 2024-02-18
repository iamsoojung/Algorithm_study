import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main (String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int testCase=1; testCase<=T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());	// 행
			int W = Integer.parseInt(st.nextToken());	// 열
			char[][] map = new char[H][W];
			
			for (int i=0; i<H; i++) {
				map[i] = br.readLine().toCharArray();					
			}
			
			// 초기 방향과 위치 찾기
			int x=0, y=0;
			for (int i=0; i<map.length; i++) {
				for (int j=0; j<map[0].length; j++) {
					if (map[i][j] == '^' || map[i][j] == 'v'
							|| map[i][j] == '<' || map[i][j] == '>') {
						x = i;
						y = j;
					}
				}
			}
			
			int N = Integer.parseInt(br.readLine());
			String input = br.readLine();
			
			for (int i=0; i<N; i++) {
				int next=0;
				int nx, ny;
				int d = curState(map[x][y]);
				
				switch(input.charAt(i)) {
				case 'U':
					map[x][y] = '^';	// 현재 위치에 방향 저장
					d = 0;	// 방향 설정
					nx = x + dx[d];
					ny = y + dy[d];
					// 범위 안에 있음 && 평지인 경우
					if (rangeCheck(map, nx, ny) && map[nx][ny] == '.') {
						map[nx][ny] = '^';
						map[x][y] = '.';	// 내 위치를 평지로
						x = nx;
						y = ny;
					}
					break;
				case 'D':
					map[x][y] = 'v';
					d = 1;
					nx = x + dx[d];
					ny = y + dy[d];
					if (rangeCheck(map, nx, ny) && map[nx][ny] == '.') {
						map[nx][ny] = 'v';
						map[x][y] = '.';
						x = nx;
						y = ny;
					}
					break;
				case 'L':
					map[x][y] = '<';
					d = 2;
					nx = x + dx[d];
					ny = y + dy[d];
					if (rangeCheck(map, nx, ny) && map[nx][ny] == '.') {
						map[nx][ny] = '<';
						map[x][y] = '.';
						x = nx;
						y = ny;
					}
					break;
				case 'R':
					map[x][y] = '>';
					d = 3;
					nx = x + dx[d];
					ny = y + dy[d];
					if (rangeCheck(map, nx, ny) && map[nx][ny] == '.') {
						map[nx][ny] = '>';
						map[x][y] = '.';
						x = nx;
						y = ny;
					}
					break;
				case 'S':
					int sx = x;	// 슛할 위치
					int sy = y;
					
					while (rangeCheck(map, sx, sy)) {
						sx += dx[d];
						sy += dy[d];
						
						if (rangeCheck(map, sx, sy)) {
							if (map[sx][sy] == '*') {	// 벽돌
								map[sx][sy] = '.';
								break;
							} else if (map[sx][sy] == '-' || map[sx][sy] == '-') {
								continue;
							} else if (map[sx][sy] == '#') {	// 강철
								break;
							}
						} else {
							break;
						}
						
					}
					break;
				}
			}
			sb.append("#" + testCase + " ");
			for (int i=0; i<map.length; i++) {
				for (int j=0; j<map[0].length; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}
	
	static int curState(char c) {
		if (c == '^') {
			return 0;
		} else if (c == 'v') {
			return 1;
		} else if (c == '<') {
			return 2;
		} else if (c == '>') {
			return 3;
		}
		return 0;
	}
	
	static boolean rangeCheck(char[][] map, int x, int y) {
		if (0<=x && x<map.length && 0<=y && y<map[0].length) {
			return true;
		}
		return false;
	}
	
}