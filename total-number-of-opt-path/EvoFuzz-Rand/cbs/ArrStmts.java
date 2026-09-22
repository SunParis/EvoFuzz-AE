public class ArrStmts {

    public static int $mut1(int[] $lval1, int $expr1) {
        return $lval1[Math.abs($expr1 % $lval1.length)];
    }

    public static Integer $mut2(Integer[] $lval1, int $expr1) {
        return $lval1[Math.abs($expr1 % $lval1.length)];
    }

    public static String $mut3(String[] $lval1, int $expr1) {
        return $lval1[Math.abs($expr1 % $lval1.length)];
    }

    public static double $mut4(double[] $lval1, int $expr1) {
        return $lval1[Math.abs($expr1 % $lval1.length)];
    }

    public static long $mut5(long[] $lval1, int $expr1) {
        return $lval1[Math.abs($expr1 % $lval1.length)];
    }

    public static float $mut6(float[] $lval1, int $expr1) {
        return $lval1[Math.abs($expr1 % $lval1.length)];
    }

    public static short $mut7(short[] $lval1, int $expr1) {
        return $lval1[Math.abs($expr1 % $lval1.length)];
    }

    public static byte $mut8(byte[] $lval1, int $expr1) {
        return $lval1[Math.abs($expr1 % $lval1.length)];
    }

    public static char $mut9(char[] $lval1, int $expr1) {
        return $lval1[Math.abs($expr1 % $lval1.length)];
    }

    public static boolean $mut10(boolean[] $lval1, int $expr1) {
        return $lval1[Math.abs($expr1 % $lval1.length)];
    }

    public static Object $mut11(Object[] $lval1, int $expr1) {
        return $lval1[Math.abs($expr1 % $lval1.length)];
    }

    public static void $mut12(int[] $lval1, int $expr1, int $expr2) {
        $lval1[Math.abs($expr1 % $lval1.length)] = $expr2;
    }

    public static void $mut13(Integer[] $lval1, int $expr1, Integer $expr2) {
        $lval1[Math.abs($expr1 % $lval1.length)] = $expr2;
    }

    public static void $mut14(String[] $lval1, int $expr1, String $expr2) {
        $lval1[Math.abs($expr1 % $lval1.length)] = $expr2;
    }

    public static void $mut15(double[] $lval1, int $expr1, double $expr2) {
        $lval1[Math.abs($expr1 % $lval1.length)] = $expr2;
    }

    public static void $mut16(long[] $lval1, int $expr1, long $expr2) {
        $lval1[Math.abs($expr1 % $lval1.length)] = $expr2;
    }

    public static void $mut17(float[] $lval1, int $expr1, float $expr2) {
        $lval1[Math.abs($expr1 % $lval1.length)] = $expr2;
    }

    public static void $mut18(short[] $lval1, int $expr1, short $expr2) {
        $lval1[Math.abs($expr1 % $lval1.length)] = $expr2;
    }

    public static void $mut19(byte[] $lval1, int $expr1, byte $expr2) {
        $lval1[Math.abs($expr1 % $lval1.length)] = $expr2;
    }

    public static void $mut20(char[] $lval1, int $expr1, char $expr2) {
        $lval1[Math.abs($expr1 % $lval1.length)] = $expr2;
    }

    public static void $mut21(boolean[] $lval1, int $expr1, boolean $expr2) {
        $lval1[Math.abs($expr1 % $lval1.length)] = $expr2;
    }

    public static void $mut22(Object[] $lval1, int $expr1, Object $expr2) {
        $lval1[Math.abs($expr1 % $lval1.length)] = $expr2;
    }

    public static void $mut23(Object[] $lval1, int $expr1, int $expr2) {
        $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)] = Integer.valueOf($expr2);
        if ($expr1 % 3 == 0 && $lval1 != null) {
            $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)] = ((int) $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)]) + 100;
            System.out.println("Object[]:: " + (int) $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)]);
        }
    }

    public static void $mut24(Object[] $lval1, int $expr1, String $expr2) {
        $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)] = String.valueOf($expr2);
        if ($expr1 % 3 == 0 && $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)] != null) {
            $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)] = ((String) $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)]).concat("_appended");
            System.out.println("Object[]:: " + $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)]);
        }
    }

    public static void $mut25(Object[] $lval1, int $expr1, float $expr2) {
        $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)] = Float.valueOf($expr2);
        if ($expr1 % 3 == 0 && $lval1 != null) {
            $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)] = ((float) $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)]) + 0.1f;
            System.out.println("Object[]:: " + (float) $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)]);
        }
    }

    // Try to trigger array pointer copy optimization
    // jdk17/arraycopynode.cpp Line 133 - 144
    public static void $mut26(int[] $lval1, int[] $lval2) {
        System.arraycopy($lval1, 0, $lval2, 0, $lval1.length);
    }

    public static void $mut27(Integer[] $lval1, Integer[] $lval2) {
        System.arraycopy($lval1, 0, $lval2, 0, $lval1.length);
    }

    public static void $mut28(String[] $lval1, String[] $lval2) {
        System.arraycopy($lval1, 0, $lval2, 0, $lval1.length);
    }

    public static void $mut29(double[] $lval1, double[] $lval2) {
        System.arraycopy($lval1, 0, $lval2, 0, $lval1.length);
    }

    public static void $mut30(Object[] $lval1, Object[] $lval2) {
        System.arraycopy($lval1, 0, $lval2, 0, $lval1.length);
    }

    // Try to trigger array copy optimization
    // jdk17/arraycopynode.cpp Line 321 - 352
    public static void $mut31(int[] $lval1, int[] $lval2) {
        $lval2 = $lval1.clone();
    }

    public static void $mut32(Integer[] $lval1, Integer[] $lval2) {
        $lval2 = $lval1.clone();
    }

    public static void $mut33(String[] $lval1, String[] $lval2) {
        $lval2 = $lval1.clone();
    }

    public static void $mut34(double[] $lval1, double[] $lval2) {
        $lval2 = $lval1.clone();
    }

    public static void $mut35(Object[] $lval1, Object[] $lval2) {
        $lval2 = $lval1.clone();
    }

    // Try to trigger array copy optimization when source and destination are the same array
    // jdk17/arraycopynode.cpp Line 366 - 378
    public static void $mut36(int[] $lval1, int $const_small1, int $const_small2, int $const_small3) {
        System.arraycopy($lval1, $const_small1, $lval1, $const_small2, $const_small3);
    }

    public static void $mut37(Integer[] $lval1, int $const_small1, int $const_small2, int $const_small3) {
        System.arraycopy($lval1, $const_small1, $lval1, $const_small2, $const_small3);
    }

    public static void $mut38(String[] $lval1, int $const_small1, int $const_small2, int $const_small3) {
        System.arraycopy($lval1, $const_small1, $lval1, $const_small2, $const_small3);
    }

    public static void $mut39(double[] $lval1, int $const_small1, int $const_small2, int $const_small3) {
        System.arraycopy($lval1, $const_small1, $lval1, $const_small2, $const_small3);
    }

    public static void $mut40(Object[] $lval1, int $const_small1, int $const_small2, int $const_small3) {
        System.arraycopy($lval1, $const_small1, $lval1, $const_small2, $const_small3);
    }

    // Try to trigger "array copy of zero elements" optimization
    // jdk17/arraycopynode.cpp Line 418
    public static void $mut41(int[] $lval1, int $const_small1, int $const_small2) {
        System.arraycopy($lval1, $const_small1, $lval1, $const_small2, 0);
    }

    public static void $mut42(Integer[] $lval1, int $const_small1, int $const_small2) {
        System.arraycopy($lval1, $const_small1, $lval1, $const_small2, 0);
    }

    public static void $mut43(String[] $lval1, int $const_small1, int $const_small2) {
        System.arraycopy($lval1, $const_small1, $lval1, $const_small2, 0);
    }

    public static void $mut44(double[] $lval1, int $const_small1, int $const_small2) {
        System.arraycopy($lval1, $const_small1, $lval1, $const_small2, 0);
    }

    public static void $mut40(Object[] $lval1, int $const_small1, int $const_small2) {
        System.arraycopy($lval1, $const_small1, $lval1, $const_small2, 0);
    }

    public static void $mut41(int[] $lval1, int[] $lval2, int $lval3) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1++) {
            $lval3 += $lval2[$tmp1] * $lval1[$tmp1];
        }
    }

    public static void $mut42(double[] $lval1, double[] $lval2, double $lval3) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1++) {
            $lval3 += $lval2[$tmp1] * $lval1[$tmp1];
        }
    }

    public static void $mut42(int[] $lval1, int[] $lval2, int $lval3) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1++) {
            $lval3 *= $lval2[$tmp1] + $lval1[$tmp1];
        }
    }

    public static void $mut43(double[] $lval1, double[] $lval2, double $lval3) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1++) {
            $lval3 *= $lval2[$tmp1] + $lval1[$tmp1];
        }
    }

    public static void $mut44(int[] $lval1, int[] $lval2, int $lval3) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1++) {
            $lval3 *= (int) Math.sqrt($lval2[$tmp1] + $lval1[$tmp1] + 123456);
        }
    }

    public static void $mut45(double[] $lval1, double[] $lval2, double $lval3) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1++) {
            $lval3 *= Math.sqrt($lval2[$tmp1] + $lval1[$tmp1] + 123456);
        }
    }

    public static void $mut46(int[] $lval1, int[] $lval2, int $lval3) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1++) {
            $lval3 *= Math.abs($lval2[$tmp1] - $lval1[$tmp1]);
        }
    }

    public static void $mut47(double[] $lval1, double[] $lval2, double $lval3) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1++) {
            $lval3 *= Math.abs($lval2[$tmp1] - $lval1[$tmp1]);
        }
    }

    public static void $mut48(byte[] $lval1, String $expr1) {
        $lval1 = $expr1.getBytes();
    }

}
