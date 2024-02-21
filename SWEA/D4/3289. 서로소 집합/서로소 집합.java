import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int[] num;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int testCase=1; testCase<=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());	// 집합 개수		
			int m = Integer.parseInt(st.nextToken());	// 입력으로 주어지는 연산 개수
			
			num = new int[n+1];
			for (int i=0; i<n; i++) {
				num[i] = i;
			}
			
			int choice, a, b;
			for (int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				choice = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				if (choice == 0) {	// union, 합집합	0 a b
					union(a, b);
				} else if (choice == 1) {	// find, 같은 집합 포함되어 있는지 1 a b
					if (find(a) == find(b)) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) {
			num[b] = a;
		}
	}
	
	static int find(int x) {
		if (num[x] == x) {
			return x;
		}
		return num[x] = find(num[x]);
	}

}
