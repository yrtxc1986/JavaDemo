package idv.wilson.demo.printer;

import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.Attribute;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.ColorSupported;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.MediaTray;
import javax.print.attribute.standard.PageRanges;
import javax.print.attribute.standard.PrintQuality;
import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import javax.print.attribute.standard.Sides;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PrinterDemo {

	public void listPrinter() {

		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
		System.out.println("Number of print services: " + printServices.length);

		for (PrintService printer : printServices)
			System.out.println("Printer: " + printer.getName());

	}

	public void printerCheck(String printerName) {

		PrintService serv = findPrinterByName(printerName);

		System.out.println(serv.toString());

		printFunctionality(serv, "Trays", MediaTray.class);
		printFunctionality(serv, "Copies", Copies.class);
		printFunctionality(serv, "Print Quality", PrintQuality.class);
		printFunctionality(serv, "Color", ColorSupported.class);
		printFunctionality(serv, "Media Size", MediaSize.class);
		printFunctionality(serv, "Accepting Jobs", PrinterIsAcceptingJobs.class);
		printFunctionality(serv, "Chromaticity", Chromaticity.class);
	}

	private static void printFunctionality(PrintService serv, String attrName, Class<? extends Attribute> attr) {
		boolean isSupported = serv.isAttributeCategorySupported(attr);
		System.out.println("    " + attrName + ": " + (isSupported ? "Y" : "N"));
	}

	public PrintService findPrinterByName(String printerName) {

		// 查詢並設定印表機
		// 獲得本臺電腦連線的所有印表機
		PrintService[] printServices = PrinterJob.lookupPrintServices();
		if (printServices == null || printServices.length == 0) {
			log.error("列印失敗，未找到可用印表機，請檢查。");
			throw new RuntimeException("Cannot find any printer");
		}
		// 匹配指定印表機
		for (int i = 0; i < printServices.length; i++) {
//			System.out.println(printServices[i].getName());
			if (printServices[i].getName().equals(printerName)) {
				return printServices[i];
			}
		}
		log.error("列印失敗，未找到名稱為" + printerName + "的印表機，請檢查。");
		throw new RuntimeException("Cannot find printer with name:" + printerName);
	}

	public void printText() {
		PrintService printer = findPrinterByName("PDF Printer");

		DocFlavor flavor = DocFlavor.CHAR_ARRAY.TEXT_PLAIN;

		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		aset.add(new Copies(1)); // 份數
		aset.add(new JobName("TextPrint", Locale.US));
//		aset.add(Sides.DUPLEX);// 單雙面

		Doc doc = new SimpleDoc("Hello World!!".toCharArray(), flavor, null);
		DocPrintJob job = printer.createPrintJob(); // 建立列印作業
		try {
			job.print(doc, aset);
		} catch (PrintException e) {
			throw new RuntimeException("Printing Failed.", e);
		}

	}

	public void printImage() {
		File file = new File(
				"/Users/wilsonwong/eclipse-workspace/JavaDemo/JavaPrintPDF/src/main/resources/asa5505.jpg");
		String printerName = "PDF Printer";

		try {
			// 这是要打印文件的格式，如果是PDF文档要设为自动识别
			DocFlavor fileFormat = DocFlavor.INPUT_STREAM.JPEG;
			// 2.得到要打印的文档类DOC
			FileInputStream fiStream = new FileInputStream(file);
			Doc myDoc = new SimpleDoc(fiStream, fileFormat, null);
			// 3.生成一个打印属性设置对象
			PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
			aset.add(new Copies(1));// Copies-打印份数1份
			aset.add(Chromaticity.COLOR);
//			aset.add(Sides.DUPLEX);// 双面打印
			PrintService services = findPrinterByName(printerName);
			DocPrintJob job = services.createPrintJob();
			job.print(myDoc, aset);
		} catch (Exception e) {
			throw new RuntimeException("printing failed", e);
		}
	}

	public void printPDF(File file, String printerName) {
		// not suggest use this function for print PDF. 
		try {
			// 这是要打印文件的格式，如果是PDF文档要设为自动识别
			DocFlavor fileFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
			// 2.得到要打印的文档类DOC
			FileInputStream fiStream = new FileInputStream(file);
			Doc myDoc = new SimpleDoc(fiStream, fileFormat, null);
			// 3.生成一个打印属性设置对象
			PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
			aset.add(new Copies(1));// Copies-打印份数1份
			aset.add(new JobName("PDF Print", Locale.US));
			aset.add(MediaSizeName.ISO_A4);
			aset.add(Chromaticity.MONOCHROME);
			aset.add(new PageRanges(2, 3)); 
			aset.add(Sides.DUPLEX);// 双面打印
			PrintService services = findPrinterByName(printerName);
			DocPrintJob job = services.createPrintJob();
			job.print(myDoc, aset);
		} catch (Exception e) {
			throw new RuntimeException("printing failed", e);
		}
	}

	public void printPDFwithPDFBox(File file, String printerName) {
		try {
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintService(findPrinterByName(printerName));
			PDDocument document = PDDocument.load(file);

			job.setPageable(new PDFPageable(document));
			PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
			attr.add(new PageRanges(2, 3)); 
			attr.add(Sides.DUPLEX);// 双面打印
			attr.add(Chromaticity.MONOCHROME);   //Request Printer Driver Support, Testing on Windows C3370 Driver without problem, but not vaild on Mac Driver
			job.print(attr);
		} catch (Exception e) {
			throw new RuntimeException("printing failed", e);
		}
	}
}
