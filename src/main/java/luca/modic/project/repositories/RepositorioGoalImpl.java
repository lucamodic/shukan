package luca.modic.project.repositories;

import luca.modic.project.models.Goal;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioGoal")
public class RepositorioGoalImpl implements RepositorioGoal {


    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioGoalImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void guardarGoal(Goal goal) {
        sessionFactory.getCurrentSession().save(goal);
    }
}
