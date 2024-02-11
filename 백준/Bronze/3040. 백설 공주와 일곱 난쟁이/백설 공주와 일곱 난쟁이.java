import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static int[] target = new int[9];
	public static int[] answer = new int[7];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i=0; i<9; i++) {
			target[i] = Integer.parseInt(br.readLine());
		}
		
		sum(0, 0);
	}
	
	
	public static void sum(int depth, int start) {
		if (depth == 7) {
			int sum = 0;
			for (int i=0; i<7; i++) {
				sum += answer[i];
			}
			if (sum == 100) {
				for (int i=0; i<7; i++) {
					System.out.println(answer[i]);
				}
			}
			return;
		}
		
		for (int i=start; i<9; i++) {
			answer[depth] = target[i];
			sum(depth+1, i+1);
		}
		return;
	}
}
