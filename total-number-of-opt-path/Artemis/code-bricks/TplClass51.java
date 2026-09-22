public class TplClass51 {

    private static final void method(int s, java.lang.String[] strings, int v) throws Throwable {
        v += strings[s].indexOf(strings[s + 1]);
        s += 2;
        if (s >= strings.length)
            s = 0;
    }
}

