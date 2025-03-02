import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
	    
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = null;
	    
	    int N = Integer.parseInt(br.readLine());
	    int[] sizeArr = new int[6];
	    
	    st = new StringTokenizer(br.readLine());
	    for (int i=0; i<6; i++) {
	        sizeArr[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    st = new StringTokenizer(br.readLine());
	    int T = Integer.parseInt(st.nextToken());
	    int P = Integer.parseInt(st.nextToken());
	    
	    int cnt = 0;
	    for (int i=0; i<6; i++) {
	        cnt += sizeArr[i] / T;
	        cnt = sizeArr[i] % T > 0 ? cnt+1 : cnt;
	    }
	    
	    System.out.println(cnt);
	    System.out.println(N/P + " " + N%P);
	}
}
