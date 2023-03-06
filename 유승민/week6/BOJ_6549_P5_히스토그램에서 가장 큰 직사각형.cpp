#include <bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
#define INF INT32_MAX
// INT32_MIN, INT64_MIN, INT32_MAX, INT64_MAX
using namespace std;
using int64 = int64_t;

int N;

int main() {
	fastio;

	while (true) {
		cin >> N;

		if (!N) break;

		int64 Ans = 0;
		stack<pair<int64, int64>> st;
		st.push({ 0, 0 });

		for (int64 i = 1; i <= N; ++i) {
			int64 number, pivot = i;

			cin >> number;

			while (st.top().first > number) {
				int64 height = st.top().first;
				int64 idx = st.top().second;

				Ans = max(Ans, height * (i - idx));
				pivot = idx;
				st.pop();
			}

			if (st.top().first != number)
				st.push({ number, pivot });

		}

		while (!st.empty()) {
			int64 height = st.top().first;
			int64 idx = st.top().second;

			Ans = max(Ans, height * (N + 1 - idx));
			st.pop();
		}

		cout << Ans << "\n";

	}


	return EXIT_SUCCESS;
}