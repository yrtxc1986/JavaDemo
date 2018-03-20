package idv.wilson.demo.azureBlob;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import idv.wilson.demo.azureBlob.config.ApplicationConfig;

@Component
public class AppMain {

	@Autowired
	private Logger log;
	@Autowired
	FileRepository fileRepository;

	public void run() {
		try {
			File sourceFile = File.createTempFile("DemoUpload", ".txt");
			Writer output = new BufferedWriter(new FileWriter(sourceFile));
			output.write("Hello Azure!");
			output.close();

			fileRepository.save("paradm", "1234", new FileInputStream(sourceFile), sourceFile.length());

			File downloadFile = File.createTempFile("DemoDownload", ".txt");
			FileOutputStream outputStream = new FileOutputStream(downloadFile);
			fileRepository.get("paradm", "1234", outputStream);
			outputStream.close();
			
			
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}

	}

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		AppMain main = context.getBean(AppMain.class);
		main.run();
	}
}
