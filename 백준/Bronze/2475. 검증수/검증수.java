import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int tmp, verifyNum = 0;
		for (int i=0; i<5; i++) {
			tmp = Integer.parseInt(st.nextToken());
			verifyNum += tmp*tmp;
		}
		System.out.println(verifyNum%10);
	}
}

// 검증수 => 고유번호의 처음 5자리에 들어가는 5개의 숫자를 각각 제곱한 수의 합을 10으로 나눈 나머지