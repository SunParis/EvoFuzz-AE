public class TplClass7473 {

    private static final void method(int indP, java.lang.String s, java.lang.String significand, int indD, long exp) throws Throwable {
        significand = s.substring(0, indD) + s.substring(indD + 1, indP);
        exp -= 4 * (indP - indD - 1);
    }
}

