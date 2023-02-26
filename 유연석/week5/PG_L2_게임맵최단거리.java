package week5;

import java.util.*;

class Solution {
    static int N, M;
    static int[][] delta = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static boolean[][] isVisited;
    static int answer = -1;

    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        isVisited = new boolean[N][M];

        bfs(new Pos(0, 0, 1), maps);

        return answer;
    }

    public static void bfs(Pos start, int[][] map) {
        Queue<Pos> queue = new ArrayDeque<>();
        queue.offer(start);
        isVisited[start.y][start.x] = true;

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();
            if (cur.y == N - 1 && cur.x == M - 1) {
                answer = cur.depth;
                return;
            }

            for (int di = 0; di < 4; di++) {
                int ni = cur.y + delta[di][0];
                int nj = cur.x + delta[di][1];
                if (isValid(ni, nj) && map[ni][nj] == 1 && !isVisited[ni][nj]) {
                    isVisited[ni][nj] = true;
                    queue.offer(new Pos(ni, nj, cur.depth + 1));
                }
            }
        }

    }

    public static boolean isValid(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < M;
    }
}

class Pos {
    int y;
    int x;
    int depth;

    public Pos(int y, int x, int depth) {
        this.y = y;
        this.x = x;
        this.depth = depth;
    }
}