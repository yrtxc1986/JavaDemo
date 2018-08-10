package idv.wilson.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class DMSIndex {
	@Id
	private Integer Id;
}
