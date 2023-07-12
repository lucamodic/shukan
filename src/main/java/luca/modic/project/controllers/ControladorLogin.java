package luca.modic.project.controllers;

import luca.modic.project.exceptions.*;
import luca.modic.project.models.DatosLogin;
import luca.modic.project.models.Usuario;
import luca.modic.project.services.ServicioLogin;
import luca.modic.project.services.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static javax.swing.JOptionPane.showMessageDialog;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorLogin {

	private ServicioLogin servicioLogin;
	private ServicioUsuario servicioUsuario;
	@Autowired
	public ControladorLogin(ServicioLogin servicioLogin, ServicioUsuario servicioUsuario){
		this.servicioLogin = servicioLogin;
		this.servicioUsuario = servicioUsuario;
	}

	@RequestMapping("/login")
	public ModelAndView irALogin(HttpServletRequest request) {
		if (request.getSession().getAttribute("usuario") != null) {
			return new ModelAndView("redirect:/home");
		}
		ModelMap modelo = new ModelMap();
		modelo.put("datosLogin", new DatosLogin());
		return new ModelAndView("login", modelo);
	}

	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLogin, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		// invoca el metodo consultarUsuario del servicio y hace un redirect a la URL
		// /home, esto es, en lugar de enviar a una vista
		// hace una llamada a otro action a traves de la URL correspondiente a esta
		Usuario usuarioBuscado = servicioLogin.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword());
		if (usuarioBuscado != null) {
			request.getSession().setAttribute("id", usuarioBuscado.getId());
			request.getSession().setAttribute("usuario", usuarioBuscado.getUsuario());
			return new ModelAndView("redirect:/home");
		} else {
			// si el usuario no existe agrega un mensaje de error en el modelo.
			model.put("error", "User or password wrong");
			return new ModelAndView("login", model);
		}
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("redirect:/login");
	}

	@RequestMapping("/registrar-usuario")
	@ResponseBody
	public ModelAndView irARegistrar(HttpServletRequest request) {

		ModelMap modelo = new ModelMap();
		modelo.put("usuario", new Usuario());
		return new ModelAndView("registro-usuario", modelo);
	}

	@RequestMapping(path = "/registrarme", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView confirmarRegistro(@ModelAttribute("usuario") Usuario datosUsuario) throws Exception {
		ModelMap model = new ModelMap();
		try {
			this.servicioLogin.guardarCliente(datosUsuario);
		} catch (CampoVacioException | ContraseniaCorta | FormatoDeEmailIncorrecto | UsuarioConUsernameExistente | UsuarioConEmailExistente e) {
			model.put("error", e.getMessage());
			return new ModelAndView("registro-usuario", model);
		}
		model.put("success", "Account created succesfully");
		return new ModelAndView ("redirect:/login");
	}


	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome(HttpServletRequest request) {
		if (request.getSession().getAttribute("usuario") == null)
			return new ModelAndView("redirect:/login");
		Usuario usuario = this.servicioUsuario.buscar((Long) request.getSession().getAttribute("id"));
		ModelMap model = new ModelMap();
		model.put("usuario", usuario);
		return new ModelAndView("home", model);
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/login");
	}
}
