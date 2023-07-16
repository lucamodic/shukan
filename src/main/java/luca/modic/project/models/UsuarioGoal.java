package luca.modic.project.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class UsuarioGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Usuario usuario;
    @ManyToOne
    private Goal goal;
    private LocalDate finishingDate;

    public UsuarioGoal(Goal goal, Usuario usuario, LocalDate finishingDate) {
        this.goal = goal;
        this.usuario = usuario;
        this.finishingDate = finishingDate;
    }

    public UsuarioGoal() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public LocalDate getFinishingDate() {
        return finishingDate;
    }

    public void setFinishingDate(LocalDate finishingDate) {
        this.finishingDate = finishingDate;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
