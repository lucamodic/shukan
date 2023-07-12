package luca.modic.project.services;

import luca.modic.project.exceptions.*;
import luca.modic.project.models.Usuario;
import luca.modic.project.repositories.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

// Implelemtacion del Servicio de usuarios, la anotacion @Service indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.servicios
// para encontrar esta clase.
// La anotacion @Transactional indica que se debe iniciar una transaccion de base de datos ante la invocacion de cada metodo del servicio,
// dicha transaccion esta asociada al transaction manager definido en el archivo spring-servlet.xml y el mismo asociado al session factory definido
// en hibernateCOntext.xml. De esta manera todos los metodos de cualquier dao invocados dentro de un servicio se ejecutan en la misma transaccion
@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

	private RepositorioUsuario servicioLoginDao;

	@Autowired
	public ServicioLoginImpl(RepositorioUsuario servicioLoginDao){
		this.servicioLoginDao = servicioLoginDao;
	}

	@Override
	public Usuario consultarUsuario (String email, String password) {
		return servicioLoginDao.buscarUsuario(email, password);
	}

	@Override
	public void guardarCliente(Usuario usuarioNuevo)
			throws CampoVacioException, ContraseniaCorta, FormatoDeEmailIncorrecto, UsuarioConUsernameExistente, UsuarioConEmailExistente {
		checkearDatos(usuarioNuevo);
		this.servicioLoginDao.guardar(usuarioNuevo);
	}

	private void checkearDatos(Usuario usuarioNuevo)
			throws FormatoDeEmailIncorrecto, ContraseniaCorta, CampoVacioException, UsuarioConUsernameExistente, UsuarioConEmailExistente {

		if (!this.verificarCaposRequeridos(usuarioNuevo)) {
			throw new CampoVacioException("All fields must be filled");
		}
		if (usuarioNuevo.getPassword().length() < 8) {
			throw new ContraseniaCorta("Password must be at least 8 characters");
		}
		if (!this.validarEmail(usuarioNuevo.getEmail())) {
			throw new FormatoDeEmailIncorrecto("Email format is incorrect");
		}
		Usuario resultado = this.servicioLoginDao.buscarUsuarioPorUsername(usuarioNuevo.getUsuario());
		if(resultado != null){
			throw new UsuarioConUsernameExistente("Username already chosen");
		}
		resultado = this.servicioLoginDao.buscarUsuario(usuarioNuevo.getEmail());
		if(resultado != null){
			throw new UsuarioConEmailExistente("Email already chosen");
		}

	}


	private Boolean verificarCaposRequeridos(Usuario usuario) {
		return usuario.getEmail().isBlank() || usuario.getUsuario().isBlank() || usuario.getPassword().isBlank() ? false
				: true;
	}

	private Boolean validarEmail(String email) {

		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

		Pattern pattern = Pattern.compile(emailRegex);
		return pattern.matcher(email).matches() ? true : false;
	}

}
