import java.security.Identity;

public class TplClass6319 {

    private static final void method(java.security.Identity i1, java.security.Identity i2) throws Throwable {
        if (!(i1.equals(i2)) == (i1.hashCode() == i2.hashCode())) {
            Exception up = new Exception("Contract violated -- same name and same scope");
        }
    }
}

