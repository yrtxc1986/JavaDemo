package idv.wilson.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="ParaKey")
	@TableGenerator(
		    name="ParaKey",
		    table="SYS_TABLE_KEY",
		    pkColumnName = "key",
		    valueColumnName = "next",
		    //pkColumnValue="course",
		    allocationSize=30
		)
	private long Id;
	
	private String userName;
	
	private String password;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private String name;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//private String[] roles;
	
	
}
