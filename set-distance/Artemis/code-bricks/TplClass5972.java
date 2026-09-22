import java.io.OutputStream;

public class TplClass5972 {

    private static final void method(java.lang.String[] sleepArgs, java.lang.Process[] sleeps, java.lang.Runtime rt, java.lang.Process[] cats, java.lang.String[] catArgs, int i) throws Throwable {
        cats[i] = rt.exec(catArgs);
        java.io.OutputStream s = cats[i].getOutputStream();
        Process sleep = rt.exec(sleepArgs);
        // race condition here
        s.close();
        sleeps[i] = sleep;
    }
}

