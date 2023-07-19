package luca.modic.project.repositories;

import luca.modic.project.models.Usuario;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {
	
	Usuario buscarUsuario(String email, String password);

	Usuario buscarUsuario(String email);
	void guardar(Usuario usuario);
	void modificar(Usuario usuario);

	Usuario buscarUsuarioPorUsername(String usuario);

	Usuario buscar(Long idUsuario);

    void eliminar(Usuario usuario);
}
