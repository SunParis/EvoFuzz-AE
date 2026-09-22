
import jdk.incubator.vector.*;

public class VecFloatStmts {

    public static int $priority = 70;

    public static void $mut4(float[] $lval1, float[] $lval2, float[] $lval3, int $const_small1, FloatVector $lval4, FloatVector $lval5, FloatVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (FloatVector) VectorShuffle.iota(FloatVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval1, $const_small1);
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
}


