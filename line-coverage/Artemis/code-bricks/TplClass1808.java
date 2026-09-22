import java.util.Scanner;

public class TplClass1808 {

    private static final void method(int r, int c, java.util.Scanner s, boolean[][] grid) throws Throwable {
        for (int x = 0; x < r; x++) {
            String line = s.next();
            for (int y = 0; y < c; y++) grid[x][y] = line.charAt(y) == '.';
        }
    }
}

