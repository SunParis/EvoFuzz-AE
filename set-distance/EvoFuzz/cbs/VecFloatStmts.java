
import jdk.incubator.vector.*;

public class VecFloatStmts {

    public static int $priority = 70;

    public static void $mut1(float[] $lval1, float[] $lval2, float[] $lval3, int $const_small1, FloatVector $lval4, FloatVector $lval5, FloatVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (FloatVector) VectorShuffle.iota(FloatVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.add($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (float)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (float)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (float)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut2(float[] $lval1, float[] $lval2, float[] $lval3, int $const_small1, FloatVector $lval4, FloatVector $lval5, FloatVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (FloatVector) VectorShuffle.iota(FloatVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.mul($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (float)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (float)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (float)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut3(float[] $lval1, float[] $lval2, float[] $lval3, int $const_small1, FloatVector $lval4, FloatVector $lval5, FloatVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (FloatVector) VectorShuffle.iota(FloatVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.sub($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (float)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (float)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (float)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut4(float[] $lval1, float[] $lval2, float[] $lval3, int $const_small1, FloatVector $lval4, FloatVector $lval5, FloatVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (FloatVector) VectorShuffle.iota(FloatVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        for (int $tmp = 0; $tmp < $lval2.length; $tmp++) {
            if ($lval2[$tmp] == 0.0f) {
                $lval2[$tmp] = 1.0f;
            }
        }
        $lval5 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.div($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (float)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (float)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (float)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut5(float[] $lval1, float[] $lval2, float[] $lval3, int $const_small1, FloatVector $lval4, FloatVector $lval5, FloatVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (FloatVector) VectorShuffle.iota(FloatVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.max($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (float)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (float)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (float)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut6(float[] $lval1, float[] $lval2, float[] $lval3, int $const_small1, FloatVector $lval4, FloatVector $lval5, FloatVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (FloatVector) VectorShuffle.iota(FloatVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.min($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (float)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (float)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (float)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut7(float[] $lval1, float[] $lval3, int $const_small1, FloatVector $lval4, FloatVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (FloatVector) VectorShuffle.iota(FloatVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval6 = $lval4.abs();
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (float)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (float)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (float)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut8(float[] $lval1, float[] $lval3, int $const_small1, FloatVector $lval4, FloatVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (FloatVector) VectorShuffle.iota(FloatVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval6 = $lval4.neg();
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (float)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (float)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (float)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut9(float[] $lval1, float[] $lval2, float[] $lval3, int $const_small1, FloatVector $lval4, FloatVector $lval5, FloatVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (FloatVector) VectorShuffle.iota(FloatVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.blend($lval5, VectorMask.fromLong(FloatVector.SPECIES_PREFERRED, $const_small1));
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (float)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (float)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (float)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static void $mut10(float[] $lval1, float[] $lval2, float[] $lval3, int $const_small1, FloatVector $lval4, FloatVector $lval5, FloatVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (FloatVector) VectorShuffle.iota(FloatVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.fma($lval5, $lval4);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (float)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (float)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (float)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

    public static FloatVector $new() {
        return null;
    }

}


