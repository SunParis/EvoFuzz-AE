public class TplClass5045 {

    private static final void method() throws Throwable {
        // Test straightforward replacement
        String data = "abcdefghi";
        String result = data.replace("def", "abc");
        if (!result.equals("abcabcghi"))
            ;
        // Test replacement with target that has metacharacters
        data = "abc(def)?ghi";
        result = data.replace("(def)?", "abc");
        if (!result.equals("abcabcghi"))
            ;
        // Test replacement with replacement that has metacharacters
        data = "abcdefghi";
        result = data.replace("def", "\\ab$c");
        if (!result.equals("abc\\ab$cghi"))
            ;
    }
}

