public class IntExprs {

    public static int $gbvar;

    // Try to trigger max(a, b) + min(a, b) -> a + b
    // jdk17/addnode.cpp Line 80 - 85
    public static int $mut0(int $lval1, int $lval2) {
        return (Math.max($lval1, $lval2) + Math.min($lval2, $lval1));
    }

    // Try to trigger (0 - b) + a -> a - b
    // jdk17/addnode.cpp Line 322
    public static int $mut1(int $lval1, int $lval2) {
        return ((0 - $lval2) + $lval1);
    }

    // Try to trigger (a >>> c) + b -> (a + (b << c)) >>> c
    // jdk17/addnode.cpp Line 341 - 344
    // b and c should be small integers to increase the chance of triggering;
    //  not sure about the "100%" condition, just probabilistic
    public static int $mut2(int $lval1, int $lval2, int $expr1) {
        return (($lval1 >>> ($expr1)) + $lval2);
    }

    // Try to trigger (a >>> rshift) + (a << lshift) -> RotateRight(a, rshift)
    // jdk17/addnode.cpp Line 353 - 361
    public static int $mut3(int $lval1, int $const1) {
        return (($lval1 >>> ($const1 % 15)) + ($lval1 << (32 - ($const1 % 15))));
    }

    // Try to trigger (~a + 1) -> -a
    // jdk17/addnode.cpp Line 370 - 371
    public static int $mut4(int $lval1) {
        return ((~$lval1 + 1));
    }

    // Try to trigger (a - b) + (b - c) -> a - c and similar optimizations
    // jdk17/addnode.cpp Line 449 - 469
    public static int $mut5(int $lval1, int $lval2, int $lval3) {
        return (($lval1 - $lval2) + ($lval2 - $lval3));
    }

    // Try to trigger (a - b) + b -> a and similar optimizations
    // jdk17/addnode.cpp Line 495 - 503
    public static int $mut6(int $lval1, int $lval2) {
        return (($lval1 - $lval2) + $lval2);
    }
    // Try to trigger (a - b) + b -> a and similar optimizations
    // jdk17/addnode.cpp Line 495 - 503
    public static int $mut62(int $lval1, int $lval2) {
        return ($lval2 + ($lval1 - $lval2));
    }

    // Try to trigger (a << shift) | (a >>> (32 - shift))
    // jdk17/addnode.cpp Line 810 - 833、799 - 806
    public static int $mut7(int $lval1, int $const_small1) {
        return ((($lval1 << ($const_small1 % 15)) | ($lval1 >>> (32 - ($const_small1 % 15)))));
    }

    // Try to trigger (a >>> shift) | (a << (32 - shift))
    // jdk17/addnode.cpp Line 810 - 833、799 - 806
    public static int $mut71(int $lval1, int $const_small1) {
        return ((($lval1 >>> ($const_small1 % 15)) | ($lval1 << (32 -  ($const_small1 % 15)))));
    }

    // Try to trigger (a << (b & 31)) | (a >>> (32 - (b & 31)))
    // jdk17/addnode.cpp Line 810 - 833、799 - 806
    public static int $mut72(int $lval1, int $lval2) {
        return ((($lval1 << ($lval2 & 31)) | ($lval1 >>> (32 - ($lval2 & 31)))));
    }

    // Try to trigger (a >>> (b & 31)) | (a << (32 - (b & 31)))
    // jdk17/addnode.cpp Line 810 - 833、799 - 806
    public static int $mut73(int $lval1, int $lval2) {
        return ((($lval1 >>> ($lval2 & 31)) | ($lval1 << (32 - ($lval2 & 31)))));
    }

    // Try to trigger branchless cMoveLNode optimization
    // jdk17/addnode.cpp Line 1130 - 1134
    public static int $mut8(int $lval1, int $lval2) {
        // Math.min(0, a - b)
        return Math.min(0, $lval1 - $lval2);
    }

    // Try to trigger branchless cMoveLNode optimization (variant: Math.min(a - b, 0))
    public static int $mut81(int $lval1, int $lval2) {
        return Math.min($lval1 - $lval2, 0);
    }

    // Try to trigger branchless cMoveLNode optimization (variant: Math.max(0, a - b))
    public static int $mut82(int $lval1, int $lval2) {
        return Math.max(0, $lval1 - $lval2);
    }

    // Try to trigger branchless cMoveLNode optimization (variant: Math.max(a - b, 0))
    public static int $mut83(int $lval1, int $lval2) {
        return Math.max($lval1 - $lval2, 0);
    }

	// Try to trigger Math.min(Math.min(a, b), c) -> Math.min(a, Math.min(b, c))
    // jdk17/addnode.cpp Line 1184 - 1193
	public static int $mut9(int $lval1, int $lval2, int $lval3) {
		return Math.min(Math.min($lval1, $lval2), $lval3);
	}

