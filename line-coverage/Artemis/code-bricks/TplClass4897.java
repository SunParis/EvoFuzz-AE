public class TplClass4897 {

    private static final void method(long meanTimeSpent, long[] times, double variance, int nbBenches) throws Throwable {
        // Calculates standard deviation
        for (int j = 1; j <= nbBenches; j++) variance += Math.pow(((double) times[j - 1] - (double) meanTimeSpent), 2);
    }
}

