## BOJ_9461_S3_파도반수열
- dynamic programming
- https://www.acmicpc.net/problem/9461



## 풀이

점화식을 만들었어요.<br/>
a(N) = a(N-1) + a(N-5)<br/>

~~~java
for (int i = 6; i < 101; i++) {
			arr[i] = arr[i-1]+ arr[i-5];
		}
~~~

## 소스코드
~~~java
import java.util.Scanner;

public class BOJ_9461_S3_파도반수열 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		long[] arr = new long[101];
		arr[1]= 1;
		arr[2] = 1;
		arr[3] = 1;
		arr[4] = 2;
		arr[5] = 2;
		for (int i = 6; i < 101; i++) {
			arr[i] = arr[i-1]+ arr[i-5];
		}
		
		for (int i= 0; i <t; i++) {
			int n = sc.nextInt();
			System.out.println(arr[n]);
		}
	}
}
~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 17724KB| 220ms|

