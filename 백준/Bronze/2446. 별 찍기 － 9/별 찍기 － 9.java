import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for (int i=0; i<N-1; i++) {
			for (int k=0; k<i; k++) {
				System.out.printf(" ");				
			}
			for (int k=i; k<2*N-1-i; k++) {
				System.out.printf("*");
			}
			System.out.println();
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N-1-i; j++) {
				System.out.printf(" ");
			}
			for (int j=0; j<i*2+1; j++) {
				System.out.printf("*");
			}
			System.out.println();
		}
	}
}