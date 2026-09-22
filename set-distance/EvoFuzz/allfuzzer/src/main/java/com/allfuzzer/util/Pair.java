package com.allfuzzer.util;


/**
 * Pair: A simple generic class to hold a pair of objects.
 */
public class Pair<F, S> {
    
    private F first;
    
    private S second;
    
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public void setSecond(S second) {
        this.second = second;
    }

}

