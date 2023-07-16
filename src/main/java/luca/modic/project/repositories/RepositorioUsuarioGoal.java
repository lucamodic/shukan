package luca.modic.project.repositories;

import luca.modic.project.models.UsuarioGoal;

import java.util.List;

public interface RepositorioUsuarioGoal {

    void guardar(UsuarioGoal usuarioGoal);

    List<UsuarioGoal> getAllTasks();

    List<UsuarioGoal> getAllHabits();

    void modificar(UsuarioGoal usuarioGoal);
    List<UsuarioGoal> getAll(Long id);

    UsuarioGoal buscar(Long id);

    UsuarioGoal buscarUsuarioGoalPorGoal(Long goalId);
}
