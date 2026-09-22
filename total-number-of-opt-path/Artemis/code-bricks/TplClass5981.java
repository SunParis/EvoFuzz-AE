import java.io.BufferedWriter;
import java.io.FileWriter;

public class TplClass5981 {

    private static final void method(java.lang.String[] doCmdCopy) throws Throwable {
        for (int i = 0; i < doCmdCopy.length; ++i) {
            try (BufferedWriter outCmd = new BufferedWriter(new FileWriter(doCmdCopy[i]))) {
                outCmd.write("@echo %1");
            }
        }
    }
}

