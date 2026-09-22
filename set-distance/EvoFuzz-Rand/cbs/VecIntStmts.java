
import jdk.incubator.vector.*;

public class VecIntStmts {

    public static int $priority = 70;

    public static void $mut1(int[] $lval1, int[] $lval2, int[] $lval3, int $const_small1, IntVector $lval4, IntVector $lval5, IntVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (IntVector) VectorShuffle.iota(IntVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.add($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (int)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (int)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (int)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut2(int[] $lval1, int[] $lval2, int[] $lval3, int $const_small1, IntVector $lval4, IntVector $lval5, IntVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (IntVector) VectorShuffle.iota(IntVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.mul($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (int)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (int)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (int)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut3(int[] $lval1, int[] $lval2, int[] $lval3, int $const_small1, IntVector $lval4, IntVector $lval5, IntVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (IntVector) VectorShuffle.iota(IntVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.sub($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (int)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (int)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (int)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut4(int[] $lval1, int[] $lval2, int[] $lval3, int $const_small1, IntVector $lval4, IntVector $lval5, IntVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (IntVector) VectorShuffle.iota(IntVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        for (int $tmp = 0; $tmp < $lval2.length; $tmp++) {
            if ($lval2[$tmp] == 0) {
                $lval2[$tmp] = 1;
            }
        }
        $lval5 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.div($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (int)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (int)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (int)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut5(int[] $lval1, int[] $lval2, int[] $lval3, int $const_small1, IntVector $lval4, IntVector $lval5, IntVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (IntVector) VectorShuffle.iota(IntVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.max($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (int)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (int)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (int)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut6(int[] $lval1, int[] $lval2, int[] $lval3, int $const_small1, IntVector $lval4, IntVector $lval5, IntVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (IntVector) VectorShuffle.iota(IntVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.min($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (int)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (int)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (int)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut7(int[] $lval1, int[] $lval3, int $const_small1, IntVector $lval4, IntVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (IntVector) VectorShuffle.iota(IntVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval6 = $lval4.abs();
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (int)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (int)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (int)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut8(int[] $lval1, int[] $lval3, int $const_small1, IntVector $lval4, IntVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (IntVector) VectorShuffle.iota(IntVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval6 = $lval4.neg();
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (int)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (int)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (int)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut9(int[] $lval1, int[] $lval2, int[] $lval3, int $const_small1, IntVector $lval4, IntVector $lval5, IntVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (IntVector) VectorShuffle.iota(IntVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.blend($lval5, VectorMask.fromLong(IntVector.SPECIES_PREFERRED, $const_small1));
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (int)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (int)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (int)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut10(int[] $lval1, int[] $lval2, int[] $lval3, int $const_small1, IntVector $lval4, IntVector $lval5, IntVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (IntVector) VectorShuffle.iota(IntVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.and($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (int)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (int)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (int)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut11(int[] $lval1, int[] $lval2, int[] $lval3, int $const_small1, IntVector $lval4, IntVector $lval5, IntVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (IntVector) VectorShuffle.iota(IntVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.or($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (int)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (int)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (int)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut12(int[] $lval1, int[] $lval2, int[] $lval3, int $const_small1, IntVector $lval4, IntVector $lval5, IntVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (IntVector) VectorShuffle.iota(IntVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.and($lval5).or($lval4.not());
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (int)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (int)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (int)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut13(int[] $lval1, int[] $lval2, int[] $lval3, int $const_small1, int $expr1, IntVector $lval4, IntVector $lval5, IntVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (IntVector) VectorShuffle.iota(IntVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.lanewise(VectorOperators.LSHL, $expr1 % Integer.SIZE);
        $lval4 = $lval5.lanewise(VectorOperators.LSHR, Integer.SIZE - ($expr1 % Integer.SIZE));
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (int)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (int)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (int)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static IntVector $new() {
        return null;
    }

}

