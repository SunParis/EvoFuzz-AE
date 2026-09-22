import java.lang.reflect.Array;

public class TplClass4574 {

    private static final void method() throws Throwable {
        Object charArray = Array.newInstance(Character.TYPE, 7);
        char[] array = (char[]) charArray;
        array[0] = '0';
        array[1] = 'W';
        array[2] = '2';
        array[3] = '3';
        array[4] = 'X';
        array[5] = '5';
        array[6] = '6';
        Array.setChar(charArray, 1, '1');
        Array.setChar(charArray, 4, '4');
        try {
            Array.setShort(charArray, 3, (short) 'Y');
        } catch (IllegalArgumentException iae) {
        }
        try {
            Array.setInt(charArray, 5, 'Z');
        } catch (IllegalArgumentException iae) {
        }
        try {
            for (int i = 0; i < array.length; i++) {
                if (Array.getInt(charArray, i) - '0' != i) {
                }
            }
            if (Array.getInt(charArray, 4) != '4') {
            }
        } catch (IllegalArgumentException iae) {
        }
        try {
            Array.getByte(charArray, 2);
        } catch (IllegalArgumentException iae) {
        }
        Array.setChar(charArray, 3, (char) 0xffff);
        try {
            if (Array.getInt(charArray, 3) != 0xffff) {
            }
        } catch (IllegalArgumentException iae) {
        }
    }
}

