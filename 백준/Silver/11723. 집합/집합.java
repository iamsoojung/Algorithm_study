import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int M = Integer.parseInt(br.readLine());
		int bit = 0;
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			String op = st.nextToken();
			int x = 0;
			
			switch(op) {
				case "add":
					x = Integer.parseInt(st.nextToken());
					bit |= (1<<x);
					break;
				case "remove":
					x = Integer.parseInt(st.nextToken());
					if ((bit & (1<<x)) != 0) {	// 존재한다면
						bit ^= (1<<x);
					}
					break;
				case "check":
					x = Integer.parseInt(st.nextToken());
					if ((bit & (1<<x)) != 0) {
						sb.append("1\n");
					} else {
						sb.append("0\n");
					}
					break;
				case "toggle":
					x = Integer.parseInt(st.nextToken());
					if ((bit & (1<<x)) != 0) {
						bit ^= (1<<x);
					} else {
						bit |= (1<<x);
					}
					break;
				case "all":
					for (int j=1; j<=20; j++) {
						bit |= (1<<j);
					}
					break;
				case "empty":
					bit = 0;
					break;
			}
		}
		System.out.println(sb.toString());
	}
}