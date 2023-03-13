#include<bits/stdc++.h>
#define fastio cin.tie(0)->ios::sync_with_stdio(0); cout.tie(0); setvbuf(stdout, nullptr, _IOFBF, BUFSIZ);
using namespace std;
using int64 = int64_t;

int R, C, tSum, rx, ry, maxx;
int arr[1000][1000];
char likeN[4] = { 'D', 'R', 'U', 'R' };
char likeF[4] = { 'R', 'D', 'L', 'D' };
char likeU[4] = { 'D', 'L', 'U', 'L' };
vector<pair<int, int>> greedy;

int main() {

	//fastio;

	cin >> R >> C;

	int dir = 0;
	for (int i = 0; i < R; ++i) {
		for (int j = 0; j < C; ++j) {
			cin >> arr[i][j];
			tSum += arr[i][j];
			if (i % 2 != j % 2 && R % 2 == 0 && C % 2 == 0) {
				greedy.push_back({ i, j });
			}
		}
	}

	if (R % 2 == 0 && C % 2 == 1) {
		for (int i = 0; i < C; ++i) {
			for (int j = 0; j < R - 1; ++j) {
				cout << likeN[dir];
			}
			if (i != C - 1) {
				dir = (dir + 1) % 4;
				cout << likeN[dir];
				dir = (dir + 1) % 4;
			}
		}
	}
	else if (R % 2 == 0 && C % 2 == 0) {
		bool tk = true;

		for (auto& iter : greedy) {
			if (maxx < tSum - arr[iter.first][iter.second]) {
				maxx = tSum - arr[iter.first][iter.second];
				rx = iter.first;
				ry = iter.second;
			}
		}

		for (int i = 0; i < R; i += 2) {
			if (i == rx || i + 1 == rx) {
				dir = 0;
				tk = false;
				for (int j = 0; j < ry; ++j) {
					cout << likeN[dir];
					dir = (dir + 1) % 4;
					cout << likeN[dir];
					dir = (dir + 1) % 4;
				}

				if (ry == C - 1) {
					if (i != R - 1 && i + 1 != R - 1)
						cout << 'D';
				}
				else {
					dir = (dir + 3) % 4;
					cout << likeN[dir];
					dir = (dir + 1) % 4;
				}

				for (int j = ry + 1; j < C-1; ++j) {
					cout << likeN[dir];
					dir = (dir + 1) % 4;
					cout << likeN[dir];
					dir = (dir + 1) % 4;
				}

				if (ry != C - 1 ) {
					if (i != R - 1 && i + 1 != R - 1)
						cout << 'D' << 'D';
					else cout << 'D';
				}


			}
			else {
				if (tk) {
					dir = 0;
					for (int i = 0; i < 2; ++i) {
						for (int j = 0; j < C - 1; ++j) {
							cout << likeF[dir];
						}
						if (i != R - 1) {
							dir = (dir + 1) % 4;
							cout << likeF[dir];
							dir = (dir + 1) % 4;
						}
					}
				}
				else {
					dir = 2;
					for (int j = i; j < i + 2; ++j) {
						for (int k = 0; k < C - 1; ++k) {
							cout << likeF[dir];
						} 
						if (j != R - 1) {
							dir = (dir + 1) % 4;
							cout << likeF[dir];
							dir = (dir + 1) % 4;
						}
					}
				}
			}
		}
	}
	else {
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C - 1; ++j) {
				cout << likeF[dir];
			}
			if (i != R - 1) {
				dir = (dir + 1) % 4;
				cout << likeF[dir];
				dir = (dir + 1) % 4;
			}
		}

	}



	return EXIT_SUCCESS;
}