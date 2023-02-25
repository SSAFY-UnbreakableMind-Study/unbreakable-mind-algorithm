package week5;

import java.util.*;

class Solution {
    static int N;
    static int[][] coms;
    static int answer = 0;
    static boolean[] isVisited;

    public int solution(int n, int[][] computers) {
        N = n;
        coms = computers;
        isVisited = new boolean[N];

        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                dfs(i);
                answer++;
            }
        }

        return answer;
    }

    static void dfs(int cur) {
        isVisited[cur] = true;

        for (int i = 0; i < N; i++) {
            if (coms[cur][i] == 1 && !isVisited[i]) {
                dfs(i);
            }
        }
    }
}