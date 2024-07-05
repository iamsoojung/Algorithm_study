import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		Set<String> set = new HashSet<>();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 플레이 신청 횟수 
		String game = st.nextToken();		// 플레이할 게임 종류
		int gameN = game.equals("Y") ? 1 : game.equals("F") ? 2 : 3;
		
		for (int i=0; i<N; i++) {
			set.add(br.readLine());
		}
		
		System.out.print(set.size() / gameN);
	}
}