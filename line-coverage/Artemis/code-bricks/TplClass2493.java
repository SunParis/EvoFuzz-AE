public class TplClass2493 {

    private static final void method(float mFloat1, double mDouble1, float mFloat2, double mDouble2, byte mByte2, byte mByte1, long mVolatileLong1, long mVolatileLong2, long mLong2, long mLong1, int mInt1, char mChar2, char mChar1, int mInt2, short mShort2, short mShort1, boolean mBoolean2, boolean mBoolean1) throws Throwable {
        mBoolean1 = true;
        mBoolean2 = false;
        mByte1 = 127;
        mByte2 = -128;
        mChar1 = 32767;
        mChar2 = 65535;
        mShort1 = 32767;
        mShort2 = -32768;
        mInt1 = 65537;
        mInt2 = -65537;
        mFloat1 = 3.1415f;
        // -inf
        mFloat2 = -1.0f / 0.0f;
        // 0x1122334455667788
        mLong1 = 1234605616436508552L;
        mLong2 = -1234605616436508552L;
        mDouble1 = 3.1415926535;
        // +inf
        mDouble2 = 1.0 / 0.0;
        mVolatileLong1 = mLong1 - 1;
        mVolatileLong2 = mLong2 + 1;
    }
}

