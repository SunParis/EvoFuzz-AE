import java.text.Bidi;

public class TplClass6907 {

    private static final void method(java.lang.String str, java.text.Bidi bidi, int[] expectedLevels, int[] directions, boolean err, int dir) throws Throwable {
        for (int index = 0; index < str.length(); index++) {
            int gotLevel = bidi.getLevelAt(index);
            if (gotLevel != expectedLevels[index]) {
                err = true;
            }
        }
    }
}

