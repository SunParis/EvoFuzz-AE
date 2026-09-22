import java.io.ByteArrayInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayOutputStream;
import java.security.Signature;
import java.security.KeyPair;
import java.security.SignedObject;
import java.security.KeyPairGenerator;

public class TplClass5907 {

    private static final void method() throws Throwable {
        String SIGALG = "SHA1withRSA";
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        KeyPair kp = kpg.generateKeyPair();
        SignedObject so1 = new SignedObject("Hello", kp.getPrivate(), Signature.getInstance(SIGALG));
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(so1);
        out.close();
        byte[] data = byteOut.toByteArray();
        SignedObject so2 = (SignedObject) new ObjectInputStream(new ByteArrayInputStream(data)).readObject();
        if (!so2.getObject().equals("Hello")) {
        }
        if (!so2.getAlgorithm().equals(SIGALG)) {
        }
        if (!so2.verify(kp.getPublic(), Signature.getInstance(SIGALG))) {
        }
    }
}

