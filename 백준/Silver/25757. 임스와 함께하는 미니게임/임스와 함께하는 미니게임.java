import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		HashMap<String, Integer> map = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 플레이 신청 횟수 
		char game = st.nextToken().charAt(0);		// 플레이할 게임 종류
		
		for (int i=0; i<N; i++) {
			String s = br.readLine();
			if (map.containsKey(s)) {
				map.replace(s, map.get(s) + 1);
			} else {
				map.put(s, 1);
			}
		}
		
		switch(game) {
		case 'Y':
			System.out.println(map.size());
			break;
		case 'F':
			System.out.println(map.size()/2);
			break;
		case 'O':
			System.out.println(map.size()/3);
			break;
		}
	}
}