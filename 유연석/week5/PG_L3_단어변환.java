package week5;

class Solution {
    static String[] words;
    static String begin, target;
    static boolean[] isVisited;
    static int answer = Integer.MAX_VALUE;

    public int solution(String b, String t, String[] temp) {
        begin = b;
        target = t;
        words = temp;
        isVisited = new boolean[words.length];
        dfs(begin, 0);
        if (answer == Integer.MAX_VALUE)
            answer = 0;
        return answer;
    }

    static void dfs(String cur, int depth) {
        if (target.equals(cur)) {
            answer = depth < answer ? depth : answer;
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!isVisited[i]) { // 방문한 적 없고
                if (getDiff(cur, words[i])) { // 알파벳이 하나만 다른 애 골라서
                    isVisited[i] = true;
                    dfs(words[i], depth + 1);
                    isVisited[i] = false;
                    // 안에서 방문 체크 할 때는 다시 되돌려주기
                }
            }
        }
    }

    static boolean getDiff(String from, String to) {
        int diff = 0;
        for (int i = 0; i < from.length(); i++) {
            if (from.charAt(i) != to.charAt(i)) {
                diff++;
            }
        }
        return diff == 1 ? true : false;
    }
}