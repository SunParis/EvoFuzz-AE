import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.ArrayList;
import java.security.cert.CertificateFactory;
import java.security.cert.Certificate;
import java.util.Base64;
import java.security.cert.CertPath;
import java.util.List;

public class TplClass7225 {

    private static final void method(java.lang.String cert2, java.lang.String pkcs7path, java.lang.String cert1) throws Throwable {
        // Make the CertPath whose encoded form has already been stored
        CertificateFactory certFac = CertificateFactory.getInstance("X509");
        final List<Certificate> certs = new ArrayList<>();
        certs.add(certFac.generateCertificate(new ByteArrayInputStream(cert1.getBytes())));
        certs.add(certFac.generateCertificate(new ByteArrayInputStream(cert2.getBytes())));
        CertPath cp = certFac.generateCertPath(certs);
        // Get the encoded form of the CertPath we made
        byte[] encoded = cp.getEncoded("PKCS7");
        // check if it matches the encoded value
        if (!Arrays.equals(encoded, Base64.getMimeDecoder().decode(pkcs7path.getBytes()))) {
        }
        // the CertPath generated from the certificates
        CertPath decodedCP = certFac.generateCertPath(new ByteArrayInputStream(encoded), "PKCS7");
        if (!decodedCP.equals(cp)) {
        }
    }
}

