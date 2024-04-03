import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int K, answer=0;
	static int[][] magnet = new int[4][8];
	static int[][] rInfo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=T; testCase++) {

			K = Integer.parseInt(br.readLine());	// 자석 회전시키는 횟수 K
			for (int i=0; i<4; i++) {	// 4 번 자석까지 각각 8 개 날의 자성정보
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			rInfo = new int[K][2];
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				rInfo[i][0] = Integer.parseInt(st.nextToken())-1;	// 자석 번호
				rInfo[i][1] = Integer.parseInt(st.nextToken());	// 회전 방향 (1 : 시계, -1 : 반시계)
			}
			
			// 회전
			for (int i=0; i<K; i++) {
				rotate(i);	
			}

//			for (int i=0; i<4; i++) {
//				for (int j=0; j<8; j++) {
//					System.out.print(magnet[i][j] + " ");
//				}System.out.println();
//			}
			
			// 점수 계산
			answer = getScore();
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void rotate(int idx) {
		// 왼쪽 주위 자석 회전 (붙어 있는 날의 자성과 다를 경우에 반대 방향으로 회전)
		rotateLeft(rInfo[idx][0], rInfo[idx][1]);
		
		// 오른쪽 주위 자석 회전 (붙어 있는 날의 자성과 다를 경우에 반대 방향으로 회전)
		rotateRight(rInfo[idx][0], rInfo[idx][1]);
		
		// 자석 회전
		rotateMe(rInfo[idx][0], rInfo[idx][1]);
	}
	
	static void rotateLeft(int mNum, int mDir) {	// target : 비교할 자성, mNum : 비교할 자석의 번호
		// 맨앞 자석이라면 종료
		if (mNum == 0) {
			return;
		}
		
		//target과 num-1 자석의 자성이 같다면 종료
		if (magnet[mNum-1][2] == magnet[mNum][6]) {
			return;
		}
		
		if (mDir == 1) {
			if (0 <= mNum-1) {
				rotateLeft(mNum-1, -1);
			}
			rotateMe(mNum-1, -1);
		} else {
			if (0 <= mNum-1) {
				rotateLeft(mNum-1, 1);
			}
			rotateMe(mNum-1, 1);
		}
	}
	
	static void rotateRight(int mNum, int mDir) {	// target : 비교할 자성, mNum : 비교할 자석의 번호
		// 맨끝 자석이라면 종료
		if (mNum == 3) {
			return;
		}
		
		// target과 num+1 자석의 자성이 같다면 종료
		if (magnet[mNum][2] == magnet[mNum+1][6]) {
			return;
		}
		
		if (mDir == 1) {
			if (mNum+1 <= 3) {
				rotateRight(mNum+1, -1);				
			}
			rotateMe(mNum+1, -1);
		} else {
			if (mNum+1 <= 3) {
				rotateRight(mNum+1, 1);
			}
			rotateMe(mNum+1, 1);
		}
	}
	
	static void rotateMe(int mNum, int mDir) {
		if (mDir == 1) {	// 시계 방향
			// 01234567
			// 70123456
			int end = magnet[mNum][7];
			for (int i=6; i>=0; i--) {
				magnet[mNum][i+1] = magnet[mNum][i];
			}
			magnet[mNum][0] = end;
			
		} else {	// 반시계 방향
			// 01234567
			// 12345670
			int start = magnet[mNum][0];
			for (int i=0; i<=6; i++) {
				magnet[mNum][i] = magnet[mNum][i+1];
			}
			magnet[mNum][7] = start;
		}
	}
	
	static int getScore() {
		int answer = 0;
		
		if (magnet[0][0] == 1)	answer += 1;
		if (magnet[1][0] == 1)	answer += 2;
		if (magnet[2][0] == 1)	answer += 4;
		if (magnet[3][0] == 1)	answer += 8;
		
		return answer;
	}
}
