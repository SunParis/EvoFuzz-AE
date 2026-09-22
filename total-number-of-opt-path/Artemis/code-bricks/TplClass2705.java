public class TplClass2705 {

    private static final void method() throws Throwable {
        try {
            // Regression test for default native methods that should cause ClassFormatException
            // if they pass the dex file verification, i.e. for old dex file versions.
            // We previously did not handle this case properly and failed a DCHECK() for
            // a non-interface class creating a copied method that was native. b/157170505
            Class.forName("TestClass");
        } catch (ClassFormatError expected) {
        } catch (Throwable unexpected) {
        }
    }
}

