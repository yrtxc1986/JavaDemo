package idv.wilson.demo.google2FA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TestTOTPValidate {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		String FilePath = "C:\\Users\\Wilson\\AppData\\Local\\Temp\\TOTPObj7696628425651340956.obj";
		ObjectInputStream o = new ObjectInputStream(new FileInputStream(FilePath));

		TOTPObject object = (TOTPObject) o.readObject();
		String PIN = "715067";

		System.out.println(object.validate(PIN));

	}

}
