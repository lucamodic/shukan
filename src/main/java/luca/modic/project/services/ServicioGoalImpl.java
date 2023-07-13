package luca.modic.project.services;

import luca.modic.project.exceptions.CampoVacioException;
import luca.modic.project.exceptions.DurationLessThanSeven;
import luca.modic.project.models.Goal;
import luca.modic.project.models.TypeOfGoal;
import luca.modic.project.models.Usuario;
import luca.modic.project.models.UsuarioGoal;
import luca.modic.project.repositories.RepositorioGoal;
import luca.modic.project.repositories.RepositorioUsuarioGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service("servicioGoal")
@Transactional
public class ServicioGoalImpl implements ServicioGoal{
    private RepositorioGoal repositorioGoal;
    private RepositorioUsuarioGoal repositorioUsuarioGoal;

    @Autowired
    public ServicioGoalImpl(RepositorioGoal repositorioGoal, RepositorioUsuarioGoal repositorioUsuarioGoal){
        this.repositorioGoal = repositorioGoal;
        this.repositorioUsuarioGoal =  repositorioUsuarioGoal;
    }

    @Override
    public void guardarTask(Goal goal, Usuario usuario) throws CampoVacioException, DurationLessThanSeven {
        if(this.verificarCamposRequeridos(goal)){
            throw new CampoVacioException("All fields must be filled");
        }
        if( goal.getLimitDate() > 7){
            throw new DurationLessThanSeven("The duration of the task must be less or equal to seven days");
        }
        goal.setType(TypeOfGoal.TASK);
        LocalDate finishingDate = LocalDate.now().plusDays(goal.getLimitDate());
        repositorioGoal.guardarGoal(goal);
        repositorioUsuarioGoal.guardar(new UsuarioGoal(goal, usuario, finishingDate));
    }

    private Boolean verificarCamposRequeridos(Goal goal) {
        return goal.getDamage() == null || goal.getDescription().isBlank() || goal.getExperience() == null
               || goal.getName().isBlank() || goal.getLimitDate() == null;
    }
}
