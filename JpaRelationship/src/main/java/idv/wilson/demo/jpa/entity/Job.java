package idv.wilson.demo.jpa.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Where;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
public class Job {

	@Builder
	public Job(LocalDateTime createDate, Context master) {
		this.createDate = createDate;
		this.master = master;
	}

	@Id
	@GeneratedValue
	Long id;

	LocalDateTime createDate;

	// @Enumerated(EnumType.ORDINAL) // Mapping Enum Order to DataBase
	@Enumerated(EnumType.STRING) // Mapping Enum Name to DataBases
	Status status; // Enum default mapping with order

	@OneToOne(cascade = { CascadeType.ALL })
	Context master;

	@OneToOne(cascade = { CascadeType.ALL })
	Context output;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY) // OneToMany Default Lazy fetch, Lazy fetch must
																		// between transactional
	@Where(clause = "type = 'Reference'")
	@JoinColumn(name = "Context_Id")
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	List<Context> references = new ArrayList<Context>();

	public Context getReference(int index) {
		return references.get(index);
	}

	public void addReference(Context reference) {
		reference.type = Context.Type.Reference;
		references.add(reference);
	}

	public List<Context> getReferences() {
		return Collections.unmodifiableList(references);
	}

	public void setOutput(Context output) {
		this.output = output;
		this.output.setType(Context.Type.Output);
	}

	public enum Status {
		Waiting, Running, CompletedWithError, Completed
	}

	public static class JobBuilder {
		Context master;

		public JobBuilder master(Context master) {
			this.master = master;
			master.setType(Context.Type.Master);
			return this;
		}

	}
}
