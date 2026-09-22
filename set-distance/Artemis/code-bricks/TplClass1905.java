public class TplClass1905 {

    private static final void method(int GROW_SIZE, int holdEnd, int holdIndex, boolean verbose, float[] hold) throws Throwable {
        int have = hold.length - holdIndex;
        int newsize = hold.length + GROW_SIZE;
        float[] newhold = new float[newsize];
        System.arraycopy(hold, holdIndex, newhold, holdIndex + GROW_SIZE, have);
        if (verbose)
            ;
        if (verbose)
            ;
        hold = newhold;
        if (verbose)
            ;
        if (verbose)
            ;
        holdIndex += GROW_SIZE;
        holdEnd += GROW_SIZE;
    }
}

