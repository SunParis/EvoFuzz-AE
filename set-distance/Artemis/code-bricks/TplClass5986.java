import java.io.BufferedWriter;
import java.io.FileWriter;

public class TplClass5986 {

    private static final void method(java.lang.String[] doCmdCopy, int i) throws Throwable {
        try (BufferedWriter outCmd = new BufferedWriter(new FileWriter(doCmdCopy[i]))) {
            outCmd.write("@echo %1");
        }
    }
}

