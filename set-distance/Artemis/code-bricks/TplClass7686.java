import java.util.ListIterator;

public class TplClass7686 {

    private static final void method(java.util.ListIterator itEven, java.util.ListIterator itAll) throws Throwable {
        itAll.previous();
        itAll.add(itEven.previous());
        // ???
        itAll.previous();
    }
}

