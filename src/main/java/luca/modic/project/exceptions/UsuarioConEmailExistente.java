package luca.modic.project.exceptions;

public class UsuarioConEmailExistente extends Exception {
    public UsuarioConEmailExistente(String mensaje) {
        super(mensaje);
    }
}
