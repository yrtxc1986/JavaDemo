package idv.wilson.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import idv.wilson.demo.entity.DMSDocument;
import idv.wilson.demo.entity.DMSIndex;
import idv.wilson.demo.repository.DMSDocumentRepository;
import idv.wilson.demo.repository.DMSIndexRepository;

@Controller
public class HomeController {

	@GetMapping
	public String HomePage() {
		return "index";
	}

	@Autowired
	DMSDocumentRepository DMSDoc;

	@GetMapping("/doc")
	public String CreateDOC() {
		DMSDocument doc = null;

		for (int i = 0; i < 10; i++) {
			doc = new DMSDocument();
			doc.setId(i);
			DMSDoc.save(doc);
		}

		return "";
	}

	@Autowired
	DMSIndexRepository DMSIndex;

	@GetMapping("/index")
	public String CreateINDEX() {
		DMSIndex doc = null;

		for (int i = 0; i < 10; i++) {
			doc = new DMSIndex();
			doc.setId(i);
			DMSIndex.save(doc);
		}

		return "";
	}

	@GetMapping("/listindex")
	public List<idv.wilson.demo.entity.DMSIndex> listINDEX() {
		return DMSIndex.findAll();
	}
}
