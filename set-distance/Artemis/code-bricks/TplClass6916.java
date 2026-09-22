import java.text.Bidi;

public class TplClass6916 {

    private static final void method() throws Throwable {
        boolean err = false;
        String string = "\u05D0\u05D1\u05D2";
        Bidi bidi = new Bidi(string, Bidi.DIRECTION_LEFT_TO_RIGHT);
        int result = bidi.getRunCount();
        if (result != 1) {
            err = true;
        }
        result = bidi.getRunStart(0);
        if (result != 0) {
            err = true;
        }
        result = bidi.getRunLimit(0);
        if (result != 3) {
            err = true;
        }
        result = bidi.getRunLevel(0);
        if (result != 1) {
            err = true;
        }
        if (err) {
        }
    }
}

