import java.util.Random;

public class TplClass4820 {

    private static final void method(java.util.Random generator) throws Throwable {
        // char wrapper
        for (int x = 0; x < 100; x++) {
            char c = (char) generator.nextInt();
            Character C = new Character(c);
            if (!C.toString().equals(Character.toString(c)))
                ;
        }
    }
}

