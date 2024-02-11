import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int N;
	static int num[];
	static boolean isVisited[];
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		isVisited = new boolean[N+1];
		
		perm(0);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void perm(int depth) {
		if (depth == N) {
			for (int n : num) {
				sb.append(n).append(' ');
			}
			sb.append('\n');
			return;
		}
		for (int i=1; i<=N; i++) {
			if (isVisited[i])	continue;
			isVisited[i] = true;
			num[depth] = i;
			perm(depth+1);
			isVisited[i] = false;
		}
	}
}
