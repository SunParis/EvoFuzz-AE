public class TplClass5833 {

    private static final void method(int i) throws Throwable {
        // check that valueOf stores i
        if (Integer.valueOf(i).intValue() != i)
            ;
        // in the range -128 to 127 (inclusive)
        if (i >= -128 && i <= 127) {
            if (Integer.valueOf(i) != Integer.valueOf(i))
                ;
        }
        i++;
    }
}

