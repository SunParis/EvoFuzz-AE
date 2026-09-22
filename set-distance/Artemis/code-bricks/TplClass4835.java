import java.util.Random;

public class TplClass4835 {

    private static final void method(java.util.Random generator) throws Throwable {
        // boolean wrapper
        boolean b = false;
        Boolean B = new Boolean(b);
        if (!B.toString().equals(Boolean.toString(b)))
            ;
        b = true;
        B = new Boolean(b);
        if (!B.toString().equals(Boolean.toString(b)))
            ;
        // char wrapper
        for (int x = 0; x < 100; x++) {
            char c = (char) generator.nextInt();
            Character C = new Character(c);
            if (!C.toString().equals(Character.toString(c)))
                ;
        }
        // byte wrapper
        for (int x = 0; x < 100; x++) {
            byte y = (byte) generator.nextInt();
            Byte Y = new Byte(y);
            if (!Y.toString().equals(Byte.toString(y)))
                ;
        }
        // short wrapper
        for (int x = 0; x < 100; x++) {
            short s = (short) generator.nextInt();
            Short S = new Short(s);
            if (!S.toString().equals(Short.toString(s)))
                ;
        }
        // int wrapper
        for (int x = 0; x < 100; x++) {
            int i = generator.nextInt();
            Integer I = new Integer(i);
            if (!I.toString().equals(Integer.toString(i)))
                ;
        }
        // long wrapper
        for (int x = 0; x < 100; x++) {
            long l = generator.nextLong();
            Long L = new Long(l);
            if (!L.toString().equals(Long.toString(l)))
                ;
        }
        // float wrapper
        for (int x = 0; x < 100; x++) {
            float f = generator.nextFloat();
            Float F = new Float(f);
            if (!F.toString().equals(Float.toString(f)))
                ;
        }
        // double wrapper
        for (int x = 0; x < 100; x++) {
            double d = generator.nextDouble();
            Double D = new Double(d);
            if (!D.toString().equals(Double.toString(d)))
                ;
        }
    }
}

