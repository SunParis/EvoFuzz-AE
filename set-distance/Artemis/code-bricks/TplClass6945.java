import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

public class TplClass6945 {

    private static final void method() throws Throwable {
        List list = new ArrayList();
        ListIterator i = list.listIterator();
        try {
            i.previous();
        } catch (NoSuchElementException e) {
        }
        if (i.hasNext()) {
        }
    }
}

