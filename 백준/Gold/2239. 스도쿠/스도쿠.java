import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {	
	
	static int[][] board;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		board = new int[9][9];
		for (int i=0; i<9; i++) {
			s = br.readLine();
			for (int j=0; j<9; j++) {
				board[i][j] = s.charAt(j) - '0';
			}
		}
		sudoku(0);
		
		System.out.print(sb.toString());
	}
	
	// 스도쿠 1행1열부터 쭉 (1~81)
	static void sudoku (int depth) {
		if (sb.length() > 0)	return;		// 시간초과 줄이기 ,,
		
		if (depth == 81) {
			for (int i=0; i<9; i++) {
				for (int j=0; j<9; j++) {
					sb.append(board[i][j]);
				}
				sb.append("\n");
			}
			return;
		}
		
		int r = depth / 9;
		int c = depth % 9;
		
		if (board[r][c] == 0) {
			for (int num=1; num<=9; num++) {	// 1부터 9까지 다 넣어보기
				if (checkLine(r, c, num) && check33(r, c, num)) {	// 잠깐? 넣기전에 판단해보자
					board[r][c] = num;
					sudoku(depth+1);
					board[r][c] = 0;	// 원복
				}
			}
		} else {
			sudoku(depth+1);
		}
		
	}
	
	static boolean checkLine(int r, int c, int num) {
		for (int i=r/3*3; i<r/3*3 + 3; i++) {
			for (int j=c/3*3; j<c/3*3 + 3; j++) {
				if (board[i][j] == num)	{	// 이미 숫자 있으면 땡!
					return false;
				}
			}
		}
		return true;	// 무사히 통과했으니 안녕
	}
	
	static boolean check33(int r, int c, int num) {
		
		for (int i=0; i<9; i++) {	// 세로
			if (board[r][i] == num) {
				return false;
			}
		}
		for (int i=0; i<9; i++) {	// 가로
			if (board[i][c] == num) {
				return false;
			}
		}
		return true;
	}
}