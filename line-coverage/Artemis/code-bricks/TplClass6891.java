import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.awt.font.TextAttribute;
import java.text.Annotation;

public class TplClass6891 {

    private static final void method() throws Throwable {
        String text = "Hello world";
        AttributedString as = new AttributedString(text);
        // add non-Annotation attributes
        as.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_LIGHT, 0, 3);
        as.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD, 3, 5);
        as.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_EXTRABOLD, 5, text.length());
        // add Annotation attributes
        as.addAttribute(TextAttribute.WIDTH, new Annotation(TextAttribute.WIDTH_EXTENDED), 0, 3);
        as.addAttribute(TextAttribute.WIDTH, new Annotation(TextAttribute.WIDTH_CONDENSED), 3, 4);
        AttributedCharacterIterator aci = as.getIterator(null, 2, 4);
        aci.first();
        int runStart = aci.getRunStart();
        if (runStart != 2) {
        }
        int runLimit = aci.getRunLimit();
        if (runLimit != 3) {
        }
        Object value = aci.getAttribute(TextAttribute.WEIGHT);
        if (value != TextAttribute.WEIGHT_LIGHT) {
        }
        value = aci.getAttribute(TextAttribute.WIDTH);
        if (value != null) {
        }
        aci.setIndex(runLimit);
        runStart = aci.getRunStart();
        if (runStart != 3) {
        }
        runLimit = aci.getRunLimit();
        if (runLimit != 4) {
        }
        value = aci.getAttribute(TextAttribute.WEIGHT);
        if (value != TextAttribute.WEIGHT_BOLD) {
        }
        value = aci.getAttribute(TextAttribute.WIDTH);
        if (!(value instanceof Annotation) || (((Annotation) value).getValue() != TextAttribute.WIDTH_CONDENSED)) {
        }
    }
}

