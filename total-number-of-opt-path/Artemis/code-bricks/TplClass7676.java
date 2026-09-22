import java.util.List;

public class TplClass7676 {

    private static final void method(java.util.List s1) throws Throwable {
        // Reverse List
        for (int j = 0, n = s1.size(); j < n; j++) s1.set(j, s1.set(n - j - 1, s1.get(j)));
    }
}

