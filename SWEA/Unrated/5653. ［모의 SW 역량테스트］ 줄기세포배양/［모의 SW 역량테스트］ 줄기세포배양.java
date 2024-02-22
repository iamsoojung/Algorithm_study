import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	private static final int INACTIVE = 2;
	private static final int ACTIVE = 1;
	private static final int DEATH = 0;
	
	public static class Cell implements Comparable<Cell>{
		int r, c;	// 행,열 좌표
		int X;	// 생명력
		int val;	// 현재값
		int status = INACTIVE;	// 상태 변수
		
		public Cell(int r, int c, int x) {
			super();
			this.r = r;
			this.c = c;
			this.X = x;
			this.val = x;
		}
		
		public void next() {	// 1시간 지날 때마다 외부에서 호출하기
			val--;
			if (val > 0)	return;	// 상태는 안바뀜
			if (status == INACTIVE) {	// 비활성화인 경우
				status = ACTIVE;	// 활성화로
				val = X;
			} else if (status == ACTIVE) {	// 활성화인 경우
				status = DEATH;		// 사망
			}
		}

		@Override
		public int compareTo(Cell o) {
			return o.X - this.X;	// 생명력 오름차순 정렬
		}
	}
	
	static int N, M, K;
	static int[][] map;	
	static PriorityQueue<Cell> q, tempQ;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int testCase=1; testCase<=T; testCase++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// 세로
			M = Integer.parseInt(st.nextToken());	// 가로
			K = Integer.parseInt(st.nextToken());	// 배양 시간
			
			map = new int[N+K][M+K];	// 최대 확장 가능한 크기만큼 배열 생성
			q = new PriorityQueue<Cell>();	// 우선순위 큐라서 Cell에서 그 기준(생명력 오름차순)을 알려주도록 comparable 적용
			tempQ = new PriorityQueue<Cell>();	// 자식들을 따로 담아놓고 다시 q에 넣는 임시 큐
			
			int start = K/2;
			for (int i = start; i < N+start; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = start; j < M+start; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 0) {
						q.add(new Cell(i, j, map[i][j]));
					}
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(bfs()).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	public static int bfs() {
		while (K-- > 0) {
			int size = q.size();
			tempQ.clear();
			for (int i=0; i<size; i++) {	// 큐의 크기만큼 반복
				Cell cell = q.poll();
				if(cell.status == ACTIVE) {	// 활성화되어있다면, 인접 칸으로 퍼뜨리기
					for (int j=0; j<4; j++) {
						int nr = cell.r + dr[j];
						int nc = cell.c + dc[j];
						if (map[nr][nc] == 0) {	// 방문하지 않았으면, 큐에 넣기
							map[nr][nc] = cell.X;	// 방문한거 기록해두기
							tempQ.add(new Cell(nr, nc, map[nr][nc]));	// 번식된 셀을 임시큐에 저장
						}
					}
				}
				cell.next();	// 세포 1시간 씩 성숙 시키기
				if (cell.status != DEATH) {	// 죽은 것빼고 다 넣자
					tempQ.add(cell);	// 꺼낸 것을 다시 큐에 넣기
				}
			}
			q.addAll(tempQ);
		}
		return q.size();	// 살아있는 세포의 수
	}
	
}
