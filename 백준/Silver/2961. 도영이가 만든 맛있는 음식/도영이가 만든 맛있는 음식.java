import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	public static int dif = Integer.MAX_VALUE;
	public static int S=1, B=0;
	public static int N;
	public static int[][] taste;
	public static boolean[] isSelected;
	public static int selectCnt = 0;	// 적어도 하나를 사용해야함
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		taste = new int[N][2];	// 신맛, 쓴맛
		isSelected = new boolean[N];	// 선택 여부
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			taste[i][0] = Integer.parseInt(st.nextToken());
			taste[i][1] = Integer.parseInt(st.nextToken());
		}
		
		diff(0, S, B);
		bw.write("" + dif);
		bw.close();
		br.close();
	}
	
	public static void diff(int cnt, int s, int b) {
		if (cnt == N) {	// 모든 원소 고려 시
			if (dif != Math.min(dif, Math.abs(s-b)) && selectCnt!=0) {
				dif = Math.abs(s-b);
			}
			return;
		}
		
		isSelected[cnt] = true;
		selectCnt++;
		diff(cnt+1, s*taste[cnt][0], b+taste[cnt][1]);
		isSelected[cnt] = false;
		selectCnt--;
		diff(cnt+1, s, b);
	}

}
