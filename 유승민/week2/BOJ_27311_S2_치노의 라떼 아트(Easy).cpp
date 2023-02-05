#include <bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF 100
// INT32_MIN, INT64_MIN, INT32_MAX, INT64_MAX
using namespace std;
using int64 = int64_t;



int main() {
	fastio;

	int T;
	cin >> T;

	for (int t = 0; t < T; ++t) {
		char arr[10][10];
		int N, M;
		cin >> N >> M;

		// 큰 사각형 경계 찾기
		int totalCream = 0, msquare = 0, nsquare = 0;
		int min_x = INF, min_y = INF, max_x = -INF, max_y = -INF, first_x = -1, first_y = -1;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				cin >> arr[i][j];

				if (arr[i][j] == '#') {
					totalCream++;
					if (first_x == -1) {
						first_x = i;
						first_y = j;
					}
					min_x = min(min_x, i);
					min_y = min(min_y, j);
					max_x = max(max_x, i);
					max_y = max(max_y, j);
				}
			}
		}

		// 만약 #이 없으면 하트가 안됨
		if (first_x == -1 && first_y == -1) {
			cout << "0" << "\n";
			continue;
		}

		msquare = max_x - min_x + 1;

		//정사각형 모양이 아니면 하트가 안됨
		if (max_y - min_y + 1 != msquare) {
			cout << "0" << "\n";
			continue;
		}

		//작은 사각형 경계 찾기
		int m_cream = 0, n_cream = 0;
		int min_nx = INF, min_ny = INF, max_nx = -INF, max_ny = -INF, first_nx = -1, first_ny = -1;
		for (int i = min_x; i <= max_x; ++i) {
			for (int j = min_y; j <= max_y; ++j) {
				if (arr[i][j] == '#') m_cream++;
				else {
					if (first_nx == -1) {
						first_nx = i;
						first_ny = j;
					}
					min_nx = min(min_nx, i);
					min_ny = min(min_ny, j);
					max_nx = max(max_nx, i);
					max_ny = max(max_ny, j);
					n_cream++;
				}
			}
		}
		
		//. 이 없으면 하트가 안됨
		if (first_nx == -1 && first_ny == -1) {
			cout << "0" << "\n";
			continue;
		}

		// 모서리에 작은 사각형 경계가 없으면 하트가 안됨
		if (!((min_nx == min_x && min_ny == min_y) || (min_nx == min_x && max_ny == max_y) || (max_nx == max_x && min_ny == min_y) || (max_nx == max_x && max_ny == max_y)) ) {
			cout << "0" << "\n";
			continue;
		}

		nsquare = max_nx - min_nx + 1;

		//작은 사각형이 정사각형 모양이 아니면 하트가 안됨
		if (max_ny - min_ny + 1 != nsquare) {
			cout << "0" << "\n";
			continue;
		}

		// 작은사각형 안에 #이 섞여있으면 하트가 안됨
		if (totalCream != msquare * msquare - nsquare * nsquare) {
			cout << "0" << "\n";
			continue;
		}

		//하트 완성 !!
		cout << "1" << "\n";

	}

	return EXIT_SUCCESS;
}