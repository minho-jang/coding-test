package programmers;

class Solution_PRG_2021Kakao_신규아이디추천 {
    public String solution(String new_id) {
        StringBuilder newId = new StringBuilder(new_id);

//        System.out.println(newId.toString());
        // 1단계
        for (int i = 0, size = newId.length(); i < size; i++) {
            char c = newId.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                newId.replace(i, i + 1, Character.toString((char) (c + ('a' - 'A'))));
            }
        }

//        System.out.println(newId.toString());
        // 2단계
        for (int i = 0; i < newId.length(); i++) {
            char c = newId.charAt(i);
            if ((c >= 'a' && c <= 'z') ||
                    (c >= '0' && c <= '9') ||
                    c == '-' || c == '_' || c == '.') {
                continue;
            } else {
                newId.replace(i, i + 1, "");
                i--;
            }
        }

//        System.out.println(newId.toString());
        // 3단계
        if (newId.length() > 0) {
            char prev = newId.charAt(0);
            for (int i = 1; i < newId.length(); i++) {
                char c = newId.charAt(i);
                if (prev == '.' && c == '.') {
                    newId.replace(i, i + 1, "");
                    i--;
                    continue;
                }
                prev = c;
            }
        }

//        System.out.println(newId.toString());
        // 4단계
        if (newId.length() > 0 && newId.charAt(0) == '.')
            newId.replace(0, 1, "");
        if (newId.length() > 0 && newId.charAt(newId.length() - 1) == '.')
            newId.replace(newId.length() - 1, newId.length(), "");

//        System.out.println(newId.toString());
        // 5단계
        if (newId.length() == 0) {
            newId.append("a");
        }

//        System.out.println(newId.toString());
        // 6단계
        if (newId.length() >= 16) {
            newId = new StringBuilder(newId.substring(0, 15));
            if (newId.charAt(newId.length() - 1) == '.')
                newId.replace(newId.length() - 1, newId.length(), "");
        }

//        System.out.println(newId.toString());
        // 7단계
        if (newId.length() <= 2) {
            char c = newId.charAt(newId.length() - 1);
            while (newId.length() < 3) {
                newId.append(c);
            }
        }

        return newId.toString();
    }
}