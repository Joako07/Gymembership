package gymembership.entities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "clientes")
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name = "nombre")
	private String name;

	@NotEmpty
	@Column(name = "apellido")
	private String surname;

	@NotEmpty
	@Email
	private String email;

	@NotNull
	@Column(name = "fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthdate;

	@NotNull
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	/*Con PrePersist llamo a un metodo para que se ejecute antes de que se guarde el objeto en la base de datos 
	y dentro del metodo guardo la fecha de hoy en el atributo createAt */
	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}

	// @Column(name="imagen")
	// private picture;

	//Ver video 127
	// @Column(name="membresia")
	// private Date membership;

// id único que identifica una clase cuando lo serializamos	
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

}
