public class TplClass5929 {

    private static final void method(int size, int BOUND2, int BOUND3, int BOUND4, int BOUND5, int asize) throws Throwable {
        if (size >= BOUND3 && asize < BOUND2) {
            asize = BOUND2;
        } else if (size >= BOUND4 && asize < BOUND3) {
            asize = BOUND3;
        } else if (size >= BOUND5 && asize < BOUND4) {
            asize = BOUND4;
        }
    }
}

