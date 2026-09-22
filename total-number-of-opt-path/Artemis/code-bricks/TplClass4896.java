public class TplClass4896 {

    private static final void method(long[] times, long totalTimeSpent, int nbBenches) throws Throwable {
        // Calculates mean spent time
        for (int i = 1; i <= nbBenches; i++) totalTimeSpent += times[i - 1];
    }
}

