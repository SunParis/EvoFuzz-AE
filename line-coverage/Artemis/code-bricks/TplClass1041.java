public class TplClass1041 {

    private static final void method(long leftFactor, long constantProduct, long rightFactor, long optimizedProduct) throws Throwable {
        // unaffected by the new optimization
        long normalProduct = leftFactor * rightFactor;
        if (optimizedProduct != constantProduct || normalProduct != constantProduct) {
        }
    }
}

