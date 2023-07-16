package luca.modic.project.repositories;

import luca.modic.project.models.Goal;

public interface RepositorioGoal {

    void guardarGoal(Goal goal);

    void modificar(Goal goal);

    void borrar(Long id);

    Goal buscar(Long id);
}
