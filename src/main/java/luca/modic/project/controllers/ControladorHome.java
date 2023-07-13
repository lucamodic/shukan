package luca.modic.project.controllers;

import luca.modic.project.exceptions.*;
import luca.modic.project.models.Goal;
import luca.modic.project.models.Usuario;
import luca.modic.project.services.ServicioGoal;
import luca.modic.project.services.ServicioLogin;
import luca.modic.project.services.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorHome {


    private ServicioUsuario servicioUsuario;
    private ServicioGoal servicioGoal;

    @Autowired
    public ControladorHome(ServicioUsuario servicioUsuario, ServicioGoal servicioGoal){
        this.servicioUsuario = servicioUsuario;
        this.servicioGoal = servicioGoal;
    }

    @RequestMapping(path = "/save-task", method = RequestMethod.POST)
    public ModelAndView saveTask(@ModelAttribute("goal") Goal goal, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Usuario usuario = this.servicioUsuario.buscar((Long) request.getSession().getAttribute("id"));
        try{
            this.servicioGoal.guardarTask(goal, usuario);
        } catch(CampoVacioException | DurationLessThanSeven e){
            model.put("error", e.getMessage());
        }


        model.put("usuario", usuario);
        model.put ("goal", new Goal());
        return new ModelAndView("home", model);
    }
}
