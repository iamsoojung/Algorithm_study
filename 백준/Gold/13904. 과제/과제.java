import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, maxDay;
	static ArrayList<int[]> homework;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		homework = new ArrayList<>();
		maxDay = 0;
		
		int d, w;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());	// 마감일까지 남은 일수
			w = Integer.parseInt(st.nextToken());	// 점수
			
			homework.add(new int[] {d, w});
			maxDay = Math.max(maxDay, d);
		}
		
		int answer = 0;
		for (int i=maxDay; i>0; i--) {	// 끝에서부터
			answer += getMaxScore(i);
		}
		
		System.out.print(answer);
	}
	
	static int getMaxScore(int day) {
		
		int idx=0, max=0;
		for (int i=0; i<homework.size(); i++) {
			if (homework.get(i)[0] >= day && homework.get(i)[1] > max) {
				max = homework.get(i)[1];
				idx = i;
			}
		}
		
		if (max != 0) {
			homework.remove(idx);
		}
		return max;
	}
}
