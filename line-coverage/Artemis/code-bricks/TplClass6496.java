public class TplClass6496 {

    private static final void method(byte[] a, int capacity) throws Throwable {
        while (a.length < capacity) {
            byte[] a2 = new byte[a.length * 2];
            System.arraycopy(a, 0, a2, 0, a.length);
            a = a2;
        }
    }
}

