import java.util.List;

public class TplClass5490 {

    private static final void method(java.util.List<java.lang.String> ls, java.lang.StringBuilder sb) throws Throwable {
        try {
            sb.insert(sb.length() + 1, ls);
        } catch (StringIndexOutOfBoundsException soob) {
            // expected: passed
        } catch (Throwable t) {
        }
    }
}

