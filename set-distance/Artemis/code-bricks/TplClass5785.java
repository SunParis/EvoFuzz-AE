public class TplClass5785 {

    private static final void method() throws Throwable {
        StringBuffer active = new StringBuffer();
        active.append("first one");
        String a = active.toString();
        active.setLength(0);
        active.append("second");
        String b = active.toString();
        active.setLength(0);
        if (!a.equals("first one")) {
        }
    }
}

