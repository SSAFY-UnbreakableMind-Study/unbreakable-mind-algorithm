#include <bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF INT32_MAX
// INT32_MIN, INT64_MIN, INT32_MAX, INT64_MAX
using namespace std;
using int64 = int64_t;

int T, N, D, DIVN = 1000000007;
vector<tuple<int, int, int64>> container[101];

class matrix {
public:
	int64 Arr[21][21] = { 0, };

	matrix operator*(matrix& ref) {
		matrix tmp;
		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j <= N; ++j) {
				tmp.Arr[i][j] = 0;
			}
		}

		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j <= N; ++j) {
				if (Arr[i][j] != 0) {
					for (int k = 1; k <= N; ++k) {
						if (ref.Arr[j][k] != 0) {
							tmp.Arr[i][k] = ((tmp.Arr[i][k] % DIVN) + ((Arr[i][j] % DIVN) * (ref.Arr[j][k] % DIVN) % DIVN) % DIVN);
						}
					}
				}
			}
		}

		return tmp;
	}
};

matrix mat[101], ans;

inline matrix splitMatrix(matrix& m, int square) {
	if (square == 1) return m;
	else if (square % 2) return splitMatrix(m, square - 1) * m;

	matrix half = splitMatrix(m, square / 2);

	return half * half;
}

int main() {
	fastio;

	//지도주기, 거점 개수, 활동 시간 
	cin >> T >> N >> D;

	for (int i = 1; i <= N; ++i) {
		ans.Arr[i][i] = mat[0].Arr[i][i] = 1;
	}

	//지도 주기
	for (int i = 1; i <= T; ++i) {

		//생성되는 통로 개수
		int M;
		cin >> M;

		//통로 맵핑
		for (int j = 1; j <= M; ++j) {
			int start, end;
			int64 dis;

			cin >> start >> end >> dis;
			container[i].push_back({ start, end, dis });

		}
	}

	//거듭제곱을 위한 계산
	int divN = D / T, modN = D % T;

	//사이클이 한번 이상일 
	if (divN) {
		for (int i = 1; i <= T; ++i) {
			for (auto& iter : container[i]) {
				for (int j = 1; j <= N; ++j) {
					mat[i].Arr[j][get<1>(iter)] = ((mat[i].Arr[j][get<1>(iter)] % DIVN) + (((mat[i - 1].Arr[j][get<0>(iter)] % DIVN) * (get<2>(iter) % DIVN) % DIVN) % DIVN) % DIVN);
				}
			}
		}

		mat[T] = splitMatrix(mat[T], divN);
		mat[T] = mat[T] * mat[modN];
		ans = ans * mat[T];
	}

	//사이클이 한번 미만일 때
	else {
		for (int i = 1; i <= modN; ++i) {
			for (auto& iter : container[i]) {
				for (int j = 1; j <= N; ++j) {
					mat[i].Arr[j][get<1>(iter)] = ((mat[i].Arr[j][get<1>(iter)] % DIVN) + (((mat[i - 1].Arr[j][get<0>(iter)] % DIVN) * (get<2>(iter) % DIVN) % DIVN) % DIVN) % DIVN);
				}
			}
		}

		ans = ans * mat[modN];
	}

	for (int i = 1; i <= N; ++i) {
		for (int j = 1; j <= N; ++j) {
			cout << ans.Arr[i][j] % DIVN << " ";
		}
		cout << "\n";
	}

	return EXIT_SUCCESS;
} 
