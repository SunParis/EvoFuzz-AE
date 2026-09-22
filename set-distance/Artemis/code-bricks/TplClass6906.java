import java.text.Bidi;

public class TplClass6906 {

    private static final void method(java.lang.String str, int[] expectedLevels, int[] directions, boolean err) throws Throwable {
        for (int dir = 0; dir < directions.length; dir++) {
            Bidi bidi = new Bidi(str, directions[dir]);
            for (int index = 0; index < str.length(); index++) {
                int gotLevel = bidi.getLevelAt(index);
                if (gotLevel != expectedLevels[index]) {
                    err = true;
                }
            }
        }
    }
}

