import java.text.Bidi;

public class TplClass6911 {

    private static final void method(java.lang.String str, java.text.Bidi bidi, int[] expectedLevels, int[] directions, boolean err, int index, int dir) throws Throwable {
        int gotLevel = bidi.getLevelAt(index);
        if (gotLevel != expectedLevels[index]) {
            err = true;
        }
    }
}

