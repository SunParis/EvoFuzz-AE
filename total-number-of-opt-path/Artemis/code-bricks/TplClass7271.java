import java.text.ChoiceFormat;

public class TplClass7271 {

    private static final void method(java.lang.String pattern, java.lang.String[] strings, double[] doubles) throws Throwable {
        ChoiceFormat choiceFormat1 = new ChoiceFormat(doubles, strings);
        ChoiceFormat choiceFormat2 = new ChoiceFormat(pattern);
        if (!choiceFormat1.equals(choiceFormat2)) {
        }
        for (int i = 0; i < doubles.length; i++) {
            String result = choiceFormat2.format(doubles[i]);
            if (!result.equals(strings[i])) {
            }
        }
    }
}

