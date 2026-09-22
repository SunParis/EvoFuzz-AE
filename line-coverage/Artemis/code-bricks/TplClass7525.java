import java.util.Random;
import java.util.Set;

public class TplClass7525 {

    private static final void method(java.util.Random rnd, java.util.Set s, int n) throws Throwable {
        int r = rnd.nextInt() % n;
        Integer e = new Integer(r < 0 ? -r : r);
        int preSize = s.size();
        boolean prePresent = s.contains(e);
        boolean added = s.add(e);
        if (!s.contains(e))
            ;
        if (added == prePresent)
            ;
        int postSize = s.size();
        if (added && preSize == postSize)
            ;
        if (!added && preSize != postSize)
            ;
    }
}

