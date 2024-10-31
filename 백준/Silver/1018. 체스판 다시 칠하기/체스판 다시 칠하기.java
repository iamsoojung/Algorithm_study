import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * M*N 보드 - 어떤 정사각형은 검은색칠, 나머지는 흰색칠
 * 이 보드를 잘라서 8*8 크기의 체스판 만드려고 함.
 * 체스판 - 조건) 검/흰 번갈아서 칠해져 있어야 함
 * 변을 공유하는 2개의 사각형은 다른 색으로 칠해져 있어야 함
 * => 체스판 색칠 경우 2가지뿐임 ((0,0)가 흰 or 검)
 * 
 * 보드가 체스판처럼 칠해져있는 보장 X => 8*8 체스판 잘라낸 후에 정사각형 몇개 다시 칠하려 함
 * 다시 칠해야 하는 정사각형의 최소 개수 
 */

// 1. 우선 체스판 8*8 크기로 잘라본다.
// 2. (행+열)의 짝/홀 여부에 따라 검/흰 or 흰/검 판단 해서 색칠하는 수 구하기
// 3. 제일 작은 수로 갱신

public class Main {
	
	static int N, M, answer;
	static char[][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		for (int i=0; i<N; i++) {
			String s = br.readLine();
			for (int j=0; j<M; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		
		answer = Integer.MAX_VALUE;
		for (int i=0; i<=N-8; i++) {
			for (int j=0; j<=M-8; j++) {
				int result = getDrawCnt(i, j);
				
				answer = Math.min(answer, result);
			}
		}
		
		System.out.print(answer);
	}
	
	static int getDrawCnt(int row, int col) {
		
		int cnt1 = 0;
		int cnt2 = 0;
		
		for (int i=row; i<row+8; i++) {
			for (int j=col; j<col+8; j++) {
				
				if ((i+j) % 2 == 0) {
					if (board[i][j] != 'B') {	// BWBWBW ..
						cnt1++;
					} else if (board[i][j] != 'W') {	// WBWBWB ..
						cnt2++;
					}
				} else {
					if (board[i][j] != 'W') {
						cnt1++;
					} else if (board[i][j] != 'B') {
						cnt2++;
					}
				}
			}
		}
		
		return Math.min(cnt1, cnt2);
	}
}