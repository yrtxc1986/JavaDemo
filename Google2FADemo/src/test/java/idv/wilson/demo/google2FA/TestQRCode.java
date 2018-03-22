package idv.wilson.demo.google2FA;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestQRCode {
	public static void main(String[] args) throws IOException {
		String Value = "http://crunchify.com/";
		File myFile = File.createTempFile("QRCode", ".png");
		System.out.println(myFile.getAbsolutePath());

		FileOutputStream stream = new FileOutputStream(myFile);
		try {
			stream.write(QRCode.getQRImage(Value));
		} finally {
			stream.close();
		}

		System.out.println("\n\nYou have successfully created QR Code.");
	}
}
