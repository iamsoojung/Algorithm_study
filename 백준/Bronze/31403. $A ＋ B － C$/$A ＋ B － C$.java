import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int A = Integer.parseInt(br.readLine());
	    int B = Integer.parseInt(br.readLine());
	    int C = Integer.parseInt(br.readLine());
	    
	    String AB = String.valueOf(A) + String.valueOf(B);
	    
	    System.out.println(A+B-C);
	    System.out.println(Integer.parseInt(AB)-C);
	}
}