import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759_G5_암호만들기 {
    static int r, n;
    static char[] data;

    // 모음 자음 개수가 조건을 만족하는지 확인
    static boolean isValid(char[] arr) {
        int cnt = 0;
        for (char c : arr) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                cnt++;
            }
        }
        if (cnt > 0 && r - cnt > 1)
            return true;
        return false;
    }

    // i : arr의 인덱스, j : data의 인덱스
    static void combi(int i, int j, char[] arr) {
        if (i == r) { // i가 r이 됐다는 것은 arr가 완성됐다는 것. -> 출력
            if (isValid(arr))
                System.out.println(arr);
            return;
        }
        if (j == n)
            return; // j가 n이 됐다는 것은 data 끝까지 탐색했다는 것. -> 재귀 종료
        arr[i] = data[j]; // arr에 data 요소 입력
        combi(i + 1, j + 1, arr); // arr, data 모두 다음 인덱스로 재귀 호출
        combi(i, j + 1, arr); // arr는 그대로, data는 다음 인덱스로 재귀 호출
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        data = new char[n];
        for (int i = 0; i < n; i++) {
            data[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(data); // 데이터 정렬
        combi(0, 0, new char[r]);

    }

}
