import jdk.incubator.vector.*;

class VecStmtsV2 {
    
    public static void $mut1(int[] $lval1, int[] $lval2, int[] $lval3) {
        for (int $tmp5 = 0; $tmp5 < $lval1.length; $tmp5 += IntVector.SPECIES_PREFERRED.length()) {
            IntVector $tmp1 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval1, $tmp5);
            IntVector $tmp2 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval3, $tmp5);
            IntVector $tmp3 = $tmp1.lanewise(VectorOperators.ROL, 7);
            IntVector $tmp4 = $tmp1.lanewise(VectorOperators.ROR, $tmp2);
            $tmp3.add($tmp4).intoArray($lval2, $tmp5);
        }
    }

    public static void $mut2(double[] $lval1, double[] $lval2, double[] $lval3, double[] $lval4) {
        for (int $tmp5 = 0; $tmp5 < $lval1.length; $tmp5 += DoubleVector.SPECIES_PREFERRED.length()) {
            DoubleVector $tmp1 = DoubleVector.fromArray(DoubleVector.SPECIES_PREFERRED, $lval1, $tmp5);
            DoubleVector $tmp2 = DoubleVector.fromArray(DoubleVector.SPECIES_PREFERRED, $lval3, $tmp5);
            DoubleVector $tmp3 = $tmp1.lanewise(VectorOperators.ROL, 7);
            DoubleVector $tmp4 = $tmp1.div($tmp2).lanewise(VectorOperators.FMA, $tmp2, $tmp3).neg().abs().sqrt();
            $tmp4.intoArray($lval4, $tmp5);
        }
    }

    public static void $mut3(float[] $lval1, float[] $lval2, float[] $lval3) {
        for (int $tmp5 = 0; $tmp5 < $lval1.length; $tmp5 += FloatVector.SPECIES_PREFERRED.length()) {
            FloatVector $tmp1 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval1, $tmp5);
            FloatVector $tmp2 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval3, $tmp5);
            FloatVector $tmp3 = $tmp1.div($tmp2).neg().abs().sqrt();
            $tmp3.intoArray($lval3, $tmp5);
        }
    }

    public static void $mut4(float[] $lval1, float[] $lval2, float[] $lval3, int $lval4) {
        for (int $tmp5 = 0; $tmp5 < $lval1.length; $tmp5 += FloatVector.SPECIES_PREFERRED.length()) {
            FloatVector $tmp1 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval1, $tmp5);
            FloatVector $tmp2 = FloatVector.fromArray(FloatVector.SPECIES_PREFERRED, $lval3, $tmp5);
            VectorMask<Float> $tmp3 = $tmp1.compare(VectorOperators.GT, $tmp2);
            FloatVector $tmp4 = $tmp2.blend($tmp1, $tmp3);
            $lval4 = $tmp3.trueCount();
            $lval3[$tmp5] = $tmp5;
            $tmp4.intoArray($lval3, $tmp5);
        }
    }

    public static void $mut5(long[] $lval1, long $lval2) {
        for (int $tmp5 = 0; $tmp5 < $lval1.length; $tmp5 += LongVector.SPECIES_PREFERRED.length()) {
            LongVector $tmp1 = LongVector.fromArray(LongVector.SPECIES_PREFERRED, $lval1, $tmp5);
            $lval2 ^= $tmp1.reduceLanes(VectorOperators.XOR);
            $lval2 |= $tmp1.reduceLanes(VectorOperators.OR);
            $lval2 &= $tmp1.reduceLanes(VectorOperators.AND);
            $lval2 += $tmp1.reduceLanes(VectorOperators.ADD);
            $lval2 -= $tmp1.reduceLanes(VectorOperators.MIN);
            $lval2 += $tmp1.reduceLanes(VectorOperators.MAX);
        }
    }

    public static void $mut6(int[] $lval1, int $lval2) {
        IntVector $tmp1 = IntVector.fromArray(IntVector.SPECIES_PREFERRED, $lval1, 0);
        int $tmp2 = $tmp1.lane(0);
        IntVector $tmp3 = $tmp1.withLane(1, $tmp2 + 1);
        FloatVector $tmp4 = $tmp3.reinterpretAsFloats();
        $lval2 = (int) $tmp4.lane(0);
    }
}