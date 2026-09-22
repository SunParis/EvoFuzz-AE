import java.util.List;

public class TplClass4475 {

    private static final void method(int allocations, java.util.List<byte[]> l) throws Throwable {
        while (true) {
            // Allocate a MB at a time
            l.add(new byte[1048576]);
            allocations++;
        }
    }
}

