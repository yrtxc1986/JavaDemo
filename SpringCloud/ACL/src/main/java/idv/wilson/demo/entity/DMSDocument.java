package idv.wilson.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class DMSDocument {

	@Id
	private Integer Id;
}
