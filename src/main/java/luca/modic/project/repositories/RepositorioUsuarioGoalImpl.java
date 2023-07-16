package luca.modic.project.repositories;

import luca.modic.project.models.UsuarioGoal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

    @Override
    public List<UsuarioGoal> getAllTasks() {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UsuarioGoal> cr = cb.createQuery(UsuarioGoal.class);
        Root<UsuarioGoal> root = cr.from(UsuarioGoal.class);
        cr.select(root);
        cr.select(root).where(cb.equal(root.get("goal").get("type"), 0));

        return session.createQuery(cr).getResultList();
    }

    @Override
    public List<UsuarioGoal> getAllHabits() {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UsuarioGoal> cr = cb.createQuery(UsuarioGoal.class);
        Root<UsuarioGoal> root = cr.from(UsuarioGoal.class);
        cr.select(root);
        cr.select(root).where(cb.equal(root.get("goal").get("type"), 2));

        return session.createQuery(cr).getResultList();
    }


    @Override
    public void modificar(UsuarioGoal usuarioGoal) {
        sessionFactory.getCurrentSession().update(usuarioGoal);
    }

    @Override
    public List<UsuarioGoal> getAll(Long id) {
        return (List<UsuarioGoal>) this.sessionFactory.getCurrentSession()
                .createCriteria(UsuarioGoal.class)
                .add(Restrictions.eq("usuario.id", id)).list();

    }

    @Override
    public UsuarioGoal buscar(Long id) {
        return (UsuarioGoal) this.sessionFactory.getCurrentSession().createCriteria(UsuarioGoal.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public UsuarioGoal buscarUsuarioGoalPorGoal(Long goalId) {
        return (UsuarioGoal) this.sessionFactory.getCurrentSession()
                .createCriteria(UsuarioGoal.class)
                .add(Restrictions.eq("goal.id", goalId)).uniqueResult();
    }
}
