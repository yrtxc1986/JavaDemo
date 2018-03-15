package idv.wilson.demo.google2FA;

import de.taimos.totp.TOTP;
import de.taimos.totp.TOTPData;

public class Main {

	public static void main(String[] args) {

		TOTPData data = TOTPData.create("ParaDM", "Wilson.Wong");

		System.out.println(data.getSecretAsHex());
		System.out.println(data.getSerial()+"?secret="+data.getSecretAsBase32()+"&issuer="+data.getIssuer());
		
		String Key = "116860C4E446C6008AEA73549EDE143CDDAD9755";
		System.out.println(TOTP.validate(Key, "999999"));

		System.out.println(TOTP.getOTP(Key));

	}



}
