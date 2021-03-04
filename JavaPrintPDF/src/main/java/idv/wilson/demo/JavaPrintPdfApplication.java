package idv.wilson.demo;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import idv.wilson.demo.printer.PrinterDemo;

@SpringBootApplication
public class JavaPrintPdfApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(JavaPrintPdfApplication.class, args);
		PrinterDemo printer = ctx.getBean(PrinterDemo.class);

		printer.listPrinter();
		System.out.println("-*---------------------");
//
//		printer.printerCheck("Bullzip PDF Printer");
		
//		IPP Printer : FUJI XEROX ApeosPort-VII C3372 (30:c9:ab)
//	    Trays: N
//	    Copies: Y
//	    Print Quality: N
//	    Color: N
//	    Media Size: N
//	    Accepting Jobs: N
//	    Chromaticity: Y

//		printer.printText();
//		printer.printImage();
//		printer.printPDF(
//				new File("/Users/wilsonwong/eclipse-workspace/JavaDemo/JavaPrintPDF/src/main/resources/sample.pdf"),
//				"FUJI XEROX ApeosPort-VII C3372 (30:c9:ab)");
//		printer.printPDF(
//		new File("/Users/wilsonwong/eclipse-workspace/JavaDemo/JavaPrintPDF/src/main/resources/SmartShare Enterprise.pdf"),
//		"Bullzip PDF Printer");

		printer.printPDFwithPDFBox(
				new File("/Users/wilsonwong/eclipse-workspace/JavaDemo/JavaPrintPDF/src/main/resources/SmartShare Enterprise.pdf"),
				"FUJI XEROX ApeosPort-VII C3372 (30:c9:ab)");
	}

}
