package idv.wilson.demo.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Context {

	@Builder
	public Context(Long id, String fileName) {
		this.id = id;
		this.fileName = fileName;
	}

	@Id
	@GeneratedValue
	Long id;
	String fileName;

	@Enumerated(EnumType.STRING)
	Type type;

	public enum Type {
		Master, Output, Reference
	}
}
