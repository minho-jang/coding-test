package baekjoon;

import java.util.Scanner;

public class Main_BOJ_11758_CCW {
    static class Vec {
        int x, y;

        public Vec(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vec a = new Vec(sc.nextInt(), sc.nextInt());
        Vec b = new Vec(sc.nextInt(), sc.nextInt());
        Vec c = new Vec(sc.nextInt(), sc.nextInt());

        Vec ab = new Vec(b.x - a.x, b.y - a.y);
        Vec bc = new Vec(c.x - b.x, c.y - b.y);

        int result = crossProduct(ab, bc);

        if (result < 0) {
            System.out.println(-1);
        } else if (result > 0) {
            System.out.println(1);
        } else
            System.out.println(0);

        sc.close();
    }

    private static int crossProduct(Vec ab, Vec bc) {
        return ab.x * bc.y - bc.x * ab.y;
    }
}
