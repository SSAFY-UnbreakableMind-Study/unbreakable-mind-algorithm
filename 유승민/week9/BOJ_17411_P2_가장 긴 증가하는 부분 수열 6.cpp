#include<iostream>
#include<string>
#include<vector>
#include<algorithm>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
using namespace std;
using int64 = int64_t;

const int MOD = 1e9 + 7;
int64 ans[1000001];
vector<pair<int, int64>> csum[1000001];

bool cmp(const pair<int, int64>& p1, const pair<int, int64>& p2) {
	return p2.first < p1.first;
}

int main() {

	fastio;

	int N, maxL = 0;
	cin >> N;

	csum[0].push_back({ 1e9 + 1, 0 });
	csum[0].push_back({ -1e9 - 1, 1 });
	vector<int> pi(1, -1e9 - 1);

	for (int i = 0; i < N; ++i) {
		int a, idx, iidx, sum;
		cin >> a;

		if (pi.back() < a) {
			idx = pi.size();
			maxL = max(maxL, idx);

			pi.push_back(a);
			csum[idx].push_back({ 1e9 + 1, 0 });
		}
		else {
			idx = lower_bound(pi.begin(), pi.end(), a) - pi.begin();
			pi[idx] = a;
		}

		iidx = upper_bound(csum[idx - 1].begin(), csum[idx - 1].end(), pair<int, int64>{ a, 0 }, cmp) - csum[idx - 1].begin();
		sum = (csum[idx - 1].back().second - csum[idx - 1][iidx-1].second + MOD) % MOD;
		ans[idx] += sum;
		ans[idx] %= MOD;

		csum[idx].push_back({ a, (sum + csum[idx].back().second)});

	}

	cout << maxL << " " << ans[maxL];

	return EXIT_SUCCESS;
}