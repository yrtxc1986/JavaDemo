package idv.wilson.demo.admin_portal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PARADM_COMPANY")
public class PARADM_COMPANY {

	@Id
	@Column(name="ID")
	int id;

	@Column(name="COMPANY_NAME")
	String companyName;

	@Column(name="COMPANY_NAME_ENG")
	String companyNameEng;

	@Column(name="DS_URL")
	String databaseUrl;
	
}
