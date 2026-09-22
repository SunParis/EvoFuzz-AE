import java.util.Properties;
import java.util.Random;

public class TplClass5259 {

    private static final void method(java.util.Random RAND, java.util.Properties props) throws Throwable {
        for (int j = 0; j < RAND.nextInt(100); j++) {
            String key = "k" + RAND.nextInt(1000);
            String value = "v" + RAND.nextInt(1000);
            props.put(key, value);
        }
    }
}

