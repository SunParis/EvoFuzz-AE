import java.util.Random;

public class TplClass5695 {

    private static final void method(java.util.Random rand, int N, java.lang.String[] elements, java.lang.StringBuilder sb) throws Throwable {
        for (int i = 0; i < N; i++) {
            sb.append(elements[rand.nextInt(elements.length)]);
        }
    }
}

