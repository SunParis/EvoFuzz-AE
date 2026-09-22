public class TplClass2590 {

    private static final void method() throws Throwable {
        String baseStr = "*** This is a very nice string!!!";
        String testStr;
        int i;
        testStr = baseStr.substring(4, baseStr.length() - 3);
        /* sloppy for loop */
        for (i = 0; i < testStr.length(); i++) ;
        String testStr2 = "This is a very nice strinG";
        if (testStr.length() != testStr2.length())
            ;
        int compareResult = testStr.compareTo(testStr2);
        if (compareResult > 0) {
        } else if (compareResult == 0) {
        } else {
        }
        // expected: -65302
        String s1 = "\u0c6d\u0cb6\u0d00\u0000\u0080\u0080\u0080\u0000\u0002\u0002\u0002\u0000\u00e9\u00e9\u00e9";
        String s2 = "\u0c6d\u0cb6\u0d00\u0000\u0080\u0080\u0080\u0000\u0002\u0002\u0002\u0000\uffff\uffff\uffff\u00e9\u00e9\u00e9";
        try {
            testStr.charAt(500);
        } catch (StringIndexOutOfBoundsException sioobe) {
        }
    }
}

