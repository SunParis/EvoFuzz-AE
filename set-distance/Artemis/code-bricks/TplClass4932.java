public class TplClass4932 {

    private static final void method(long[] times, int NB_RUNS, int MAX_RANGE, java.lang.String benchName) throws Throwable {
        int nbBenches = times.length;
        long totalTimeSpent = 0;
        long meanTimeSpent;
        double variance = 0;
        double standardDeviation = 0;
        // Calculates mean spent time
        for (int i = 1; i <= nbBenches; i++) totalTimeSpent += times[i - 1];
        meanTimeSpent = totalTimeSpent / nbBenches;
        // Calculates standard deviation
        for (int j = 1; j <= nbBenches; j++) variance += Math.pow(((double) times[j - 1] - (double) meanTimeSpent), 2);
        variance = variance / (double) times.length;
        standardDeviation = Math.sqrt(variance) / meanTimeSpent;
    }
}

