public class TplClass4898 {

    private static final void method(java.lang.String[] args, boolean Verbose, boolean DoIt) throws Throwable {
        for (String s : args) {
            if (s.equals("-doit"))
                DoIt = true;
            else if (s.equals("-verbose"))
                Verbose = true;
        }
    }
}

