package luca.modic.project.services;

import luca.modic.project.models.Usuario;

public interface ServicioUsuario {

    Usuario buscar(Long idUsuario);

    void darNivel(Usuario usuario, Long id);
}
