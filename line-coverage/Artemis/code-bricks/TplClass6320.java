import java.security.Identity;

public class TplClass6320 {

    private static final void method(java.security.Identity i1, java.security.Identity i3) throws Throwable {
        if (!((i1.equals(i3)) && (i1.hashCode() == i3.hashCode()))) {
            Exception up = new Exception("Contract violated -- PublicKeys do not differ");
        }
    }
}

