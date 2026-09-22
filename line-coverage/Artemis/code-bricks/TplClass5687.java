public class TplClass5687 {

    private static final void method(java.lang.Runnable r, java.lang.ThreadLocal<java.lang.Integer> myGroup, int groupId) throws Throwable {
        myGroup.set(groupId);
        r.run();
    }
}

