package idv.wilson.demo.google2FA;

import java.io.Serializable;

import de.taimos.totp.TOTP;
import de.taimos.totp.TOTPData;

public class TOTPObject implements Serializable {

	private static final long serialVersionUID = 1072215357800657622L;

	private final TOTPData data;

	public static TOTPObject create(String issuer, String user) {
		TOTPData data = TOTPData.create(issuer, user);
		return new TOTPObject(data);
	}

	public byte[] getQRCode() {
		String URI = data.getSerial() + "?secret=" + data.getSecretAsBase32() + "&issuer=" + data.getIssuer();
		return QRCode.getQRImage(URI);
	}

	public String getOTP() {
		return TOTP.getOTP(data.getSecretAsHex());
	}

	public boolean validate(String PIN) {
		return TOTP.validate(data.getSecretAsHex(), PIN);
	}

	private TOTPObject(TOTPData data) {
		this.data = data;
	}

}
