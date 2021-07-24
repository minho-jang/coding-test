package programmers;

import java.util.Arrays;

class Solution_PRG_단속카메라 {
    public int solution(int[][] routes) {
        int answer = 0;

        // 나가는 시점을 기준으로 정렬
        Arrays.sort(routes, (o1, o2) -> {
            return Integer.compare(o1[1], o2[1]);
        });

        int cameraPos = 0;  // 카메라를 설치했던 지점
        for (int i = 0; i < routes.length; i++) {
            // 카메라를 하나도 설치하지 않았으면
            if (answer == 0) {
                cameraPos = routes[i][1];
                answer++;
                continue;
            }
            // 카메라를 설치한 지점에 만난다면
            if (routes[i][0] <= cameraPos && cameraPos <= routes[i][1]) {
                continue;
            }

            // 카메라 설치
            cameraPos = routes[i][1];
            answer++;
        }

        return answer;
    }
}