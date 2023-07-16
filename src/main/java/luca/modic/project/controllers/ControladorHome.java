package luca.modic.project.controllers;

import luca.modic.project.exceptions.*;
import luca.modic.project.models.Goal;
import luca.modic.project.models.TypeOfGoal;
import luca.modic.project.models.Usuario;
import luca.modic.project.services.ServicioGoal;
import luca.modic.project.services.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        List<Goal> goals = this.servicioGoal.getGoalsById(usuario.getId());
        model.put("goals", goals);
        model.put("usuario", usuario);
        model.put ("goal", new Goal());
        try{
            this.servicioGoal.guardarTask(goal, usuario);
        } catch(CampoVacioException | DurationLessThanSeven e){
            model.put("errorTask", e.getMessage());
            return new ModelAndView("home", model);
        }
        return new ModelAndView("redirect:/home", model);
    }

    @RequestMapping(path = "/save-mission", method = RequestMethod.POST)
    public ModelAndView saveMission(@ModelAttribute("goal") Goal goal, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Usuario usuario = this.servicioUsuario.buscar((Long) request.getSession().getAttribute("id"));
        List<Goal> goals = this.servicioGoal.getGoalsById(usuario.getId());
        model.put("goals", goals);
        model.put("usuario", usuario);
        model.put ("goal", new Goal());
        try{
            this.servicioGoal.guardarMission(goal, usuario);
        } catch(CampoVacioException | DurationMoreThanSeven e){
            model.put("errorMission", e.getMessage());
            return new ModelAndView("home", model);
        }
        return new ModelAndView("redirect:/home", model);
    }

    @RequestMapping(path = "/save-habit", method = RequestMethod.POST)
    public ModelAndView saveHabit(@ModelAttribute("goal") Goal goal, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Usuario usuario = this.servicioUsuario.buscar((Long) request.getSession().getAttribute("id"));
        List<Goal> goals = this.servicioGoal.getGoalsById(usuario.getId());
        model.put("goals", goals);
        model.put("usuario", usuario);
        model.put ("goal", new Goal());
        try{
            this.servicioGoal.guardarHabit(goal, usuario);
        } catch(CampoVacioException e){
            model.put("errorHabit", e.getMessage());
            return new ModelAndView("home", model);
        }
        return new ModelAndView("redirect:/home", model);
    }


    @RequestMapping(path = "/eliminar", method = RequestMethod.POST)
    @ResponseBody
    public void eliminar(@RequestParam String id, HttpServletRequest request){
        this.servicioGoal.borrar(Long.parseLong(id));
    }

    @RequestMapping(path = "/finish", method = RequestMethod.POST)
    @ResponseBody
    public void finish(@RequestParam String id, HttpServletRequest request){
        //le ponga true a hecho
        //le de nivel a usuario
        this.servicioGoal.completar(Long.parseLong(id));
        Usuario usuario = this.servicioUsuario.buscar((Long) request.getSession().getAttribute("id"));
        this.servicioUsuario.darNivel(usuario, Long.parseLong(id));
        if(this.servicioGoal.buscar(Long.parseLong(id)).getType() == TypeOfGoal.HABIT){
            this.servicioGoal.habitCheck(Long.parseLong(id));
        }

    }

}
