import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

	static int N, K, size, answer;
	static String s;
	static Set<String> set;
	static ArrayList<String> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 숫자의 개수
			K = Integer.parseInt(st.nextToken());	// 크기 순서
			
			s = br.readLine();
			
			size = N/4;		// 생성될 문자열 크기
			set = new HashSet<>();
			
			for (int i=0; i<size; i++) {	// 만들 문자열 크기만큼
				int start = 0;
				int end = size;
				
				for (int j=0; j<4; j++) {	// 4개변 모두
					set.add(s.substring(start, end));
					start += size;
					end += size;
				}
				// 오른쪽으로 한칸씩 이동
				s = s.substring(s.length()-1, s.length())
						+ s.substring(0, s.length()-1);
			}
			
			list = new ArrayList<>(set);
			Collections.sort(list, Collections.reverseOrder());
			
			answer = Integer.parseInt(list.get(K-1), 16);	// parseInt() <-> toString()
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");			
		}
		System.out.print(sb.toString());
	}
}
