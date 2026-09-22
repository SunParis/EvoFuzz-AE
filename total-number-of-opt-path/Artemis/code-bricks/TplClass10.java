public class TplClass10 {

    private static final void method(float d1, float d2) throws Throwable {
        int total = 0;
        for (int i = 0; i < 100000; i++) {
            if (Float.floatToRawIntBits(-(d1 - d2)) == Float.floatToRawIntBits(-0.0f)) {
                total++;
            }
        }
        if (total != 100000) {
        }
    }
}

