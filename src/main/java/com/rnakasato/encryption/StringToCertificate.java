package com.rnakasato.encryption;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Calendar;

import javax.security.auth.x500.X500Principal;

import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;

public class StringToCertificate {

	public static void main(String[] args) {

		try {
			String BEGIN_CERT = "-----BEGIN CERTIFICATE-----";
			String END_CERT = "-----END CERTIFICATE-----";

			String certificado = "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";
			// certificado = new
			// String(DatatypeConverter.parseBase64Binary(certificado));

			CertificateFactory cf;

			if (!certificado.contains(BEGIN_CERT)) {
				StringBuilder sb = new StringBuilder();
				sb.append(BEGIN_CERT);
				sb.append("\n");
				sb.append(certificado);
				sb.append("\n");
				sb.append(END_CERT);
				certificado = sb.toString();
			}

			cf = CertificateFactory.getInstance("X.509");
			InputStream stream = new ByteArrayInputStream(certificado.getBytes());
			Certificate cert = cf.generateCertificate(stream);
			X509Certificate certificate = (X509Certificate) cert;
			stream.close();

			String emissor = certificate.getIssuerX500Principal().getName();

			Calendar notBefore = Calendar.getInstance();
			notBefore.setTime(certificate.getNotBefore());

			Calendar notAfter = Calendar.getInstance();
			notAfter.setTime(certificate.getNotAfter());

			String numeroSerie = String.valueOf(certificate.getSerialNumber());

			System.out.println("Emissor: " + emissor);
			System.out.println("Ini vigencia: " + notBefore);
			System.out.println("fim vigencia: " + notAfter);
			System.out.println("Serie: " + numeroSerie);

			X500Name x500name = new X500Name(certificate.getSubjectX500Principal().getName(X500Principal.RFC1779));
			RDN cn = x500name.getRDNs(BCStyle.CN)[0];
			String cnpj = IETFUtils.valueToString(cn.getFirst().getValue());
			cnpj = cnpj.substring(cnpj.indexOf(":") + 1, cnpj.length());
			System.out.println(cnpj);

		} catch (CertificateException | IOException e) {
			e.printStackTrace();
		}

	}

}
