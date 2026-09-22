public class TplClass7 {

    private static final void method(int total, float d1, float d2) throws Throwable {
        for (int i = 0; i < 100000; i++) {
            if (Float.floatToRawIntBits(-(d1 - d2)) == Float.floatToRawIntBits(-0.0f)) {
                total++;
            }
        }
    }
}

