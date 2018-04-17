package idv.wilson.demo.google2FA;

import java.io.Serializable;

import de.taimos.totp.TOTP;
import de.taimos.totp.TOTPData;

public class TOTPObject implements Serializable {

	private static final long serialVersionUID = 1072215357800657622L;

	// private final TOTPData data;

	// private String issuer;
	// private String user;
	private String SecretAsHex;
	// private String SecretAsBase32;
	private String URI;
	// private String Serial;

	public static TOTPObject create(String issuer, String user) {
		TOTPData data = TOTPData.create(issuer, user);
		return new TOTPObject(data);
	}

	public boolean validate(String PIN) {
		return TOTP.validate(SecretAsHex, PIN);
	}

	public String getOTP() {
		return TOTP.getOTP(SecretAsHex);
	}

	public byte[] getQRCode() {
		return QRCode.getQRImage(URI);
	}

	private TOTPObject(TOTPData data) {
		// issuer = data.getIssuer();
		// user = data.getUser();
		SecretAsHex = data.getSecretAsHex();
		// SecretAsBase32 = data.getSecretAsBase32();
		// Serial = data.getSerial();
		URI = data.getUrl();
	}

	public String getSecretAsHex() {
		return SecretAsHex;
	}

}
