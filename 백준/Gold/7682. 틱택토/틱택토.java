
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 3*3 게임판, X부터 시작, XOXOXO...
 * 먼저 가로/세로/대각선으로 3칸 이으면 즉시 끝남
 * 게임판 가득 차도 게임 끝남
 * 게임판 상태가 최종상태가 맞는지 확인
 */
// 가로, 세로, 대각선 검사 -> 빙고 찾아내기
// 1) XOXO : O가 이겨야함
// 2) XOXOX : X가 이겨야함

public class Main {

	static char[][] map;
	static int xRow, xCol, xCross;	// 가로, 세로, 대각선 빙고 수
	static int oRow, oCol, oCross;	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String s = br.readLine();
			
			if (s.equals("end"))	break;
			
			xRow = 0;
			xCol = 0;
			xCross = 0;
			oRow = 0;
			oCol = 0;
			oCross = 0;
			
			map = new char[3][3];
			
			int xCnt = 0;
			int oCnt = 0;
			for (int i=0; i<9; i++) {
				map[i/3][i%3] = s.charAt(i);
				
				if (map[i/3][i%3] == 'X')	xCnt++;
				else if (map[i/3][i%3] == 'O')	oCnt++;
			}
			
			// XOXO or XOXOX만 가능 (X갯수==O갯수 or X갯수==O갯수+1)
			if (!(xCnt == oCnt || xCnt == oCnt+1)) {
				System.out.println("invalid");
				continue;
			}
			
			// 가로, 세로 검사
			for (int i=0; i<3; i++) {
				int xRowCnt=0;
				int xColCnt=0;
				int oRowCnt=0;
				int oColCnt=0;
				
				for (int j=0; j<3; j++) {
					// 가로
					if (map[i][j] == 'X') xRowCnt++;
					else if (map[i][j] == 'O') oRowCnt++;
					
					// 세로
					if (map[j][i] == 'X') xColCnt++;
					else if (map[j][i] == 'O') oColCnt++;

					if (xRowCnt == 3) {
						xRow++;
					} else if (xColCnt == 3) {
						xCol++;
					} else if (oRowCnt == 3) {
						oRow++;
					} else if (oColCnt == 3) {
						oCol++;
					}
				}
			}
			
			// 오 대각선 검사
			int xCrossCnt = 0;
			int oCrossCnt = 0;
			for (int i=0; i<3; i++) {
				// (0,0)~(2,2)
				if (map[i][i] == 'X')	xCrossCnt++;
				else if (map[i][i] == 'O')	oCrossCnt++;

				if (xCrossCnt == 3)	xCross++;
				else if (oCrossCnt == 3)	oCross++;
			}
			
			// 왼 대각선 검사
			xCrossCnt = 0;
			oCrossCnt = 0;
			for (int i=0; i<3; i++) {
				// (0,2)~(2,0)
				if (map[i][2-i] == 'X')	xCrossCnt++;
				else if (map[i][2-i] == 'O')	oCrossCnt++;
				
				if (xCrossCnt == 3)	xCross++;
				else if (oCrossCnt == 3)	oCross++;
			}
			
			// 1) 불가능한 승리 조건
			if (xCnt == oCnt) {	// XOXO (O가 이겨야함)
				if (xRow>0 || xCol>0 || xCross>0) {
					System.out.println("invalid");
					continue;
				}
			} else if (xCnt == oCnt+1) {	// XOXOX (X가 이겨야함)
				if (oRow>0 || oCol>0 || oCross>0) {
					System.out.println("invalid");
					continue;
				}
			}
			
			// 2) 보드가 다 찼다면
			if (xCnt + oCnt == 9) {
				System.out.println("valid");
				continue;
			}
			
			// 3) 빙고 하나도 없다면 invalid
			if (xRow + xCol + xCross + oRow + oCol + oCross == 0) {
				System.out.println("invalid");
				continue;
			}
			
			// 필터들에게서 안걸러졌다면 가능
			System.out.println("valid");
		}
	}
}
