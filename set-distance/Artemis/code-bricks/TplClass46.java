public class TplClass46 {

    private static final void method(int s, java.lang.String[] strings, int ITERATIONS, int v) throws Throwable {
        for (int i = 0; i < ITERATIONS; i++) {
            v += strings[s].indexOf(strings[s + 1]);
            s += 2;
            if (s >= strings.length)
                s = 0;
        }
    }
}

