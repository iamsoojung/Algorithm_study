import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 정육면체 주사위 1~6 / 10x10 각 숫자 적힌 보드판 (맨밑 왼부터 시작인듯)
 * 플레이어 - 주사위 굴려서 나온 수만큼 이동 => 플레이어 i번 칸, 주사위 4 => i+4칸 이동
 * 결과가 100 넘으면 이동 x, 사다리면 위로 올라감, 뱀이면 뱀따라서 내려감
 * 100번 도착하기 위해 주사위 굴려야 하는 최소 횟수
 */
public class Main {
	
	static int N, M, answer=0;
	static int[] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());	// 사다리 수
		M = Integer.parseInt(st.nextToken());	// 뱀 수

		board = new int[101];
		for (int i=1; i<board.length; i++) {
			board[i] = i;
		}
		
		for (int i=0; i<N+M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());			
			board[x] = y;
		}

		answer = bfs(1);
		System.out.print(answer);
	}
	
	static int bfs(int start) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {start, 0});

		boolean[] visit = new boolean[101];
		visit[start] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			if (cur[0] == 100)	return cur[1];
			
			for (int i=1; i<=6; i++) {
				int nx = board[cur[0]] + i;
				
				if (nx > 100)	continue;
				if (visit[nx])	continue;
				
				q.add(new int[] {nx, cur[1]+1});
				visit[nx] = true;
			}
		}
		
		return -1;	
	}
}
