import java.io.File;

public class TplClass5198 {

    private static final void method(java.io.File outB, boolean fileOut, java.lang.ProcessBuilder builderB) throws Throwable {
        if (fileOut) {
            outB = new File("outB.txt");
            builderB.redirectOutput(outB);
        }
    }
}

