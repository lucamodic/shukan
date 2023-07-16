package luca.modic.project.services;

import luca.modic.project.exceptions.CampoVacioException;
import luca.modic.project.exceptions.DurationLessThanSeven;
import luca.modic.project.exceptions.DurationMoreThanSeven;
import luca.modic.project.models.Goal;
import luca.modic.project.models.Usuario;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface ServicioGoal {

    void guardarTask(Goal goal, Usuario usuario) throws CampoVacioException, DurationLessThanSeven;
    void taskTime();

    List<Goal> getGoalsById(Long id);

    void borrar(Long id);

    void completar(Long id);

    void guardarMission(Goal goal, Usuario usuario) throws CampoVacioException, DurationMoreThanSeven;

    void guardarHabit(Goal goal, Usuario usuario) throws CampoVacioException;

    @Scheduled(cron = "0 * * * * *")
    void habitTime();

    void habitCheck(Long id);

    Goal buscar(Long id);
}
