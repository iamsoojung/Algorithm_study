import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] map;	// 자리 정할 맵
	static ArrayList<Integer>[] student;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static class Node implements Comparable<Node>{
		int x;
		int y;
		int likeCnt;
		int emptyCnt;
		
		public Node(int x, int y, int likeCnt, int emptyCnt) {
			super();
			this.x = x;
			this.y = y;
			this.likeCnt = likeCnt;
			this.emptyCnt = emptyCnt;
		}

		@Override
		public int compareTo(Node o) {
			if (this.likeCnt == o.likeCnt) {	// 좋아하는사람 인접 수 내림차순
				if (this.emptyCnt == o.emptyCnt) {	// 비어있는 인접 수 내림차순
					if (this.x == o.x) {	// 좌표 오름차순
						return Integer.compare(this.y, o.y);
					} else {
						return Integer.compare(this.x, o.x);
					}
				} else {
					return Integer.compare(o.emptyCnt, this.emptyCnt);
				}
			} else {
				return Integer.compare(o.likeCnt, this.likeCnt);
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		student = new ArrayList[N*N+1];
		for (int i=0; i<N*N+1; i++) {
			student[i] = new ArrayList<>();
		}
		
		for (int i=0; i<N*N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int me = Integer.parseInt(st.nextToken());
			
			for (int j=0; j<4; j++) {
				student[me].add(Integer.parseInt(st.nextToken()));
			}
			setSeat(me);
		}
		System.out.print(getHappy());
	}
	static void setSeat(int me) {
		PriorityQueue<Node> seat = new PriorityQueue<>();	// 자리 정하기 위함
		
		for (int r=0; r<N; r++) {
			for (int c=0; c<N; c++) {
				
				if (map[r][c] == 0) {	// 자리가 비어있을때만 정할 수 있음
					int lCnt=0, eCnt=0;
					for (int d=0; d<4; d++) {
						int nx = r + dx[d];
						int ny = c + dy[d];
						
						if (0>nx || nx>=N || 0>ny || ny>=N)	continue;
						
						if (student[me].contains(map[nx][ny]))	lCnt++;	// 인접 좋 수
						if (map[nx][ny] == 0)	eCnt++;	// 인접 빈 수
					}
					seat.add(new Node(r, c, lCnt, eCnt));
				}
			}
		}
		
		Node pick = seat.poll();
		map[pick.x][pick.y] = me;
	}
	static int getHappy() {
		int answer = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				
				int cnt=0;
				int me = map[i][j];
				
				for (int d=0; d<4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if (0>nx || nx>=N || 0>ny || ny>=N)	continue;
					
					if (student[me].contains(map[nx][ny])) cnt++;
				}
				switch(cnt) {
				case 1:
					answer += 1;
					break;
				case 2:
					answer += 10;
					break;
				case 3:
					answer += 100;
					break;
				case 4:
					answer += 1000;
					break;
				}
			}
		}
		return answer;
	}
}