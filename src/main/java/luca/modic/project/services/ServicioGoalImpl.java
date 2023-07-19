package luca.modic.project.services;

import luca.modic.project.exceptions.CampoVacioException;
import luca.modic.project.exceptions.DurationLessThanSeven;
import luca.modic.project.exceptions.DurationMoreThanSeven;
import luca.modic.project.models.Goal;
import luca.modic.project.models.TypeOfGoal;
import luca.modic.project.models.Usuario;
import luca.modic.project.models.UsuarioGoal;
import luca.modic.project.repositories.RepositorioGoal;
import luca.modic.project.repositories.RepositorioUsuario;
import luca.modic.project.repositories.RepositorioUsuarioGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service("servicioGoal")
@Transactional
@EnableScheduling
public class ServicioGoalImpl implements ServicioGoal{
    private RepositorioGoal repositorioGoal;
    private RepositorioUsuario repositorioUsuario;
    private RepositorioUsuarioGoal repositorioUsuarioGoal;

    @Autowired
    public ServicioGoalImpl(RepositorioGoal repositorioGoal, RepositorioUsuarioGoal repositorioUsuarioGoal, RepositorioUsuario repositorioUsuario){
        this.repositorioGoal = repositorioGoal;
        this.repositorioUsuarioGoal =  repositorioUsuarioGoal;
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public void guardarTask(Goal goal, Usuario usuario) throws CampoVacioException, DurationLessThanSeven {
        if(this.verificarCamposRequeridos(goal)){
            throw new CampoVacioException("All fields must be filled");
        }
        if( goal.getLimitDate() > 7 || goal.getLimitDate() <= 0){
            throw new DurationLessThanSeven("The duration of the task must be less or equal to seven days but not less than 1");
        }
        goal.setType(TypeOfGoal.TASK);
        LocalDate finishingDate = LocalDate.now().plusDays(goal.getLimitDate());
        taskTime();
        repositorioGoal.guardarGoal(goal);
        repositorioUsuarioGoal.guardar(new UsuarioGoal(goal, usuario, finishingDate));
    }

    private Boolean verificarCamposRequeridos(Goal goal) {
        return goal.getDamage() == null || goal.getDescription().isBlank() || goal.getExperience() == null
               || goal.getName().isBlank() || goal.getLimitDate() == null;
    }

    private Boolean verificarCamposRequeridosHabits(Goal goal) {
        return goal.getDamage() == null || goal.getDescription().isBlank() || goal.getExperience() == null
                || goal.getName().isBlank();
    }

    @Override
    @Scheduled (cron = "0 * * * * *")
    public void taskTime(){
        List<UsuarioGoal> list = repositorioUsuarioGoal.getAllTasks();
        LocalDate now = LocalDate.now();
        for(UsuarioGoal usuarioGoal : list){
            if(now.isAfter(usuarioGoal.getFinishingDate()) && !usuarioGoal.getGoal().getHecho() && !usuarioGoal.getGoal().getActivado()){
                Usuario usuario = usuarioGoal.getUsuario();
                usuarioGoal.getGoal().setActivado(true);
                usuario.setActualHealth(usuario.getActualHealth() - usuarioGoal.getGoal().getDamage());
                repositorioGoal.modificar(usuarioGoal.getGoal());
                repositorioUsuario.modificar(usuario);
            }
        }
    }

    @Override
    public List<Goal> getGoalsById(Long id) {
        List<UsuarioGoal> usuarioGoals = repositorioUsuarioGoal.getAll(id);
        List<Goal> goals = new ArrayList<>();;
        for(UsuarioGoal usuarioGoal : usuarioGoals){
            goals.add(usuarioGoal.getGoal());
        }
        return goals;
    }

    @Override
    public void borrar(Long id) {
        this.repositorioGoal.borrar(id);
    }

    @Override
    public void completar(Long id) {
        Goal goal = this.repositorioGoal.buscar(id);
        goal.setHecho(true);
        this.repositorioGoal.modificar(goal);
    }

    @Override
    public void guardarMission(Goal goal, Usuario usuario) throws CampoVacioException, DurationMoreThanSeven {
        if(this.verificarCamposRequeridos(goal)){
            throw new CampoVacioException("All fields must be filled");
        }
        if( goal.getLimitDate() <= 7){
            throw new DurationMoreThanSeven("The duration of the task must be more than 7 days");
        }
        goal.setType(TypeOfGoal.MISSION);
        LocalDate finishingDate = LocalDate.now().plusDays(goal.getLimitDate());
        taskTime();
        repositorioGoal.guardarGoal(goal);
        repositorioUsuarioGoal.guardar(new UsuarioGoal(goal, usuario, finishingDate));
    }

    @Override
    public void guardarHabit(Goal goal, Usuario usuario) throws CampoVacioException {
        if(this.verificarCamposRequeridosHabits(goal)){
            throw new CampoVacioException("All fields must be filled");
        }
        goal.setType(TypeOfGoal.HABIT);
        LocalDate finishingDate = LocalDate.now().plusDays(1);
        habitTime();
        repositorioGoal.guardarGoal(goal);
        repositorioUsuarioGoal.guardar(new UsuarioGoal(goal, usuario, finishingDate));

    }

    @Override
    @Scheduled (cron = "0 * * * * *")
    public void habitTime(){
        List<UsuarioGoal> list = repositorioUsuarioGoal.getAllHabits();
        LocalDate now = LocalDate.now();
        for(UsuarioGoal usuarioGoal : list){
            if(now.isAfter(usuarioGoal.getFinishingDate()) && !usuarioGoal.getGoal().getHecho()){
                Usuario usuario = usuarioGoal.getUsuario();
                usuario.setActualHealth(usuario.getActualHealth() - usuarioGoal.getGoal().getDamage());
                usuario.setStreak(0);
                usuarioGoal.setFinishingDate(LocalDate.now().plusDays(1));
                repositorioUsuarioGoal.modificar(usuarioGoal);
                repositorioUsuario.modificar(usuario);
            }
            if(now.isAfter(usuarioGoal.getFinishingDate()) && usuarioGoal.getGoal().getHecho()){
                Goal goal = usuarioGoal.getGoal();
                goal.setHecho(false);
                usuarioGoal.setFinishingDate(LocalDate.now().plusDays(1));
                repositorioGoal.modificar(goal);
                repositorioUsuarioGoal.modificar(usuarioGoal);
            }
        }
    }


    //VA CUANDO LE DA A FINALIZAR EL HABIT
    @Override
    public void habitCheck(Long id) {
        Goal goal = this.repositorioGoal.buscar(id);
        UsuarioGoal usuarioGoal = this.repositorioUsuarioGoal.buscarUsuarioGoalPorGoal(id);
        Usuario usuario = usuarioGoal.getUsuario();
        if(countHabits(usuario.getId()) && !usuario.getStreakToday()){
            usuario.setStreakToday(true);
            usuario.setStreak(usuario.getStreak() + 1);
            this.repositorioUsuario.modificar(usuario);
        };

    }

    @Override
    public Goal buscar(Long id) {
        return this.repositorioGoal.buscar(id);
    }

    private Boolean countHabits(Long id) {
        Integer count = 0;
        Integer habits = 0;
        //TODOS HABITS
        List<UsuarioGoal> list = repositorioUsuarioGoal.getAllHabits();
        for(UsuarioGoal usuarioGoal : list){
            if(usuarioGoal.getUsuario().getId().equals(id)){
                count ++;
                if(usuarioGoal.getGoal().getHecho()){
                    habits ++;
                }
            }
        }
        return count.equals(habits);
    }

}
