public class CheckStmts {
    

    public static void $check1(int[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("int[] $lval1:: at " + $tmp1 + " " + $lval1[$tmp1]);
        }
    }

    public static void $check2(Integer[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("Integer[] $lval1:: at " + $tmp1 + " " + $lval1[$tmp1]);
        }
    }

    public static void $check3(String[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("String[] $lval1:: at " + $tmp1 + " `" + $lval1[$tmp1] + "`");
        }
    }

    public static void $check4(double[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("double[] $lval1:: at " + $tmp1 + " " + $lval1[$tmp1]);
        }
    }

    public static void $check5(long[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("long[] $lval1:: at " + $tmp1 + " " + $lval1[$tmp1]);
        }
    }

    public static void $check6(float[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("float[] $lval1:: at " + $tmp1 + " " + $lval1[$tmp1]);
        }
    }

    public static void $check7(short[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("short[] $lval1:: at " + $tmp1 + " " + $lval1[$tmp1]);
        }
    }

    public static void $check8(byte[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("byte[] $lval1:: at " + $tmp1 + " " + $lval1[$tmp1]);
        }
    }

    public static void $check9(char[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("char[] $lval1:: at " + $tmp1 + " " + (int)($lval1[$tmp1]));
        }
    }

    public static void $check10(boolean[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("boolean[] $lval1:: at " + $tmp1 + " " + $lval1[$tmp1]);
        }
    }

    public static void $check11(Object[] $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.length; $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("Object[] $lval1:: at " + $tmp1 + " " + ($lval1[$tmp1]).getClass().getCanonicalName());
        }
    }

    public static void $check12(boolean $lval1) {
        System.out.println("boolean $lval1:: " + $lval1);
    }

    public static void $check13(Boolean $lval1) {
        System.out.println("Boolean $lval1:: " + String.valueOf($lval1));
    }

    public static void $check14(char $lval1) {
        System.out.println("char $lval1:: " + (int)($lval1));
    }

    public static void $check15(short $lval1) {
        System.out.println("short $lval1:: " + $lval1);
    }

    public static void $check16(byte $lval1) {
        System.out.println("byte $lval1:: " + $lval1);
    }

    public static void $check17(Character $lval1) {
        System.out.println("Character $lval1:: " + String.valueOf((int)($lval1)));
    }

    public static void $check18(Short $lval1) {
        System.out.println("Short $lval1:: " + String.valueOf($lval1));
    }

    public static void $check6(Byte $lval1) {
        System.out.println("Byte $lval1:: " + String.valueOf($lval1));
    }

    public static void $check19(double $lval1) {
        System.out.println("double $lval1:: " + $lval1);
    }

    public static void $check20(Double $lval1) {
        System.out.println("Double $lval1:: " + String.valueOf($lval1));
    }

    public static void $check21(float $lval1) {
        System.out.println("float $lval1:: " + $lval1);
    }

    public static void $check22(Float $lval1) {
        System.out.println("Float $lval1:: " + String.valueOf($lval1));
    }

    public static void $check23(int $lval1) {
        System.out.println("int $lval1:: " + $lval1);
    }

    public static void $check24(Integer $lval1) {
        System.out.println("Integer $lval1:: " + String.valueOf($lval1));
    }

    public static void $check25(long $lval1) {
        System.out.println("long $lval1:: " + $lval1);
    }

    public static void $check26(Long $lval1) {
        System.out.println("Long $lval1:: " + String.valueOf($lval1));
    }
    
    public static void $check27(String $lval1) {
        System.out.println("String $lval1:: `" + $lval1 + "`");
    }

    public static void $check28(Object $lval1) {
        System.out.println("Object $lval1:: " + $lval1.getClass().getCanonicalName());
    }

}
