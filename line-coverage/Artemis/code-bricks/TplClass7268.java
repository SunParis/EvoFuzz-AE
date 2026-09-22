import java.text.ChoiceFormat;

public class TplClass7268 {

    private static final void method(java.lang.String[] strings, double[] doubles, java.text.ChoiceFormat choiceFormat2) throws Throwable {
        for (int i = 0; i < doubles.length; i++) {
            String result = choiceFormat2.format(doubles[i]);
            if (!result.equals(strings[i])) {
            }
        }
    }
}