    // Try to trigger Math.min(a + c0, Math.min(c, a + c1)) -> Math.min(a + Math.min(c0, c1), c)
    // jdk17/addnode.cpp Line 1177 - 1256
    public static int $mut10(int $lval1, int $lval2, int $const1, int $const2) {
        return Math.min($lval1 + $const1, Math.min($lval2, $lval1 + $const2));
    }

    // Variant 1: Math.min(a + c0, Math.min(a + c1, c))
    public static int $mut101(int $lval1, int $lval2, int $const1, int $const2) {
        return Math.min($lval1 + $const1, Math.min($lval1 + $const2, $lval2));
    }

    // Variant 2: Math.min(a + c0, a + c1) + c
    public static int $mut102(int $lval1, int $lval2, int $const1, int $const2) {
        return Math.min($lval1 + $const1, $lval1 + $const2) + $lval2;
    }

    // Variant 3: Math.min(Math.min(a, c + c0), a + c1)
    public static int $mut103(int $lval1, int $lval2, int $const1, int $const2) {
        return Math.min(Math.min($lval1, $lval2 + $const1), $lval1 + $const2);
    }

	// Try to trigger (a & ~0b111) / 8 -> a >> 3
    // jdk17/divnode.cpp Line 125 - 130
	public static int $mut11(int $lval1) {
		return ($lval1 & ~0b111) / 8;
	}

	// Try to trigger int_min / -1
    // jdk17/divnode.cpp Line 523 - 534
	public static int $mut12() {
		return Integer.MIN_VALUE / -1;
	}

	// Try to trigger min_int % -1
    // jdk17/divnode.cpp Line 1000 - 1006
	public static int $mut13() {
		return Integer.MIN_VALUE % -1;
	}

	// Try to trigger Long.numberOfLeadingZeros(const long)
    // jdk17/countbitsnode.cpp Line 61 - 71
	public static int $mut14(long $expr1) {
		return Long.numberOfLeadingZeros($expr1);
	}

	// Try to trigger Long.numberOfLeadingZeros(const long)
    // jdk17/countbitsnode.cpp Line 81 - 93
	public static int $mut15(int $expr1) {
		return Integer.numberOfTrailingZeros($expr1);
	}

	// Try to trigger Long.numberOfLeadingZeros(const long)
    // jdk17/countbitsnode.cpp Line 103 - 116
	public static int $mut16(long $expr1) {
		return Long.numberOfTrailingZeros($expr1);
	}

	// $mut17: Math.addExact(a, b)
	public static void $mut17(int $lval1, int $lval2, int $lval3) {
        $lval2 /= 2;
        $lval1 /= 2;
		$lval3 = Math.addExact($lval1, $lval2);
	}

	// $mut18: Math.subtractExact(a, b)
	public static void $mut18(int $lval1, int $lval2, int $lval3) {
		$lval2 /= 2;
        $lval1 /= 2;
		$lval3 = Math.subtractExact($lval1, $lval2);
	}

	// $mut19: Math.multiplyExact(a, b)
	public static void $mut19(int $lval1, int $lval2, int $lval3) {
		$lval1 = $lval1 % 10000;
        $lval2 = $lval2 % 10000;
		$lval3 = Math.multiplyExact($lval1, $lval2);
	}

    public static int $mut20(int $expr1, int $expr2) {
		return $expr1 + $expr2;
	}

    public static int $mut21(int $expr1, int $expr2) {
		return $expr1 - $expr2;
	}

    public static int $mut22(int $expr1, int $expr2) {
		return $expr1 * $expr2;
	}

    public static int $mut23(int $expr1, int $expr2) {
		return $expr1 / Math.max(1, $expr2);
	}

    public static int $mut24(int $expr1, int $expr2) {
        return $expr1 % Math.max(1, $expr2);
    }

    public static void $mut25(int $expr1) {
        $gbvar = $expr1 + 10;
    }

    public static void $mut26(int $expr1) {
        $gbvar = $expr1 * 50000;
    }

    public static int $mut27(int $expr1) {
		return ($expr1);
	}

    public static int $mut28(int $expr1, int $expr2) {
        return ((($expr1) & ($expr2)));
    }

    public static int $mut29(int $expr1, int $expr2) {
        return ((($expr1) | ($expr2)));
    }

    public static int $mut30(int $expr1, int $expr2) {
        return ((($expr1) ^ ($expr2)));
    }

    public static int $mut31(int[] $lval1, int $expr1) {
        return $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)];
    }

    public static void $mut32(int[] $lval1, int $expr1, int $expr2) {
        $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)] = $expr2;
    }

}
