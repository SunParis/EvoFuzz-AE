public class LongExprs {

    public static long $gbvar;

    // Try to trigger (a - b) + (b - c) -> a - c and similar optimizations
    // jdk17/addnode.cpp Line 420 - 469
    public static long $mut0(long $lval1, long $lval2, long $expr1) {
        return (($lval1 - $lval2) + ($lval2 - ($expr1)));
    }

    // Try to trigger (a >>> rshift) + (a << lshift) -> RotateRight(a, rshift)
    // jdk17/addnode.cpp Line 483 - 491
    public static long $mut1(long $lval1, int $const) {
        return (($lval1 >>> ($const % 64)) + ($lval1 << (64 - ($const % 64))));
    }

    // Try to trigger (~a + 1) -> -a
    // jdk17/addnode.cpp Line 499 - 501
    public static long $mut2(long $lval1) {
        return ((~$lval1 + 1L));
    }

    // Try to trigger underflow, overflow
    // jdk17/addnode.cpp Line 530 - 538
    public static long $mut3() {
        return ((-9223372036854775807L + (-50)));
    }
    public static long $mut31() {
        return ((9223372036854775800L + 50));
    }
    public static long $mut32() {
        return ((9223372036854775807L + 1));
    }

    // Try to trigger (val << shift) | (val >>> (64 - shift))
    // jdk17/addnode.cpp Line 787 - 791
    public static long $mut4(long $lval1, int $const) {
        return (($lval1 << ($const % 64)) | ($lval1 >>> (64 - ($const % 64))));
    }
    // Try to trigger (val >>> shift) | (val << (64 - shift))
    public static long $mut41(long $lval1, int $const) {
        return (($lval1 >>> ($const % 64)) | ($lval1 << (64 - ($const % 64))));
    }
    // Try to trigger (a << (b & 63)) | (a >>> (64 - (b & 63)))
    public static long $mut42(long $lval1, long $lval2) {
        return (($lval1 << ($lval2 & 63)) | ($lval1 >>> (64 - ($lval2 & 63))));
    }
    // Try to trigger (a >>> (b & 63)) | (a << (64 - (b & 63)))
    public static long $mut43(long $lval1, long $lval2) {
        return (($lval1 >>> ($lval2 & 63)) | ($lval1 << (64 - ($lval2 & 63))));
    }

    // Try to trigger the roate left/right
    // jdk17/addnode.cpp Line 876 - 899
    public static long $mut5(long $lval1, int $const) {
        return (($lval1 << ($const % 64)) | ($lval1 >>> (64 - ($const % 64))));
    }
    public static long $mut51(long $lval1, int $const) {
        return (($lval1 >>> ($const % 64)) | ($lval1 << (64 - ($const % 64))));
    }

    // Try to trigger branchless cMoveLNode optimization
    // jdk17/addnode.cpp Line 1124 - 1127、1136 - 1139
    public static long $mut7(long $lval1, long $lval2) {
        return (Math.min(0, ($lval1 - $lval2)));
    }
    public static long $mut71(long $lval1, long $lval2) {
        return (Math.min(($lval1 - $lval2), 0));
    }
    public static long $mut72(long $lval1, long $lval2) {
        return (Math.max(0, ($lval1 - $lval2)));
    }
    public static long $mut73(long $lval1, long $lval2) {
        return (Math.max(($lval1 - $lval2), 0));
    }

    // Try to trigger the optimization where the absolute value of the divisor is a power of 2
    // jdk17/divnode.cpp Line 356 - 444
    public static long $mut8(long $lval1, int $const1) {
        return ((Math.abs($lval1)) / $const1);
    }
    public static long $mut81(long $lval1, int $const1) {
        return (((Math.abs($lval1)) & ~0b1111L) / $const1);
    }
    public static long $mut82(long $lval1, int $const1) {
        return ((Math.abs($lval1)) / (-1) * $const1);
    }

    // Try to trigger Long.MIN_VALUE / -1L and long / Long.MIN_VALUE
    // jdk17/divnode.cpp Line 629 - 654
    public static long $mut9() {
        return ((Long.MIN_VALUE / -1L));
    }
    public static long $mut91(long $lval1) {
        return (($lval1 / Long.MIN_VALUE));
    }
    public static long $mut92(int $expr1) {
        return ((($expr1) / Long.MIN_VALUE));
    }

    // Try to trigger long % (2^k - 1)
    // jdk17/divnode.cpp Line 1048 - 1077
    public static long $mut10(long $lval1, int $const1) {
        return (($lval1 % $const1));
    }
    public static long $mut101(long $lval1, int $const1) {
        return (($lval1 % ((-1) * $const1)));
    }

    // $mut11: Math.addExact(a, b)
    public static void $mut11(long $lval1, long $lval2, long $lval3) {
        $lval1 /= 2;
        $lval2 /= 2;
        $lval3 = (Math.addExact($lval1, $lval2));
    }

    // $mut12: Math.subtractExact(a, b)
    public static void $mut12(long $lval1, long $lval2, long $lval3) {
        $lval1 /= 2;
        $lval2 /= 2;
        $lval3 = (Math.subtractExact($lval1, $lval2));
    }
    
    // $mut13: Math.multiplyExact(a, b)
    public static void $mut13(long $lval1, long $lval2, long $lval3) {
        $lval1 = $lval1 % 10000;
        $lval2 = $lval2 % 10000;
        $lval3 = (Math.multiplyExact($lval1, $lval2));
    }

    public static void $mut14(long $expr1) {
        $gbvar = (($expr1) + 10L);
    }

    public static void $mut15(long $expr1) {
        $gbvar = (($expr1) + 50000L);
    }

    public static void $mut16(long $expr1, long $expr2) {
        $gbvar = (($expr1) + ($expr2));
    }

    public static void $mut17(long $expr1, long $expr2) {
        $gbvar = (($expr1) + ($expr2));
    }

    public static long $mut18(long $expr1, long $expr2) {
        return ((($expr1) + ($expr2)));
    }

    public static long $mut19(long $expr1, long $expr2) {
        return ((($expr1) - ($expr2)));
    }

    public static long $mut20(long $expr1, long $expr2) {
        return ((($expr1) * ($expr2)));
    }

    public static long $mut21(long $expr1, long $expr2) {
        return ((($expr1) / Math.max(1L, ($expr2))));
    }

    public static long $mut22(long $expr1, long $expr2) {
        return ((($expr1) % Math.max(1L, ($expr2))));
    }

    public static long $mut23(long $expr1) {
        return (($expr1));
    }

    public static long $mut24(long $expr1, long $expr2) {
        return ((($expr1) & ($expr2)));
    }

    public static long $mut25(long $expr1, long $expr2) {
        return ((($expr1) | ($expr2)));
    }

    public static long $mut26(long $expr1, long $expr2) {
        return ((($expr1) ^ ($expr2)));
    }

    public static long $mut27(int $expr1, long[] $lval1) {
        return ($lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)]);
    }

    public static void $mut28(int $expr1, long $expr2, long[] $lval1) {
        $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)] = ($expr2);
    }

}
