import java.security.SignatureException;
import java.security.cert.CertificateParsingException;
import java.security.ProviderException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.KeyException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.DigestException;
import java.security.cert.CRLException;
import java.security.KeyStoreException;
import java.security.spec.InvalidKeySpecException;
import java.security.KeyManagementException;
import java.security.InvalidAlgorithmParameterException;

public class TplClass7336 {

    private static final void method(java.lang.String MSG, java.lang.Exception cause) throws Throwable {
        SecurityException se = new SecurityException(cause);
        if (!se.getCause().equals(cause)) {
        }
        se = new SecurityException(MSG, cause);
        if (!se.getMessage().equals(MSG) || !se.getCause().equals(cause)) {
        }
        DigestException de = new DigestException(cause);
        if (!de.getCause().equals(cause)) {
        }
        de = new DigestException(MSG, cause);
        if (!de.getMessage().equals(MSG) || !de.getCause().equals(cause)) {
        }
        GeneralSecurityException gse = new GeneralSecurityException(cause);
        if (!gse.getCause().equals(cause)) {
        }
        gse = new GeneralSecurityException(MSG, cause);
        if (!gse.getMessage().equals(MSG) || !gse.getCause().equals(cause)) {
        }
        InvalidAlgorithmParameterException iape = new InvalidAlgorithmParameterException(cause);
        if (!iape.getCause().equals(cause)) {
        }
        iape = new InvalidAlgorithmParameterException(MSG, cause);
        if (!iape.getMessage().equals(MSG) || !iape.getCause().equals(cause)) {
        }
        InvalidKeyException ike = new InvalidKeyException(cause);
        if (!ike.getCause().equals(cause)) {
        }
        ike = new InvalidKeyException(MSG, cause);
        if (!ike.getMessage().equals(MSG) || !ike.getCause().equals(cause)) {
        }
        InvalidKeySpecException ikse = new InvalidKeySpecException(cause);
        if (!ikse.getCause().equals(cause)) {
        }
        ikse = new InvalidKeySpecException(MSG, cause);
        if (!ikse.getMessage().equals(MSG) || !ikse.getCause().equals(cause)) {
        }
        KeyException ke = new KeyException(cause);
        if (!ke.getCause().equals(cause)) {
        }
        ke = new KeyException(MSG, cause);
        if (!ke.getMessage().equals(MSG) || !ke.getCause().equals(cause)) {
        }
        KeyManagementException kme = new KeyManagementException(cause);
        if (!kme.getCause().equals(cause)) {
        }
        kme = new KeyManagementException(MSG, cause);
        if (!kme.getMessage().equals(MSG) || !kme.getCause().equals(cause)) {
        }
        KeyStoreException kse = new KeyStoreException(cause);
        if (!kse.getCause().equals(cause)) {
        }
        kse = new KeyStoreException(MSG, cause);
        if (!kse.getMessage().equals(MSG) || !kse.getCause().equals(cause)) {
        }
        NoSuchAlgorithmException nsae = new NoSuchAlgorithmException(cause);
        if (!nsae.getCause().equals(cause)) {
        }
        nsae = new NoSuchAlgorithmException(MSG, cause);
        if (!nsae.getMessage().equals(MSG) || !nsae.getCause().equals(cause)) {
        }
        ProviderException pe = new ProviderException(cause);
        if (!pe.getCause().equals(cause)) {
        }
        pe = new ProviderException(MSG, cause);
        if (!pe.getMessage().equals(MSG) || !pe.getCause().equals(cause)) {
        }
        SignatureException sige = new SignatureException(cause);
        if (!sige.getCause().equals(cause)) {
        }
        sige = new SignatureException(MSG, cause);
        if (!sige.getMessage().equals(MSG) || !sige.getCause().equals(cause)) {
        }
        CRLException crle = new CRLException(cause);
        if (!crle.getCause().equals(cause)) {
        }
        crle = new CRLException(MSG, cause);
        if (!crle.getMessage().equals(MSG) || !crle.getCause().equals(cause)) {
        }
        CertificateException ce = new CertificateException(cause);
        if (!ce.getCause().equals(cause)) {
        }
        ce = new CertificateException(MSG, cause);
        if (!ce.getMessage().equals(MSG) || !ce.getCause().equals(cause)) {
        }
        CertificateParsingException cpe = new CertificateParsingException(cause);
        if (!cpe.getCause().equals(cause)) {
        }
        cpe = new CertificateParsingException(MSG, cause);
        if (!cpe.getMessage().equals(MSG) || !cpe.getCause().equals(cause)) {
        }
        CertificateEncodingException cee = new CertificateEncodingException(cause);
        if (!cee.getCause().equals(cause)) {
        }
        cee = new CertificateEncodingException(MSG, cause);
        if (!cee.getMessage().equals(MSG) || !cee.getCause().equals(cause)) {
        }
    }
}

