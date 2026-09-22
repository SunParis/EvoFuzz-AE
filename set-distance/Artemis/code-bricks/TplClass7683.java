import java.util.ListIterator;

public class TplClass7683 {

    private static final void method(java.util.ListIterator itEven, java.util.ListIterator itAll) throws Throwable {
        while (itEven.hasPrevious()) {
            itAll.previous();
            itAll.add(itEven.previous());
            // ???
            itAll.previous();
        }
    }
}

