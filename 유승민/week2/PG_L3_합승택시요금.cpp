#include <string>
#include <vector>
#include <algorithm>
#define INF INT32_MAX
using namespace std;

int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
    int64 answer = INF;
    
    int64 arr[201][201];
    
    //가중치 초기화
    for(int i = 1; i <= n; ++i){
        for(int j=1; j<=n; ++j){
            if(i != j) arr[i][j] = INF;
            else arr[i][j] = 0;
        }
    }
    
    //가중치 설정
    for(auto iter : fares){
        arr[iter[0]][iter[1]] = iter[2];
        arr[iter[1]][iter[0]] = iter[2];
    }
    
    //플로이드 워셜 알고리즘
    for(int i=1; i<=n; ++i){
        for(int j=1; j<=n; ++j){
            for(int k=1; k<=n; ++k){
                arr[j][k] = min(arr[j][k] , arr[j][i] + arr[i][k]);
            }
        }
    }
    
    //최솟값 찾기
    for(int i = 1; i<=n; ++i){
        answer = min(answer, arr[s][i] + arr[i][a] + arr[i][b]);
    }
    
    return answer;
}