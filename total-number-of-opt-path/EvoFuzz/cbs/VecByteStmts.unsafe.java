
import jdk.incubator.vector.*;

public class VecByteStmts {

    public static int $priority = 70;

    public static void $mut1(byte[] $lval1, byte[] $lval2, byte[] $lval3, int $const_small1, ByteVector $lval4, ByteVector $lval5, ByteVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ByteVector) VectorShuffle.iota(ByteVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.div($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (byte)(1 + $lval6.reduceLanes(VectorOperators.ADD));
            $lval1[2] = (byte)(1 + $lval6.reduceLanes(VectorOperators.XOR));
            $lval1[3] = (byte)(1 + $lval6.reduceLanes(VectorOperators.MAX));
        }
    }

}

