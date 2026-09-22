public class TplClass5969 {

    private static final void method(java.lang.Process[] cats) throws Throwable {
        for (// hangs?
        // hangs?
        int i = 0; // hangs?
        i < cats.length; ++i) cats[i].waitFor();
    }
}

