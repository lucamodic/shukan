package luca.modic.project.services;

import luca.modic.project.models.Goal;
import luca.modic.project.models.Usuario;
import luca.modic.project.repositories.RepositorioGoal;
import luca.modic.project.repositories.RepositorioUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("servicioUsuario")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario{

    private RepositorioUsuario repositorioUsuario;
    private RepositorioGoal repositorioGoal;

    @Autowired
    public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario, RepositorioGoal repositorioGoal){
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioGoal = repositorioGoal;
    }

    @Override
    public Usuario buscar(Long id) {
        return this.repositorioUsuario.buscar(id);
    }

    @Override
    public void darNivel(Usuario usuario, Long id) {
        Goal goal = this.repositorioGoal.buscar(id);
        usuario.setActualExp(usuario.getActualExp() + goal.getExperience());
        checkExp(usuario);
        this.repositorioUsuario.modificar(usuario);
    }

    private void checkExp(Usuario usuario) {
        if(usuario.getActualExp() >= usuario.getTotalExp()){
            usuario.setActualHealth(100);
            usuario.setLevel(usuario.getLevel() + 1);
            usuario.setActualExp(usuario.getActualExp() - usuario.getTotalExp());
            usuario.setTotalExp(usuario.getTotalExp() * 3 / 2);
        }
    }

    @Override
    public void eliminar(Usuario usuario) {
        this.repositorioUsuario.eliminar(usuario);
    }

}
