public class TplClass2702 {

    private static final void method(int[] array2, int[] catch_phi) throws Throwable {
        try {
            System.nanoTime();
            catch_phi = array2;
            System.nanoTime();
        } catch (Throwable ex) {
            catch_phi[0] = 14;
        }
    }
}

