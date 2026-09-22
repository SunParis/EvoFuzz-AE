
import jdk.incubator.vector.*;

public class VecShortStmts {

    public static int $priority = 70;

    public static void $mut4(short[] $lval1, short[] $lval2, short[] $lval3, int $const_small1, ShortVector $lval4, ShortVector $lval5, ShortVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ShortVector) VectorShuffle.iota(ShortVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ShortVector.fromArray(ShortVector.SPECIES_PREFERRED, $lval1, $const_small1);
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

}

