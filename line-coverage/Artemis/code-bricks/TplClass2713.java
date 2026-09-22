public class TplClass2713 {

    private static final void method(int length, java.lang.StringBuilder sb) throws Throwable {
        for (int i = 0; i < length; i++) {
            // Generate repeating alphabet.
            sb.append(Character.valueOf((char) ('a' + (i % 26))));
        }
    }
}

