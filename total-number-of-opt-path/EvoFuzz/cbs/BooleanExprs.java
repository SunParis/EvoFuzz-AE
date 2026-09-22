import java.util.Arrays;

public class BooleanExprs {

    public static boolean $gbvar;
    
    // int comparisons
    public static boolean $mut1(int $expr1, int $expr2) {
        return (($expr1) > ($expr2));
    }
    
    public static boolean $mut2(int $expr1, int $expr2) {
        return (($expr1) < ($expr2));
    }
    
    public static boolean $mut3(int $expr1, int $expr2) {
        return (($expr1) >= ($expr2));
    }
    
    public static boolean $mut4(int $expr1, int $expr2) {
        return (($expr1) <= ($expr2));
    }
    
    public static boolean $mut5(int $expr1, int $expr2) {
        return (($expr1) == ($expr2));
    }
    
    public static boolean $mut6(int $expr1, int $expr2) {
        return (($expr1) != ($expr2));
    }

    // long comparisons
    public static boolean $mut7(long $expr3, long $expr4) {
        return (($expr3) > ($expr4));
    }
    
    public static boolean $mut8(long $expr3, long $expr4) {
        return (($expr3) < ($expr4));
    }
    
    public static boolean $mut9(long $expr3, long $expr4) {
        return (($expr3) >= ($expr4));
    }
    
    public static boolean $mut10(long $expr3, long $expr4) {
        return (($expr3) <= ($expr4));
    }
    
    public static boolean $mut11(long $expr3, long $expr4) {
        return (($expr3) == ($expr4));
    }
    
    public static boolean $mut12(long $expr3, long $expr4) {
        return (($expr3) != ($expr4));
    }


    // float comparisons
    public static boolean $mut13(float $expr5, float $expr6) {
        return (($expr5) > ($expr6));
    }
    
    public static boolean $mut14(float $expr5, float $expr6) {
        return (($expr5) < ($expr6));
    }
    
    public static boolean $mut15(float $expr5, float $expr6) {
        return (($expr5) >= ($expr6));
    }
    
    public static boolean $mut16(float $expr5, float $expr6) {
        return (($expr5) <= ($expr6));
    }
    
    public static boolean $mut17(float $expr5, float $expr6) {
        return (($expr5) == ($expr6));
    }
    
    public static boolean $mut18(float $expr5, float $expr6) {
        return (($expr5) != ($expr6));
    }


    // double comparisons
    public static boolean $mut19(double $expr7, double $expr8) {
        return (($expr7) > ($expr8));
    }
    
    public static boolean $mut20(double $expr7, double $expr8) {
        return (($expr7) < ($expr8));
    }
    
    public static boolean $mut21(double $expr7, double $expr8) {
        return (($expr7) >= ($expr8));
    }
    
    public static boolean $mut22(double $expr7, double $expr8) {
        return (($expr7) <= ($expr8));
    }
    
    public static boolean $mut23(double $expr7, double $expr8) {
        return (($expr7) == ($expr8));
    }
    
    public static boolean $mut24(double $expr7, double $expr8) {
        return (($expr7) != ($expr8));
    }


    // char comparisons
    public static boolean $mut25(char $expr9, char $expr10) {
        return (($expr9) > ($expr10));
    }
    
    public static boolean $mut26(char $expr9, char $expr10) {
        return (($expr9) < ($expr10));
    }
    
    public static boolean $mut27(char $expr9, char $expr10) {
        return (($expr9) >= ($expr10));
    }
    
    public static boolean $mut28(char $expr9, char $expr10) {
        return (($expr9) <= ($expr10));
    }
    
    public static boolean $mut29(char $expr9, char $expr10) {
        return (($expr9) == ($expr10));
    }
    
    public static boolean $mut30(char $expr9, char $expr10) {
        return (($expr9) != ($expr10));
    }


    // byte comparisons
    public static boolean $mut31(byte $expr11, byte $expr12) {
        return (($expr11) > ($expr12));
    }
    
    public static boolean $mut32(byte $expr11, byte $expr12) {
        return (($expr11) < ($expr12));
    }
    
    public static boolean $mut33(byte $expr11, byte $expr12) {
        return (($expr11) >= ($expr12));
    }
    
    public static boolean $mut34(byte $expr11, byte $expr12) {
        return (($expr11) <= ($expr12));
    }
    
    public static boolean $mut35(byte $expr11, byte $expr12) {
        return (($expr11) == ($expr12));
    }
    
    public static boolean $mut36(byte $expr11, byte $expr12) {
        return (($expr11) != ($expr12));
    }


    // short comparisons
    public static boolean $mut37(short $expr13, short $expr14) {
        return (($expr13) > ($expr14));
    }
    
    public static boolean $mut38(short $expr13, short $expr14) {
        return (($expr13) < ($expr14));
    }
    
    public static boolean $mut39(short $expr13, short $expr14) {
        return (($expr13) >= ($expr14));
    }
    
    public static boolean $mut40(short $expr13, short $expr14) {
        return (($expr13) <= ($expr14));
    }
    
    public static boolean $mut41(short $expr13, short $expr14) {
        return (($expr13) == ($expr14));
    }
    
    public static boolean $mut42(short $expr13, short $expr14) {
        return (($expr13) != ($expr14));
    }

    public static boolean $mut43(float $expr1, float $expr2) {
        return (Float.floatToRawIntBits(($expr1)) == Float.floatToRawIntBits(($expr2)));
    }

    public static boolean $mut44(Object[] $lval1, Object[] $lval2) {
        return (Arrays.equals(($lval1), ($lval2)));
    }

    public static boolean $mut45(boolean $expr1) {
        return (!($expr1));
    }

    public static boolean $mut46(boolean $expr1, boolean $expr2) {
        return (($expr1) && ($expr2));
    }

    public static boolean $mut47(boolean $expr1, boolean $expr2) {
        return (($expr1) || ($expr2));
    }

    public static void $mut48(boolean $expr1) {
        $gbvar = (($expr1) || ($gbvar));
    }

    public static void $mut49(boolean $expr1, boolean $expr2) {
        $gbvar = ((($expr1) && ($gbvar)) || (($expr2) && ($gbvar)) || (($expr1) && ($expr2)));
    }

}
