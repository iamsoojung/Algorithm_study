import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] tree;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		tree = new int[N][2];	// 인덱스 : 노드, [0] : 왼자식, [1] : 오자식
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			char start = st.nextToken().charAt(0);
			
			for (int j=0; j<2; j++) {
				char c = st.nextToken().charAt(0);
				if (c != '.') {
					tree[(int)start - 65][j] = (int)c - 65;
				} else {
					tree[(int)start - 65][j] = -1;
				}
			}
		}
		
		PreOrder(0);
		sb.append('\n');

		InOrder(0);
		sb.append('\n');
		
		PostOrder(0);
		sb.append('\n');
		
		System.out.println(sb);
		
	}
	
	static void PreOrder(int cur) {
		
		sb.append((char)('A'+cur));
		
		if (tree[cur][0] != -1) {
			PreOrder(tree[cur][0]);
		}
		
		if (tree[cur][1] != -1) {
			PreOrder(tree[cur][1]);
		}
	}
	
	static void InOrder(int cur) { 
		
		if (tree[cur][0] != -1) {
			InOrder(tree[cur][0]);
		}

		sb.append((char)('A'+cur));
		
		if (tree[cur][1] != -1) {
			InOrder(tree[cur][1]);
		}
		
	}
	
	static void PostOrder(int cur) {
		
		if (tree[cur][0] != -1) {
			PostOrder(tree[cur][0]);
		}
		
		if (tree[cur][1] != -1) {
			PostOrder(tree[cur][1]);
		}

		sb.append((char)('A'+cur));
		
	}
}
