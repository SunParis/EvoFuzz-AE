import java.nio.charset.IllegalCharsetNameException;

public class TplClass2532 {

    private static final void method() throws Throwable {
        String a, b;
        final String foo = "foo";
        final String bar = "bar";
        // Two interned strings should match.
        a = foo.concat(bar).intern();
        b = foo.concat(bar).intern();
        if (a == b && foo != bar) {
        } else {
        }
        // An interned string should match a string literal.
        a = ("f" + foo.substring(1, 3)).intern();
        if (a == foo) {
        } else {
        }
        // Check that a string literal in libcore equals one in the app.
        a = (new java.nio.charset.IllegalCharsetNameException(null)).getMessage();
        b = "null";
        if (a == b) {
        } else {
        }
    }
}

