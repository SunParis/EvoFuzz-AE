public class TplClass452 {

    private static final void method(int index, java.lang.Object[] set, int firstRemoved, java.lang.Object key) throws Throwable {
        if (firstRemoved != -1)
            set[firstRemoved] = "dead";
        else
            set[index] = key;
    }
}

