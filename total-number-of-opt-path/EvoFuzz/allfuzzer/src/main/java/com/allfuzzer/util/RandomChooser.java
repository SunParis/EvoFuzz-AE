package com.allfuzzer.util;

import java.util.Random;

/**
 * RandomChooser: Utility class for generating random values.
 */
public class RandomChooser {

    public static final Random random = new Random();
    
    /**
     * Generate a random integer between 0 (inclusive) and the specified value (exclusive).
     * If the specified value is not positive, returns 0.
     * @param bound the upper bound (exclusive).
     * @return a random integer between 0 (inclusive) and bound (exclusive).
     */
    public static int nextInt(int bound) {
        if (bound <= 0) return 0;
        return random.nextInt(bound);
    }

    /**
     * Generate a random integer.
     * @return a random integer.
     */
    public static int nextInt() {
        return random.nextInt();
    }

    /**
     * Generate a random boolean value.
     * @return a random boolean value.
     */
    public static boolean nextBoolean() {
        return random.nextBoolean();
    }

    /**
     * Generate a random double value between 0.0 (inclusive) and 1.0 (exclusive).
     * @return a random double value between 0.0 and 1.0.
     */
    public static double nextDouble() {
        return random.nextDouble();
    }

    /**
     * Generate a random float value between 0.0f (inclusive) and 1.0f (exclusive).
     * @return a random float value between 0.0f and 1.0f.
     */
    public static float nextFloat() {
        return random.nextFloat();
    }
    
    /**
     * Generate a random long value.
     * @return a random long value.
     */
    public static long nextLong() {
        return random.nextLong();
    }

    /**
     * Generate a random string of the specified length consisting of lowercase letters.
     * @param length the length of the string to generate.
     * @return a random string of the specified length.
     */
    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = (char) ('a' + random.nextInt(26));
            if (i != 0 && random.nextBoolean()) {
                c = (char) ('0' + random.nextInt(10));
            } else if (random.nextBoolean()) {
                c = (char) ('A' + random.nextInt(26));
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Generate a random string of length 10 consisting of lowercase letters.
     * @return a random string of length 10.
     */
    public static String randomString() {
        return randomString(nextInt(10) + 10);
    }

}
