package luca.modic.project.repositories;

import luca.modic.project.models.UsuarioGoal;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioUsuarioGoal")
public class RepositorioUsuarioGoalImpl implements RepositorioUsuarioGoal{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioUsuarioGoalImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(UsuarioGoal usuarioGoal) {
        sessionFactory.getCurrentSession().save(usuarioGoal);
    }
}
