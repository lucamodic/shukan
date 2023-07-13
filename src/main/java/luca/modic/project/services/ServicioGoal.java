package luca.modic.project.services;

import luca.modic.project.exceptions.CampoVacioException;
import luca.modic.project.exceptions.DurationLessThanSeven;
import luca.modic.project.models.Goal;
import luca.modic.project.models.Usuario;

public interface ServicioGoal {

    void guardarTask(Goal goal, Usuario usuario) throws CampoVacioException, DurationLessThanSeven;

}
