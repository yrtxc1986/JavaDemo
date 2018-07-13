package idv.wilson.demo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Authorities {

	@Id
	private Long Id;

	@ManyToOne
	private Users User;
	private String Authority;

}
