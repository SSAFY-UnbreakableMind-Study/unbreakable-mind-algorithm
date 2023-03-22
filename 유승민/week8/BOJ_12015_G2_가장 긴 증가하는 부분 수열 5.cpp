#include<bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
using namespace std;
using int64 = int64_t;

	

int main() {

	fastio;

	int N;
	cin >> N;

	vector<int> ar(N), arr(1, -1000000001), pi(N), ans;
	for (int i = 0; i < N; ++i) {
		cin >> ar[i];
		if (arr.back() < ar[i]) {
			pi[i] = arr.size();
			arr.push_back(ar[i]);
		}
		else {
			int idx = lower_bound(arr.begin(), arr.end(), ar[i]) - arr.begin();
			arr[idx] = ar[i];
			pi[i] = idx;
		}
	}

	int maxL = arr.size() - 1;
	cout << maxL << "\n";
	
	for (int i = N-1; i >=0; --i) {
		if (pi[i] == maxL) {
			ans.push_back(ar[i]);
			maxL--;
		}
	}

	for (int i = ans.size() - 1; i >= 0; --i) {
		cout << ans[i] << " ";
	}

	return EXIT_SUCCESS;
}