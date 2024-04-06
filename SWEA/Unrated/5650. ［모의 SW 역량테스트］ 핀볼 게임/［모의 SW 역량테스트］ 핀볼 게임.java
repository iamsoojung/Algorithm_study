import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    
    static int N, answer;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static List<Point> startList;    // 시작 위치 저장
    static List<Point>[] wormList;    // 웜홀 위치 저장 (페어로)

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int T = scanner.nextInt();
        for (int testCase = 1; testCase <= T; testCase++) {
            answer = 0;
            
            N = scanner.nextInt();
            map = new int[N][N];
            
            startList = new ArrayList<>();
            wormList = new ArrayList[11];    // 6 ~ 10
            for (int i = 6; i <= 10; i++) {
                wormList[i] = new ArrayList<>();
            }
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = scanner.nextInt();
                    if (map[i][j] == 0) {
                        startList.add(new Point(i, j));
                    } else if (map[i][j] >= 6) {
                        wormList[map[i][j]].add(new Point(i, j));
                    }
                }
            }
            
            // 임의로 출발 위치 & 진행 방향 선정 가능
            for (Point start : startList) {
                for (int d = 0; d < 4; d++) {
                    int result = go(start, d);
                    answer = Math.max(answer, result);
                }
            }
            
            System.out.println("#" + testCase + " " + answer);
        }
        
        scanner.close();
    }
    
    static int go(Point start, int d) {
        int cnt = 0;
        
        int cx = start.x;
        int cy = start.y;
        
        while (true) {
            cx += dx[d];    // 전진
            cy += dy[d];
            
            // 벽
            if (0 > cx || cx >= N || 0 > cy || cy >= N) {
            	d = (d + 2) % 4;    // 방향 반대
            	cnt++;
            	continue;
            }
            
            // 종료 조건 (출발위치 or 블랙홀)
            if ((cx == start.x && cy == start.y) || map[cx][cy] == -1)    break;
            
            // 블록
            switch (map[cx][cy]) {
            case 1:
                if (d == 2 || d == 3) {    // 경사면
                    d = (d == 2) ? 1 : 0;
                } else {    // 수평면
                    d = (d == 0) ? 2 : 3;
                }
                cnt++;
                break;
            case 2:
                if (d == 0 || d == 3) {
                    d = (d == 0) ? 1 : 2;
                } else {
                    d = (d == 1) ? 3 : 0;
                }
                cnt++;
                break;
            case 3:
                if (d == 0 || d == 1) {
                    d = (d == 0) ? 3 : 2;
                } else {
                    d = (d == 2) ? 0 : 1;
                }
                cnt++;
                break;
            case 4:
                if (d == 1 || d == 2) {
                    d = (d == 1) ? 0 : 3;
                } else {
                    d = (d == 0) ? 2 : 1;
                }
                cnt++;
                break;
            case 5:
                d = (d + 2) % 4;
                cnt++;
                break;
            // 웜홀
            case 6: case 7: case 8: case 9: case 10:
            	int n = map[cx][cy];
            	if (wormList[n].get(0).x == cx && wormList[n].get(0).y == cy) {	// 페어 찾기
                    cx = wormList[n].get(1).x;
                    cy = wormList[n].get(1).y;
                } else {
                	cx = wormList[n].get(0).x;
                    cy = wormList[n].get(0).y;
                }
            	break;
            }
        }
        return cnt;
    }
}