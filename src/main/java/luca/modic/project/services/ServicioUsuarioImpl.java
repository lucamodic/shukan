package luca.modic.project.services;

import luca.modic.project.models.Goal;
import luca.modic.project.models.Usuario;
import luca.modic.project.repositories.RepositorioGoal;
import luca.modic.project.repositories.RepositorioUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

@Service("servicioUsuario")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario{

    private ServletContext servletContext;
    private RepositorioUsuario repositorioUsuario;
    private RepositorioGoal repositorioGoal;

    @Autowired
    public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario, RepositorioGoal repositorioGoal, ServletContext servletContext){
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioGoal = repositorioGoal;
        this.servletContext = servletContext;
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

    @Override
    public void cambiarFotoPerfil(MultipartFile fotoPerfil, Long id) throws IOException {
        Usuario usuario = this.repositorioUsuario.buscar(id);
        if (usuario.getFotoPerfil() != null && !usuario.getFotoPerfil().isBlank()) {
            try {
                Files.delete(
                        Paths.get(servletContext.getRealPath("") + "images/fotosPerfil/" + usuario.getFotoPerfil()));
            } catch (NoSuchFileException ex) {
                System.err.println(ex);
            }
        }
        guardarImagen(fotoPerfil);
        usuario.setFotoPerfil(fotoPerfil.getOriginalFilename());
        repositorioUsuario.modificar(usuario);
    }


    private void guardarImagen(MultipartFile imagen) throws IOException {
        try {
            String fileName = imagen.getOriginalFilename();
            String uploadDir = servletContext.getRealPath("") + "images/fotosPerfil";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            InputStream inputStream = imagen.getInputStream();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new IOException("No se pudo guardar el archivo");
        }
    }


}
