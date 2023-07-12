package luca.modic.project.services;

import luca.modic.project.exceptions.*;
import luca.modic.project.models.Usuario;

// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioLogin {

	Usuario consultarUsuario(String email, String password);

    void guardarCliente(Usuario datosUsuario) throws UsuarioExistenteException, CampoVacioException, ContraseniaCorta, FormatoDeEmailIncorrecto, UsuarioConUsernameExistente, UsuarioConEmailExistente;
}
