public class InitStmts {
    
    public static void $init1(int[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1++) {
            $lval1[$tmp1] = ((int) $tmp1) * 100 + 12;
        }
    }

    public static void $init2(Integer[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1++) {
            $lval1[$tmp1] = Integer.valueOf(((int) $tmp1) * 200 + 34);
        }
    }

    public static void $init3(String[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1++) {
            $lval1[$tmp1] = $tmp1 % 2 == 0 ? ("Str" + (((int) $tmp1) * 10 + 56)): "";
        }
    }

    public static void $init4(double[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1++) {
            $lval1[$tmp1] = ((double) $tmp1) * 1.5 + 7.89;
        }
    }

    public static void $init5(long[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1++) {
            $lval1[$tmp1] = ((long) $tmp1) * 100 + 12L;
        }
    }

    public static void $init6(float[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1++) {
            $lval1[$tmp1] = ((float) $tmp1) * 1.5f + 7.89f;
        }
    }

    public static void $init7(short[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1++) {
            $lval1[$tmp1] = (short) (((short) $tmp1) * 10 + 34);
        }
    }

    public static void $init8(byte[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1++) {
            $lval1[$tmp1] = (byte) (((byte) $tmp1) * 5 + 12);
        }
    }

    public static void $init8(char[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1++) {
            $lval1[$tmp1] = (char) (((char) $tmp1) * 5 + 12);
        }
    }

    public static void $init9(boolean[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1++) {
            $lval1[$tmp1] = ($tmp1 % 2 == 0);
        }
    }
}
