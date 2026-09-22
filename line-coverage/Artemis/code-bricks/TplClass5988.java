import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class TplClass5988 {

    private static final void method(java.lang.String[] doCmdCopy) throws Throwable {
        new File(".\\Program Files").mkdirs();
        for (int i = 0; i < doCmdCopy.length; ++i) {
            try (BufferedWriter outCmd = new BufferedWriter(new FileWriter(doCmdCopy[i]))) {
                outCmd.write("@echo %1");
            }
        }
    }
}

