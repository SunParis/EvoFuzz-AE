import java.text.Normalizer;

public class TplClass3594 {

    private static final void method() throws Throwable {
        String composed = "Bl\u00c1ah";
        String decomposed = "Bl\u0041\u0301ah";
        String res;
        res = Normalizer.normalize(composed, Normalizer.Form.NFD);
        if (!decomposed.equals(res)) {
        }
        res = Normalizer.normalize(decomposed, Normalizer.Form.NFC);
        if (!composed.equals(res)) {
        }
    }
}

