package idv.wilson.demo.google2FA;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestTOTPObject {
	public static void main(String[] args) throws IOException {
		String issuer = "ParaDM";
		String user = "Wilson.Wong";

		TOTPObject object = TOTPObject.create(issuer, user);

		String PIN = object.getOTP();
		System.out.println(PIN);
		System.out.println(object.validate(PIN));

		byte[] QRCode = object.getQRCode();

		File tempFile = File.createTempFile("OTPQR", ".png");
		FileOutputStream stream = new FileOutputStream(tempFile);
		try {
			stream.write(QRCode);
		} finally {
			stream.close();
		}
		System.out.println(tempFile.getAbsolutePath());
	}
}
