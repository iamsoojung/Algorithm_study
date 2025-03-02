import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 퀸은 상하좌우, 대각선으로 거리 제한 없이 한방향 이동 가능
 * 서로 공격할 수 없게 만드는 모든 경우의 수
 */
public class Main {
	
	static int N, answer=0;
	static int[] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new int[N];
		
		find(0);
		
		System.out.println(answer);
	}
	
	// 경우의 수 탐색
	static void find(int depth) {
		if (depth == N) {
			answer++;
			return;
		}
		
		for (int i=0; i<N; i++) {
			board[depth] = i;
			if (check(depth)) {
				find(depth + 1);
			}
		}
	}
	
	// 그 자리(q)에 놓을 수 있는지?
	static boolean check(int col) {
		for (int i=0; i<col; i++) {
			// 같은 행인지
			if (board[col] == board[i]) {
				return false;
			}
			// 대각선 방향에 있는지
			else if (Math.abs(col - i) == Math.abs(board[col] - board[i])) {
				return false;
			}
		}
		return true;
	}
}