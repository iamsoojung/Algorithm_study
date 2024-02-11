import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int N, M;
	public static int[] switch_arr;
	public static int[][] student;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());	// 스위치 갯수
		StringTokenizer st = new StringTokenizer(br.readLine());
		switch_arr = new int[N];	
		for (int i=0; i<N; i++) {
			switch_arr[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());	// 학생 수
		student = new int[M][2];
		for (int j=0; j<M; j++) {
			st = new StringTokenizer(br.readLine());
			student[j][0] = Integer.parseInt(st.nextToken());	// 성별 (남 1, 여 2)
			student[j][1] = Integer.parseInt(st.nextToken());	// 받은 수
		}
		
		for (int j=0; j<M; j++) {
			if (student[j][0] == 1) {
				boy_check(student[j][1]);
			} 
			else if (student[j][0] == 2){
				int tmp = student[j][1]-1;
				if (switch_arr[tmp] == 1) {
					switch_arr[tmp] = 0;
				} else {
					switch_arr[tmp] = 1;
				}
				girl_check(student[j][1], 1);
			}
		}
		
		for (int i=0; i<N; i++) {
			if (i!=0 && i%20 == 0) {
				System.out.println();
			}
			System.out.print(switch_arr[i] + " ");
		}
	}
	
	public static void boy_check(int amount) {
		int i = 1;
		int idx = i*amount-1;
		while (idx < N) {
			if (switch_arr[idx] == 1) {
				switch_arr[idx] = 0;
			} else {
				switch_arr[idx] = 1;
			}
			i++;
			idx = i*amount-1;
		}
	}
	
	public static void girl_check(int amount, int depth) {
		int idx = amount - 1;
		if (0 <= (idx-depth) && (idx+depth) < N && 
				switch_arr[idx-depth] == switch_arr[idx+depth]) {
			if (switch_arr[idx-depth] == 1) {
				switch_arr[idx-depth] = 0;
				switch_arr[idx+depth] = 0;
			} else {
				switch_arr[idx-depth] = 1;
				switch_arr[idx+depth] = 1;
			}
			girl_check(amount, depth+1);
		}
	}

}

/*
 * ON : 1 / OFF : 0
 * 남 : 자신이 받은 수의 배수
 * 여 : 자신이 받은 수의 좌우대칭으로 가장 큰 범위
 * 
 */