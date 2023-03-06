#include <bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF INT32_MAX
// INT32_MIN, INT64_MIN, INT32_MAX, INT64_MAX
using namespace std;
using int64 = int64_t;

int N, K, Ans = -1;
int vst[2][500001];
queue<pair<int, int>> q;

bool isValid(int curPos) {
	return (curPos >= 0 && curPos <= 500000);
}

int main() {
	fastio;

	for (int i = 0; i < 2; ++i) {
		memset(vst[i], -1, sizeof(vst[i]));
	}

	cin >> N >> K;

	vst[0][N] = 0;
	q.push({ N, 0 });

	while (!q.empty()) {
		int pos = q.front().first;
		int tm = q.front().second;

		q.pop();

		int plusPos = pos + 1;
		int minusPos = pos - 1;
		int tpPos = pos * 2;

		if (isValid(plusPos) && vst[(tm + 1) % 2][plusPos] == -1) {
			vst[(tm + 1) % 2][plusPos] = tm + 1;
			q.push({ plusPos, tm + 1 });
		}

		if (isValid(minusPos) && vst[(tm + 1) % 2][minusPos] == -1) {
			vst[(tm + 1) % 2][minusPos] = tm + 1;
			q.push({ minusPos, tm + 1 });
		}

		if (isValid(tpPos) && vst[(tm + 1) % 2][tpPos] == -1) {
			vst[(tm + 1) % 2][tpPos] = tm + 1;
			q.push({ tpPos, tm + 1 });
		}

	}

	for (int i = 0; K + i <= 500000; ++i) {
		K += i;
		if (vst[(i % 2)][K] != -1 && vst[(i % 2)][K] <= i) {
			Ans = i;
			break;
		}
	}

	cout << Ans;

	return EXIT_SUCCESS;
}