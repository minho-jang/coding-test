package programmers;

public class Solution_PRG_조이스틱 {
    static int answer, N;
    static char[] nameArr;
    static int[] distance = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
            12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    static boolean[] visited;

    public int solution(String name) {
        this.nameArr = name.toCharArray();
        this.N = name.length();
        this.answer = 987654321;

        visited = new boolean[N];
        for (int i = 0; i < N; i++)
            if (nameArr[i] == 'A')
                visited[i] = true;

        visited[0] = true;
        dfs(0, 0, 0);

        return answer;
    }

    private static void dfs(int cur, int moveCount, int depth) {
        if (answer < moveCount)
            return;
        if (isAllVisited(visited)) {
            int moveUpDown = changeAto(nameArr[cur]);
            answer = Math.min(answer, moveCount + moveUpDown);
            return;
        }

        int moveUpDown = changeAto(nameArr[cur]);

        int moveRightLeft = 0;
        int idx = cur;
        for (int i = 0; i < N; i++) {
            idx = (idx + 1) % N;
            moveRightLeft++;
            if (!visited[idx]) {
                visited[idx] = true;
                dfs(idx, moveCount + moveUpDown + moveRightLeft, depth + 1);
                visited[idx] = false;
                break;
            }
        }

        moveRightLeft = 0;
        idx = cur;
        for (int i = 0; i < N; i++) {
            idx = (idx - 1 >= 0) ? idx - 1 : idx - 1 + N;
            moveRightLeft++;
            if (!visited[idx]) {
                visited[idx] = true;
                dfs(idx, moveCount + moveUpDown + moveRightLeft, depth + 1);
                visited[idx] = false;
                break;
            }
        }
    }

    private static boolean isAllVisited(boolean[] visited) {
        for (int i = 0; i < N; i++) {
            if (!visited[i])
                return false;
        }
        return true;
    }

    private static int changeAto(char c) {
        return distance[c - 'A'];
    }
}
