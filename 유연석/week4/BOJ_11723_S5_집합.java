import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_11723_S5_집합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int flag=0;
		StringBuilder sb = new StringBuilder();
		for (int n=0; n<N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String inst = st.nextToken();
			int x=0;
			if (!inst.equals("all")&&!inst.equals("empty")) x = Integer.parseInt(st.nextToken());
			//명령어에 따라 비트 마스킹
			switch(inst) {
			case "add":
				flag  = flag | 1<<x;
				break;
			case "remove":
				flag = flag & ~(1<<x) ;
				break;
			case "check":
				sb.append((((flag & 1<<x)>0)?1:0)+"\n");
				break;
			case "toggle":
				flag = (flag&1<<x) == 1<<x ? flag&~(1<<x) : flag|1<<x;	//체크 되어 있으면 0으로 아니면 1로
				break;
			case "all":
				flag = flag|~0;
				break;
			case "empty":
				flag = flag&0;
				break;
			}
		}
		//output
		bw.write(sb.toString());
		//close
		bw.flush(); bw.close(); br.close();
	}

}
