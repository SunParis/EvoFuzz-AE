public class MethodInlining {
    public static int $priority = 50;
    public int int_inline(int $expr1, int $expr2) {
        return $expr1 + $expr2;
    }

    public double double_inline(double $expr1, double $expr2) {
        return $expr1 + $expr2;
    }
    
    public float float_inline(float $expr1, float $expr2) {
        return $expr1 + $expr2;
    }
    
    public boolean boolean_inline(boolean $expr1, boolean $expr2) {
        return $expr1 && $expr2;
    }
    
    public long long_inline(long $expr1, long $expr2) {
        return $expr1 + $expr2;
    }
    
    public short short_inline(short $expr1, short $expr2) {
        return (short) ($expr1 + $expr2);
    }
    
    public byte byte_inline(byte $expr1, byte $expr2) {
        return (byte) ($expr1 + $expr2);
    }
   
    public char char_inline(char $expr1, char $expr2) {
        return (char) ($expr1 + $expr2);
    } 
    public String string_inline(String $expr1, String $expr2) {
        return $expr1 + $expr2;
    }  
}
