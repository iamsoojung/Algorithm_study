import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		if (N == 1) {
			System.out.println(1);
		} else {
			int diff = 1;	// 테두리 번호, 6의 배수만큼 커지게
			int room = 2;	// 방 번호
			
			while(room <= N) {
				room += diff * 6;
				diff++;
			}
			System.out.println(diff);
		}
	}
}
// 1 / 2~7(6) / 8~19(12) 
// 0, 6, 12, 18 ... 6의 배수만큼 커짐