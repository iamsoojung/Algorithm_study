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
            int cNum = map[cx][cy];
            
            // 여기가 블록?
            if (1 <= cNum && cNum <= 5) {
                switch (cNum) {
                case 1:
                    if (d == 2 || d == 3) {    // 경사면
                        d = (d == 2) ? 1 : 0;
                    } else {    // 수평면
                        d = (d == 0) ? 2 : 3;
                    }
                    break;
                case 2:
                    if (d == 0 || d == 3) {
                        d = (d == 0) ? 1 : 2;
                    } else {
                        d = (d == 1) ? 3 : 0;
                    }
                    break;
                case 3:
                    if (d == 0 || d == 1) {
                        d = (d == 0) ? 3 : 2;
                    } else {
                        d = (d == 2) ? 0 : 1;
                    }
                    break;
                case 4:
                    if (d == 1 || d == 2) {
                        d = (d == 1) ? 0 : 3;
                    } else {
                        d = (d == 0) ? 2 : 1;
                    }
                    break;
                case 5:
                    d = (d + 2) % 4;
                    break;
                }
                cnt++;
            }
            
            int nx = cx + dx[d];    // 전진
            int ny = cy + dy[d];
            
            // 갔는데 벽?
            if (0 > nx || nx >= N || 0 > ny || ny >= N) {
                d = (d + 2) % 4;    // 방향은 반대
                nx = cx;    // 위치는 그대로
                ny = cy;
                cnt++;
            }

            // 갔는데 웜홀?
            if (map[nx][ny] >= 6) {
                int n = map[nx][ny];
                for (int i = 0; i < 2; i++) {    // 페어 찾기
                    if (wormList[n].get(i).x == nx && wormList[n].get(i).y == ny) {
                        nx = (i == 0) ? wormList[n].get(1).x : wormList[n].get(0).x;
                        ny = (i == 1) ? wormList[n].get(0).y : wormList[n].get(1).y;
                        break;
                    }
                }
            }
            
            // 종료 조건 (출발위치 or 블랙홀)
            if ((nx == start.x && ny == start.y) || map[nx][ny] == -1)    break;
            
            // 다음 준비 (다음이 현재로)
            cx = nx;
            cy = ny;
        }
        return cnt;
    }
}
