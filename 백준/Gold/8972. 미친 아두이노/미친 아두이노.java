import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int R, C;
    static char[][] map;	//  '.'는 빈 칸, 'R'은 미친 아두이노, 'I'는 종수의 위치
    static Queue<Node> jongsoo = new ArrayDeque<>();
    static Queue<Node> crazy = new ArrayDeque<>();
    static int[] dx = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dy = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'I') {
                	jongsoo.add(new Node(i, j));
                }
                else if (map[i][j] == 'R') {
                	crazy.add(new Node(i, j));
                }
            }
        }
        
        String s = br.readLine();

        int count = 1;
        boolean flag = false;
        for (int i = 0; i < s.length(); i++) {
        	
        	// 1. 종수 이동	
			// 2. 종수가 미친 아두이노와 겹치는가?
            if (!moveJongsu(s.charAt(i) - '0')) {
                flag = true;
                break;
            }
            
            // 3. 미친 아두이노 이동
 			// 4. 미친 아두이노가 종수와 겹치는가?
            // 5. 여러 미친 아두이노가 겹친다면 폭발		
            if (!moveCrazyArduino()) {
                flag = true;
                break;
            }
            
            // 이동해버려잇
            map();

            count++;
        }
        if (flag) {
        	System.out.println("kraj " + count);
        }
        else {
        	for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }
    }

    public static boolean moveJongsu(int dir) {
        Node tmp = jongsoo.poll();

        int nx = tmp.r + dx[dir];
        int ny = tmp.c + dy[dir];

        if (map[nx][ny] == 'R') return false;
        else {
            jongsoo.add(new Node(nx, ny));
            if (dir != 5) {		// 제자리 아니면
                map[nx][ny] = map[tmp.r][tmp.c];
                map[tmp.r][tmp.c] = '.';
            }
            return true;
        }
    }

    public static boolean moveCrazyArduino() {
        int[][] tmp = new int[R][C];

        Node js = jongsoo.peek();

        int jsX = js.r;
        int jsY = js.c;

        while (!crazy.isEmpty()) {

            Node cr = crazy.poll();

            int min = Integer.MAX_VALUE;
            int dir = 0;
            
            for (int j=1; j<=9; j++) {
            	
                if (j == 5) continue;
                
                int nx = cr.r + dx[j];
                int ny = cr.c + dy[j];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;

                // 거리 계산
                int dist = Math.abs(jsX - nx) + Math.abs(jsY - ny);
                
                // 최솟값 갱신
                if (min > dist) {
                	min = dist;
                    dir = j;
                }
            }
            
            // 이동하는거 반영하기
            int moveX = cr.r + dx[dir];
            int moveY = cr.c + dy[dir];

            // 겹치는지
            if (map[moveX][moveY] == 'I') {
                return false;
            }
            tmp[moveX][moveY] += 1;
        }
        
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (tmp[i][j] == 1) {
                    crazy.add(new Node(i, j));
                }
            }
        }

        return true;
    }

    public static void map() {
    	// 맵 새로!!! 초기화해버려
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = '.';
            }
        }

        Node js = jongsoo.peek();

        // 종수 이동
        map[js.r][js.c] = 'I';
        
        // 미친놈 이동
        for (int i = 0; i < crazy.size(); i++) {
            Node cr = crazy.poll();
            map[cr.r][cr.c] = 'R';
            crazy.add(new Node(cr.r, cr.c));
        }
    }
}
