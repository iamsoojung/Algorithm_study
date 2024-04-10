import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 7:30 ~
public class Main {

	static int N, answer = Integer.MIN_VALUE;		// 이닝 수
	static int[][] round;	// 각 이닝마다 얻는 결과
	static int[] order = new int[10];			// 타순 저장
	static boolean[] select = new boolean[10];	// 타순 구할때 선택 처리
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		round = new int[N][10];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j=1; j<10; j++) {
				round[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1. 타순 정하기 (순열)
		order[4] = 1;		// 4번 타자에 1번 선수 넣음
		select[4] = true;	// 4번 타자 선택됨
		perm(2);	// 2번 선수부터 넣어주기 ~
		
		System.out.print(answer);
	}

	static void perm(int cnt) {
		if (cnt == 10) {
//			for (int i=0; i<9; i++) {
//				System.out.print(order[i] + " ");
//			}System.out.println();
			
			int result = play();	// 2. N라운드 진행해보기
			answer = Math.max(answer, result);	// 3. 얻을 수 있는 최대 점수로 갱신하기
			return;
		}
		
		for (int i=1; i<10; i++) {	// 현 선수를 1~9자리에 다 넣어보기
			if (!select[i]) {
				select[i] = true;		
				order[i] = cnt;
				perm(cnt+1);
				select[i] = false;
			}
		}		
	}
	
	static int play() {
		int player = 1;	// 1번 선수부터 시작
		int score = 0;
		
		for (int i=0; i<N; i++) {
			boolean[] base = new boolean[4];	// 베이스에 선수 있는지 없는지
			int out = 0;	// 아웃 수
			
			while(out < 3) {
				switch(round[i][order[player]]) {
				case 0:		// 아웃
					out++;
					break;
				case 1:
					if (base[3]) {
						score++;
						base[3] = false;
					}
					if (base[2]) {
						base[3] = true;
						base[2] = false;
					}
					if (base[1]) {
						base[2] = true;
						base[1] = false;
					}
					base[1] = true;
					break;
				case 2:
					if (base[3]) {
						score++;
						base[3] = false;
					}
					if (base[2]) {
						score++;
						base[2] = false;
					}
					if (base[1]) {
						base[3] = true;
						base[1] = false;
					}
					base[2] = true;
					break;
				case 3:
					if (base[3]) {
						score++;
						base[3] = false;
					}
					if (base[2]) {
						score++;
						base[2] = false;
					}
					if (base[1]) {
						score++;
						base[1] = false;
					}
					base[3] = true;
					break;
				case 4:
					if (base[3]) {
						score++;
						base[3] = false;
					}
					if (base[2]) {
						score++;
						base[2] = false;
					}
					if (base[1]) {
						score++;
						base[1] = false;
					}
					score++;
					break;
				}
				player++;
				
				if (player > 9) {
					player = 1;
				}
			}
		}
		return score;
	}
}
