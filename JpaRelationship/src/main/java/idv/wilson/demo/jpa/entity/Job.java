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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
public class Job {

	@Id
	@GeneratedValue
	Long id;

	LocalDateTime createDate;

	// @Enumerated(EnumType.ORDINAL) // Mapping Enum Order to DataBase
	@Enumerated(EnumType.STRING) // Mapping Enum Name to DataBases
	Status status; // Enum default mapping with order

	@OneToOne(cascade = { CascadeType.ALL })
	@Where(clause = "type = 'Master'")
	Context master;

	@OneToOne(cascade = { CascadeType.ALL })
	@Where(clause = "type = 'Output'")
	Context output;

	// Lazy fetch must have between transactional
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY) // OneToMany Default Lazy fetch
	@Where(clause = "type = 'Reference'")
	@JoinColumn(name = "Job_Id")
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	List<Context> references = new ArrayList<Context>();

	public Context getReference(int index) {
		return references.get(index);
	}

	public void addReference(Context reference) {
		reference.type = Context.Type.Reference;
		reference.setJob(this);
		references.add(reference);
	}

	public List<Context> getReferences() {
		return Collections.unmodifiableList(references);
	}

	public void setMaster(Context master) {
		this.master = master;
		this.master.setType(Context.Type.Master);
		this.master.setJob(this);
	}

	public void setOutput(Context output) {
		this.output = output;
		this.output.setType(Context.Type.Output);
		this.output.setJob(this);
	}

	public enum Status {
		Waiting, Running, CompletedWithError, Completed
	}

}
