package idv.wilson.demo.dms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "SMARTSHARE_DOCUMENT")
public class Document {

	@Id
	@Column(name = "ID")
	int id;

	@Column(name = "DOC_NAME")
	String name;
	@Column(name = "DOC_TYPE")
	String type;

}
