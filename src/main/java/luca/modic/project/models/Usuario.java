package luca.modic.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Clase que modela el concepto de Usuario, la anotacion @Entity le avisa a hibernate que esta clase es persistible
// el paquete ar.edu.unlam.tallerweb1.modelo esta indicado en el archivo hibernateCOntext.xml para que hibernate
// busque entities en el
@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String usuario;
	private String email;
	private String password;
	private String fotoPerfil;
	private Integer totalHealth = 100;
	private Integer actualHealth = 100;
	private Integer totalExp = 100;
	private Integer actualExp = 0;
	private Integer Streak = 0;
	private Integer level = 1;

	private Boolean streakToday = false;
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getFotoPerfil() {
		return fotoPerfil;
	}
	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public Integer getTotalHealth() {
		return totalHealth;
	}

	public void setTotalHealth(Integer totalHealth) {
		this.totalHealth = totalHealth;
	}

	public Integer getActualHealth() {
		return actualHealth;
	}

	public void setActualHealth(Integer actualHealth) {
		this.actualHealth = actualHealth;
	}

	public Integer getTotalExp() {
		return totalExp;
	}

	public void setTotalExp(Integer totalExp) {
		this.totalExp = totalExp;
	}

	public Integer getActualExp() {
		return actualExp;
	}

	public void setActualExp(Integer actualExp) {
		this.actualExp = actualExp;
	}

	public Integer getStreak() {
		return Streak;
	}

	public void setStreak(Integer streak) {
		Streak = streak;
	}

	public Boolean getStreakToday() {
		return streakToday;
	}

	public void setStreakToday(Boolean streakToday) {
		this.streakToday = streakToday;
	}
}
