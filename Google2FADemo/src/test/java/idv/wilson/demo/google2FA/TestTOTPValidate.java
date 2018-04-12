package idv.wilson.demo.google2FA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TestTOTPValidate {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		String FilePath = "C:\\Users\\Wilson\\AppData\\Local\\Temp\\TOTPObj2607850213668270222.obj";
		ObjectInputStream o = new ObjectInputStream(new FileInputStream(FilePath));

		TOTPObject object = (TOTPObject) o.readObject();
		String PIN = "464841";

		System.out.println(object.validate(PIN));

	}

}
