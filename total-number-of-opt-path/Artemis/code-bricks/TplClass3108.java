public class TplClass3108 {

    private static final void method(short[] s, short[] interesting) throws Throwable {
        for (int i = 0; i < 64; i++) {
            s[i] = interesting[i % interesting.length];
        }
    }
}

