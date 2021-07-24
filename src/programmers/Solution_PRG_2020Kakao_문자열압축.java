package programmers;

import java.util.ArrayList;

class Solution_PRG_2020Kakao_문자열압축 {
    public int solution(String s) {

        int N = s.length();
        int UPPER_LIMIT = N / 2;
        int answer = N;

        for (int i = 1; i <= UPPER_LIMIT; i++) {
            answer = Math.min(answer, compression(s, i));
        }

        return answer;
    }

    // split 길이로 잘라 압축할 때 길이를 반환
    int compression(String s, int split) {
        ArrayList<String> slist = new ArrayList<>();
        int len = s.length();

        int i = 0;
        for (i = 0; i + split < len; i += split) {
            slist.add(s.substring(i, i + split));
        }
        slist.add(s.substring(i));

        ArrayList<String> result = new ArrayList<>();
        ArrayList<Integer> cnt = new ArrayList<>();
        for (i = 0; i < slist.size(); i++) {
            String got = slist.get(i);
            if (result.size() > 0 && result.get(result.size() - 1).equals(got)) {
                cnt.set(result.size() - 1, cnt.get(result.size() - 1) + 1);

            } else {
                result.add(got);
                cnt.add(1);

            }
        }

        StringBuilder compressed = new StringBuilder();
        for (int j = 0; j < result.size(); j++) {
            if (cnt.get(j) > 1)
                compressed.append(cnt.get(j));
            compressed.append(result.get(j));
        }

        return compressed.length();
    }
}