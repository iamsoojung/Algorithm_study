import java.util.*;
import java.io.*;

class Solution {
    
    public static class Monster {
        int x, y, dist;
        
        public Monster(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        N = maps.length;
        M = maps[0].length;

        answer = bfs(maps);
        
        return answer;
    }
    
    public int bfs(int[][] maps) {
        Queue<Monster> q = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[N][M];
        
        q.add(new Monster(0, 0, 1));
        isVisited[0][0] = true;
        
        while(!q.isEmpty()) {
            Monster cur = q.poll();
            
            if (cur.x == N-1 && cur.y == M-1) return cur.dist;
            
            for (int d=0; d<4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (!inRange(nx, ny) || isVisited[nx][ny] || maps[nx][ny] == 0)   continue;

                q.add(new Monster(nx, ny, cur.dist+1));
                isVisited[nx][ny] = true;
            }
        }
        
        return -1;        
    }
    
    public boolean inRange(int x, int y) {
        return 0<=x && x<N && 0<=y && y<M;
    }
}