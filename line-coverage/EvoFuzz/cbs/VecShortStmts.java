
import jdk.incubator.vector.*;

public class VecShortStmts {

    public static int $priority = 70;

    public static void $mut1(short[] $lval1, short[] $lval2, short[] $lval3, int $const_small1, ShortVector $lval4, ShortVector $lval5, ShortVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ShortVector) VectorShuffle.iota(ShortVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.add($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (short)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (short)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (short)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut2(short[] $lval1, short[] $lval2, short[] $lval3, int $const_small1, ShortVector $lval4, ShortVector $lval5, ShortVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ShortVector) VectorShuffle.iota(ShortVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.mul($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (short)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (short)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (short)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut3(short[] $lval1, short[] $lval2, short[] $lval3, int $const_small1, ShortVector $lval4, ShortVector $lval5, ShortVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ShortVector) VectorShuffle.iota(ShortVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.sub($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (short)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (short)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (short)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut4(short[] $lval1, short[] $lval2, short[] $lval3, int $const_small1, ShortVector $lval4, ShortVector $lval5, ShortVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ShortVector) VectorShuffle.iota(ShortVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        for (int $tmp = 0; $tmp < $lval2.length; $tmp++) {
            if ($lval2[$tmp] == 0) {
                $lval2[$tmp] = 1;
            }
        }
        $lval5 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.div($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (short)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (short)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (short)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut5(short[] $lval1, short[] $lval2, short[] $lval3, int $const_small1, ShortVector $lval4, ShortVector $lval5, ShortVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ShortVector) VectorShuffle.iota(ShortVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.max($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (short)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (short)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (short)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut6(short[] $lval1, short[] $lval2, short[] $lval3, int $const_small1, ShortVector $lval4, ShortVector $lval5, ShortVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ShortVector) VectorShuffle.iota(ShortVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.min($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (short)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (short)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (short)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut7(short[] $lval1, short[] $lval3, int $const_small1, ShortVector $lval4, ShortVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ShortVector) VectorShuffle.iota(ShortVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval6 = $lval4.abs();
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (short)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (short)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (short)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut8(short[] $lval1, short[] $lval3, int $const_small1, ShortVector $lval4, ShortVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ShortVector) VectorShuffle.iota(ShortVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval6 = $lval4.neg();
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (short)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (short)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (short)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut9(short[] $lval1, short[] $lval2, short[] $lval3, int $const_small1, ShortVector $lval4, ShortVector $lval5, ShortVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ShortVector) VectorShuffle.iota(ShortVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.blend($lval5, VectorMask.fromLong(ShortVector.SPECIES_PREFERRED, $const_small1));
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (short)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (short)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (short)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut10(short[] $lval1, short[] $lval2, short[] $lval3, int $const_small1, ShortVector $lval4, ShortVector $lval5, ShortVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ShortVector) VectorShuffle.iota(ShortVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.and($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (short)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (short)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (short)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut11(short[] $lval1, short[] $lval2, short[] $lval3, int $const_small1, ShortVector $lval4, ShortVector $lval5, ShortVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ShortVector) VectorShuffle.iota(ShortVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.or($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (short)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (short)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (short)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut12(short[] $lval1, short[] $lval2, short[] $lval3, int $const_small1, ShortVector $lval4, ShortVector $lval5, ShortVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ShortVector) VectorShuffle.iota(ShortVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.and($lval5).or($lval4.not());
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (short)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (short)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (short)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut13(short[] $lval1, short[] $lval2, short[] $lval3, int $const_small1, short $expr1, ShortVector $lval4, ShortVector $lval5, ShortVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ShortVector) VectorShuffle.iota(ShortVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.lanewise(VectorOperators.LSHL, $expr1 % Short.SIZE);
        $lval4 = $lval5.lanewise(VectorOperators.LSHR, Short.SIZE - ($expr1 % Short.SIZE));
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (short)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (short)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (short)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static ShortVector $new() {
        return null;
    }

}

