import java.text.Bidi;

public class TplClass6910 {

    private static final void method(java.lang.String str, int[] expectedLevels, int[] directions, boolean err, int dir) throws Throwable {
        Bidi bidi = new Bidi(str, directions[dir]);
        for (int index = 0; index < str.length(); index++) {
            int gotLevel = bidi.getLevelAt(index);
            if (gotLevel != expectedLevels[index]) {
                err = true;
            }
        }
    }
}

