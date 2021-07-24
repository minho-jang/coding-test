package programmers;

public class Solution_PRG_2018Kakao_추석트래픽 {
    class Node {
        int endTime, processTime, startTime;

        Node(int endTime, int processTime) {
            this.endTime = endTime;
            this.processTime = processTime;
            this.startTime = endTime - processTime + 1;
        }
    }

    int N;
    Node[] nodes;

    public int solution(String[] lines) {
        int answer = 0;

        N = lines.length;

        nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            String line = lines[i];
            int et = getEndTime(line);
            int pt = getProcessTime(line);

            nodes[i] = new Node(et, pt);
        }

        for (int i = 0; i < N; i++) {
            int endTime = nodes[i].endTime;
            int result = getProcessForOneMinute(endTime);

            answer = Math.max(answer, result);
        }

        return answer;
    }

    private int getProcessForOneMinute(int start) {
        int end = start + 1000 - 1;
        int result = 0;

        for (int i = 0; i < N; i++) {
            Node node = nodes[i];
            if ((start <= node.startTime && node.startTime <= end) ||
                    (start <= node.endTime && node.endTime <= end) ||
                    (node.startTime <= start && start <= node.endTime)) {
                result++;
            }
        }

        return result;
    }

    private int getProcessTime(String line) {
        String time = line.split(" ")[2];
        time = time.substring(0, time.length() - 1);
        int result = (int) (Double.parseDouble(time) * 1000);

        return result;
    }

    private int getEndTime(String line) {
        String time = line.split(" ")[1];

        String[] timeSplitted = time.split(":");
        int hour = Integer.parseInt(timeSplitted[0]);
        int minute = Integer.parseInt(timeSplitted[1]);

        String[] secondSplitted = timeSplitted[2].split("\\.");
        int second = Integer.parseInt(secondSplitted[0]);
        int mill = Integer.parseInt(secondSplitted[1]);

        int result = 0;
        result += (hour * 1000 * 60 * 60);
        result += (minute * 1000 * 60);
        result += (second * 1000);
        result += mill;

        return result;
    }

    public static void main(String[] args) {
        Solution_PRG_2018Kakao_추석트래픽 sol = new Solution_PRG_2018Kakao_추석트래픽();

        String[] lines = {
                "2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"};

        System.out.println(sol.solution(lines));
    }
}
