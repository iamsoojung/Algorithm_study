import java.util.*;
import java.io.*;

class Solution {
    
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    static class Point {
        int x, y, dist;
        
        public Point (int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
    // rectangle : 각 직사각형 좌표
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        int answer = 0;
        
        // 중요!! 테두리만 필터링하기 (없음:0, 테두리:1, 내부:2)
        // 꺾이는 부분을 쉽게 하기 위해, 맵을 2배로 확장. (좌표값 50이하이므로, 101*101)
        // 연결되어있지않으나, 거리가 1이라면 그곳으로 갈 수 있기 때문
        // *  x---x                o---x
        // *      |     ---x-->    ⬆️   |
        // *  o->-o                o---x
        // *  오른쪽으로 가야함       위로 감
        map = new int[101][101];
        
        for (int i=0; i<rectangle.length; i++) {
            fill(rectangle[i][0] * 2, rectangle[i][1] * 2,
                rectangle[i][2] * 2, rectangle[i][3] * 2);
        }
        
        answer = bfs(characterX*2, characterY*2, itemX*2, itemY*2);
            
        return answer;    
    }
    
    public void fill(int x1, int y1, int x2, int y2) {
        for (int i=x1; i<=x2; i++) {
            for (int j=y1; j<=y2; j++) {
                if (map[i][j] == 2) continue;   // 이미 내부
                
                if (i == x1 || j == y1 || i == x2 || j == y2) {
                    map[i][j] = 1;  // 경계
                } else {
                    map[i][j] = 2;  // 내부
                }                
            }
        }
    }
    
    public int bfs(int startX, int startY, int endX, int endY) {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[101][101];
        
        q.add(new Point(startX, startY, 0));
        isVisited[startX][startY] = true;
        
        while(!q.isEmpty()) {
            Point cur = q.poll();
            
            if (cur.x == endX && cur.y == endY) {
                return cur.dist / 2;    // map을 2배 확장했기에 값은 2배 축소
            }
            
            for (int d=0; d<4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                
                if (!inRange(nx, ny) || isVisited[nx][ny] || map[nx][ny] != 1)   continue;
                
                isVisited[nx][ny] = true;
                q.add(new Point(nx, ny, cur.dist+1));
            }
        }
        return 0;
    }
    
    public boolean inRange(int x, int y) {
        return 0<=x && x<101 && 0<=y && y<101;
    }
}