import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	static int[] tri = new int[3];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			tri[0] = Integer.parseInt(st.nextToken());
			tri[1] = Integer.parseInt(st.nextToken());
			tri[2] = Integer.parseInt(st.nextToken());
			
			if (tri[0] == 0 && tri[1] == 0 && tri[2] == 0)	break;

			Arrays.sort(tri);
			
			// 삼각형 충족 조건
			if (tri[2] >= tri[0]+tri[1]) {
				sb.append("Invalid\n");
				continue;
			}
			
			// 삼각형 정의
			if (tri[0] == tri[1]) {
				if (tri[1] == tri[2]) {
					sb.append("Equilateral\n");
				} else {
					sb.append("Isosceles\n");
				}
			} else {
				if (tri[1] == tri[2]) {
					sb.append("Isosceles\n");
				} else {
					if (tri[0] == tri[2]) {
						sb.append("Isosceles\n");
					} else {
						sb.append("Scalene\n");						
					}
				}
			}
		}
		System.out.println(sb.toString());
	}
}