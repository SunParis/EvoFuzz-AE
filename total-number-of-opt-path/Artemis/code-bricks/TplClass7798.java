import java.util.LinkedList;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class TplClass7798 {

    private static final void method() throws Throwable {
        List head = Collections.nCopies(7, "deadly sin");
        List tail = Collections.nCopies(4, "basic food group");
        List l1 = new ArrayList(head);
        List l2 = new LinkedList(head);
        l1.addAll(tail);
        l2.addAll(tail);
        if (!l1.equals(l2))
            ;
    }
}

