import java.awt.Color;
import java.awt.Frame;

public class TplClass6899 {

    private static final void method(java.lang.String[] args) throws Throwable {
        // up causing awt lib to be loaded so the vm won't die with the unsatisfied link error.
        if (args.length > 0) {
            Frame f = new Frame();
            f.setSize(300, 300);
            f.setBackground(Color.white);
            f.show();
        }
    }
}

