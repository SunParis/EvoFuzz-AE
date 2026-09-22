import java.util.Scanner;

public class TplClass1811 {

    private static final void method(int x, int c, java.util.Scanner s, boolean[][] grid) throws Throwable {
        String line = s.next();
        for (int y = 0; y < c; y++) grid[x][y] = line.charAt(y) == '.';
    }
}

