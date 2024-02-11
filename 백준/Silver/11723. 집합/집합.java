import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		int bitSet = 0;
		
		for (int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String op = st.nextToken();
			
			if (st.countTokens() == 0) {
				if (op.equals("all")) {
					for (int j=1; j<=20; j++) {
						bitSet |= (1<<j);	// j번째가 1이 되도록
					}
					continue;
				}
				if (op.equals("empty")) {
					bitSet = 0;
					continue;
				}
			}
			else {
				int x = Integer.parseInt(st.nextToken());
				switch(op) {
					case "add":
						bitSet = bitSet | (1<<x);	// x자리를 1로 (OR)
						break;
					case "remove":
						if ((bitSet & (1<<x)) != 0) {	// 있다면
							bitSet = bitSet ^ (1<<x);	// x자리를 0으로 (XOR)
						};
						break;
					case "check":
						if ((bitSet & (1<<x)) != 0) {
							sb.append(1 + "\n");
						} else {
							sb.append(0 + "\n");
						}
						break;
					case "toggle":
						if ((bitSet & (1<<x)) != 0) {	// 있다면
							bitSet = bitSet ^ (1<<x);	// 제거 (XOR)
						} else {
							bitSet = bitSet | (1<<x);	// 추가 (OR)
						}
						break;
				}
			}
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}
