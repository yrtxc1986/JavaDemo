package idv.wilson.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import idv.wilson.demo.admin_portal.entity.PARADM_COMPANY;
import idv.wilson.demo.admin_portal.repository.CompanyRepository;
import idv.wilson.demo.config.SQLConfig;
import idv.wilson.demo.config.company_datasource.DataSourceContextHolder;
import idv.wilson.demo.dms.entity.Document;
import idv.wilson.demo.dms.repository.DocumentRepository;

@SpringBootApplication
public class DynamicDataSourceApplication {

	@Autowired
	CompanyRepository repository;
	@Autowired
	SQLConfig sql;
	@Autowired
	AbstractRoutingDataSource datasource;
	@Autowired
	DocumentRepository documentR;

	private void setCompanyDS() {
		Map<Object, DataSource> currentdatasource = datasource.getResolvedDataSources();

		Map<Object, Object> targetDataSource = new HashMap<Object, Object>();

		for (Entry<Object, DataSource> entity : currentdatasource.entrySet()) {
			targetDataSource.put(entity.getKey(), entity.getValue());
		}

		Iterable<PARADM_COMPANY> allCompany = repository.findAll();
		for (PARADM_COMPANY company : allCompany) {
			BasicDataSource ds = new BasicDataSource();

			ds.setUrl(company.getDatabaseUrl());
			ds.setUsername(sql.getUsername());
			ds.setPassword(sql.getPassword());

			targetDataSource.put(company.getId() + "", ds);
		}

		datasource.setTargetDataSources(targetDataSource);
	}

	private void printDSKey() {

		Map<Object, DataSource> currentdatasource = datasource.getResolvedDataSources();
		for (Entry<Object, DataSource> entity : currentdatasource.entrySet()) {
			System.out.println(entity.getKey());
		}

	}

	private void printeDocumentName() {
		Iterable<Document> docs = documentR.findAll();
		for (Document doc : docs) {
			System.out.println(doc.getName());
		}
	}

	private void printerCompanyName() {
		Iterable<PARADM_COMPANY> allCompany = repository.findAll();
		for (PARADM_COMPANY company : allCompany) {
			System.out.println(company.getCompanyName());
		}
	}

	public void run() {
		printerCompanyName();

		setCompanyDS();
		printDSKey();

		System.out.println("Print Company 10014 Document");
		DataSourceContextHolder.setDataSource("10014");
		printeDocumentName();
		

		System.out.println("Print Company 10008 Document");
		DataSourceContextHolder.setDataSource("10008");
		printeDocumentName();

		try {
			printerCompanyName();
		} catch (InvalidDataAccessResourceUsageException e) {
			System.out.println("----------------------Know Exception due to db not switch");
		}

		DataSourceContextHolder.setMaster();
		printerCompanyName();

	}

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(DynamicDataSourceApplication.class, args);

		ctx.getBean(DynamicDataSourceApplication.class).run();

	}

}
