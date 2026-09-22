public class TplClass7290 {

    private static final void method(int LEN, java.lang.StringBuffer sb) throws Throwable {
        for (int i = 0; i < LEN; i++) {
            int c = Character.MIN_SUPPLEMENTARY_CODE_POINT + 1;
            sb.append(Character.toChars(c));
        }
    }
}

