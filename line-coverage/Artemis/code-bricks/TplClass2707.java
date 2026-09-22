public class TplClass2707 {

    private static final void method(java.lang.Object y) throws Throwable {
        if (y instanceof String) {
            // Bug: 15808277
            // Non-sensical instance-of to check merging after the branch doesn't result in a verifier
            // error.
            ((String) y).charAt(0);
        }
    }
}

