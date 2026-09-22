
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
        $lval6 = $lval4.add($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (byte)($lval6.reduceLanes(VectorOperators.ADD) + 1);
            $lval1[2] = (byte)($lval6.reduceLanes(VectorOperators.XOR) + 1);
            $lval1[3] = (byte)($lval6.reduceLanes(VectorOperators.MAX) + 1);
        }
    }

    public static void $mut2(byte[] $lval1, byte[] $lval2, byte[] $lval3, int $const_small1, ByteVector $lval4, ByteVector $lval5, ByteVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ByteVector) VectorShuffle.iota(ByteVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.mul($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (byte)($lval6.reduceLanes(VectorOperators.ADD) + 1);
            $lval1[2] = (byte)($lval6.reduceLanes(VectorOperators.XOR) + 1);
            $lval1[3] = (byte)($lval6.reduceLanes(VectorOperators.MAX) + 1);
        }
    }

    public static void $mut3(byte[] $lval1, byte[] $lval2, byte[] $lval3, int $const_small1, ByteVector $lval4, ByteVector $lval5, ByteVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ByteVector) VectorShuffle.iota(ByteVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.sub($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (byte)($lval6.reduceLanes(VectorOperators.ADD) + 1);
            $lval1[2] = (byte)($lval6.reduceLanes(VectorOperators.XOR) + 1);
            $lval1[3] = (byte)($lval6.reduceLanes(VectorOperators.MAX) + 1);
        }
    }

    public static void $mut4(byte[] $lval1, byte[] $lval2, byte[] $lval3, int $const_small1, ByteVector $lval4, ByteVector $lval5, ByteVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ByteVector) VectorShuffle.iota(ByteVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        for (int $tmp = 0; $tmp < $lval2.length; $tmp++) {
            if ($lval2[$tmp] == 0) {
                $lval2[$tmp] = 1;
            }
        }
        $lval5 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.div($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (byte)($lval6.reduceLanes(VectorOperators.ADD) + 1);
            $lval1[2] = (byte)($lval6.reduceLanes(VectorOperators.XOR) + 1);
            $lval1[3] = (byte)($lval6.reduceLanes(VectorOperators.MAX) + 1);
        }
    }

    public static void $mut5(byte[] $lval1, byte[] $lval2, byte[] $lval3, int $const_small1, ByteVector $lval4, ByteVector $lval5, ByteVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ByteVector) VectorShuffle.iota(ByteVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.max($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (byte)($lval6.reduceLanes(VectorOperators.ADD) + 1);
            $lval1[2] = (byte)($lval6.reduceLanes(VectorOperators.XOR) + 1);
            $lval1[3] = (byte)($lval6.reduceLanes(VectorOperators.MAX) + 1);
        }
    }

    public static void $mut6(byte[] $lval1, byte[] $lval2, byte[] $lval3, int $const_small1, ByteVector $lval4, ByteVector $lval5, ByteVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ByteVector) VectorShuffle.iota(ByteVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.min($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (byte)($lval6.reduceLanes(VectorOperators.ADD) + 1);
            $lval1[2] = (byte)($lval6.reduceLanes(VectorOperators.XOR) + 1);
            $lval1[3] = (byte)($lval6.reduceLanes(VectorOperators.MAX) + 1);
        }
    }

    public static void $mut7(byte[] $lval1, byte[] $lval3, int $const_small1, ByteVector $lval4, ByteVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ByteVector) VectorShuffle.iota(ByteVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval6 = $lval4.abs();
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (byte)($lval6.reduceLanes(VectorOperators.ADD) + 1);
            $lval1[2] = (byte)($lval6.reduceLanes(VectorOperators.XOR) + 1);
            $lval1[3] = (byte)($lval6.reduceLanes(VectorOperators.MAX) + 1);
        }
    }

    public static void $mut8(byte[] $lval1, byte[] $lval3, int $const_small1, ByteVector $lval4, ByteVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ByteVector) VectorShuffle.iota(ByteVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval6 = $lval4.neg();
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (byte)($lval6.reduceLanes(VectorOperators.ADD) + 1);
            $lval1[2] = (byte)($lval6.reduceLanes(VectorOperators.XOR) + 1);
            $lval1[3] = (byte)($lval6.reduceLanes(VectorOperators.MAX) + 1);
        }
    }

    public static void $mut9(byte[] $lval1, byte[] $lval2, byte[] $lval3, int $const_small1, ByteVector $lval4, ByteVector $lval5, ByteVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ByteVector) VectorShuffle.iota(ByteVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.blend($lval5, VectorMask.fromLong(ByteVector.SPECIES_PREFERRED, $const_small1));
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (byte)($lval6.reduceLanes(VectorOperators.ADD) + 1);
            $lval1[2] = (byte)($lval6.reduceLanes(VectorOperators.XOR) + 1);
            $lval1[3] = (byte)($lval6.reduceLanes(VectorOperators.MAX) + 1);
        }
    }

    public static void $mut10(byte[] $lval1, byte[] $lval2, byte[] $lval3, int $const_small1, ByteVector $lval4, ByteVector $lval5, ByteVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ByteVector) VectorShuffle.iota(ByteVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.and($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (byte)($lval6.reduceLanes(VectorOperators.ADD) + 1);
            $lval1[2] = (byte)($lval6.reduceLanes(VectorOperators.XOR) + 1);
            $lval1[3] = (byte)($lval6.reduceLanes(VectorOperators.MAX) + 1);
        }
    }

    public static void $mut11(byte[] $lval1, byte[] $lval2, byte[] $lval3, int $const_small1, ByteVector $lval4, ByteVector $lval5, ByteVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ByteVector) VectorShuffle.iota(ByteVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.or($lval5);
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (byte)($lval6.reduceLanes(VectorOperators.ADD) + 1);
            $lval1[2] = (byte)($lval6.reduceLanes(VectorOperators.XOR) + 1);
            $lval1[3] = (byte)($lval6.reduceLanes(VectorOperators.MAX) + 1);
        }
    }

    public static void $mut12(byte[] $lval1, byte[] $lval2, byte[] $lval3, int $const_small1, ByteVector $lval4, ByteVector $lval5, ByteVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ByteVector) VectorShuffle.iota(ByteVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.and($lval5).or($lval4.not());
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (byte)($lval6.reduceLanes(VectorOperators.ADD) + 1);
            $lval1[2] = (byte)($lval6.reduceLanes(VectorOperators.XOR) + 1);
            $lval1[3] = (byte)($lval6.reduceLanes(VectorOperators.MAX) + 1);
        }
    }

    public static void $mut13(byte[] $lval1, byte[] $lval2, byte[] $lval3, int $const_small1, byte $expr1, ByteVector $lval4, ByteVector $lval5, ByteVector $lval6) {
        if ((int)($lval3[0]) % 3 < 1) {
            $lval4 = (ByteVector) VectorShuffle.iota(ByteVector.SPECIES_PREFERRED, 0, $const_small1, true).toVector();
        }
        else {
            $lval4 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval1, $const_small1);
        }
        $lval5 = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, $lval2, $const_small1);
        $lval6 = $lval4.lanewise(VectorOperators.LSHL, $expr1 % Byte.SIZE);
        $lval4 = $lval5.lanewise(VectorOperators.LSHR, Byte.SIZE - ($expr1 % Byte.SIZE));
        $lval6.intoArray($lval3, $const_small1);
        if (($lval1 == null || $lval2 == null || $lval3 == null) && ($const_small1 % 5 <= 3)) {
            $lval1[1] = (byte)($lval6.reduceLanes(VectorOperators.ADD) + 1);
            $lval1[2] = (byte)($lval6.reduceLanes(VectorOperators.XOR) + 1);
            $lval1[3] = (byte)($lval6.reduceLanes(VectorOperators.MAX) + 1);
        }
    }

    public static ByteVector $new() {
        return null;
    }

}

