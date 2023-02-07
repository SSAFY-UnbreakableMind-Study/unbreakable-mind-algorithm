## BOJ*1759_G5*암호만들기

- 조합론, 브루트포스, 백트래킹, 수학
- https://www.acmicpc.net/problem/{문제번호}

## 풀이

- 알파벳 순서대로 나오기 위한 정렬
  우선 입력받은 문자열을 char[]에 넣고 이를 정렬한다.
- 순열 재귀 호출 (알고리즘 Live 시간에 배운 순열을 활용)
  만들고자 하는 문자열 길이만큼 재귀 호출을 하여 한글자씩 넣어서 순열, 조합을 만들어야 한다.
  라이브에서는 boolean[]을 이용하여 사용한 문자를 masking 하여 처리하였지만,
  그 방식으로 이 문제를 접근하면, acsi 등 알파벳 순서를 맞추지 못하는 경우가 발생한다.
  그렇기에 메서드의 파라미터로 만들고 있는 문자열에 넣은 문자 개수 (cnt)와
  내가 사용할 수 있는 알파벳 범위들 중 제일 마지막에 사용한 문자의 인덱스 (idx)를 주어,
  문자열에서 사용된 문자보다 알파벳 순서 상 앞에 있는 알파벳은 다음 문자로 추가될 수 없다는 제약 조건을 건다. -재귀 종료
  문자열 길이를 만족하여 재귀 호출을 종료할 땐, 자음, 모음의 개수 체크를 하여
  조건에 만족하는 경우만 정답으로 출력한다.

---

추가적으로 정답 출력을 잘 못해서 메모리 초과가 나왔는데,
오답이어서 발생한 줄 모르고 공간 복잡도를 줄일 방법에 대한 고민을 계속하였다.
permutation에 parameter로 넣은 char[]이나 class의 static char[]나 큰 차이는 없었다.

또한, Bufferedwrite를 위해 StringBuilder에 쭉 넣어두고, 마지막에 BR.write()로 하는 것이
System.out.println으로 찍는 것보단 시간이 미약하게 나마 감소하였다.

```java
/*풀이 코드 작성*/
//output 개수 만족하면 재귀 탈출
if (cnt == L) {
    //정답에 추가하려면 자음, 모음 수 체크
    if(isValid(output)) sb.append(String.valueOf(output)+"\n");
    return;
}
//재귀 구현부
for (int i=idx; i<C; i++) {
    output[cnt] = charRange[i];
    permutation(output, cnt+1, i+1);
}
```

## 소스코드

```java
package my;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import java.util.StringTokenizer;

public class BOJ_1759_G5_암호만들기 {
	static char[] charRange;
	static int L, C;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());	//r
		C = Integer.parseInt(st.nextToken());	//n
		charRange = new char[C];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i=0; i<C; i++) {
			charRange[i] = st.nextToken().charAt(0);
		}

		//sorting
		Arrays.sort(charRange);
		//순열로 출력
		permutation(new char[L], 0, 0);

		//output
		bw.write(sb.toString());
		//close
		bw.close();
		br.close();
	}

	private static void permutation(char[] output, int cnt, int idx) throws IOException {
		//output 개수 만족하면 재귀 탈출
		if (cnt == L) {
			//정답에 추가하려면 자음, 모음 수 체크
			if(isValid(output)) sb.append(String.valueOf(output)+"\n");
			return;
		}
		//재귀 구현부
		for (int i=idx; i<C; i++) {
			output[cnt] = charRange[i];
			permutation(output, cnt+1, i+1);
		}
	}

	//순열로 만든 문자열이 모음 1개, 자음 2개 조건 성립하는지
	private static boolean isValid(char[] str) {
		int vowelCnt = 0;	//모음 개수
		for (char ch:str) {
			//문자열에서 하나의 문자가 모음인지 체크
			switch (ch) {
			case 'a': case 'e': case 'i': case 'o': case'u':
				vowelCnt++;
				break;
			}
		}
		if (vowelCnt >= 1 && L-vowelCnt >= 2)  return true;
		else return false;
	}

}

```

## 결과

| 메모리   | 시간   |
| -------- | ------ |
| 14340 KB | 128 ms |
