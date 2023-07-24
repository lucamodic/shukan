package luca.modic.project.services;

import luca.modic.project.models.Usuario;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ServicioUsuario {

    Usuario buscar(Long idUsuario);

    void darNivel(Usuario usuario, Long id);

    void eliminar(Usuario usuario);

    void cambiarFotoPerfil(MultipartFile fotoPerfil, Long id) throws IOException;
}
