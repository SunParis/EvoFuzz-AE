
import jdk.incubator.vector.*;

public class VecIntStmts {

    public static int $priority = 70;

    public static void $mut4(int[] $lval1, int[] $lval2, int[] $lval3, int $const_small1, IntVector $lval4, IntVector $lval5, IntVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (IntVector) VectorShuffle.iota(IntVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval1, $const_small1);
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

}

