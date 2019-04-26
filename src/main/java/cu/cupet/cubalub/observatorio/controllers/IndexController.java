package cu.cupet.cubalub.observatorio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;

/**
 * Creado a las 18:36 del día 9/02/17.
 *
 * @author Eduardo Noel Núñez <enoel.corebsd@gmail.com>
 */
@Controller(value = "indexController")
public class IndexController implements Serializable {

    @RequestMapping(method = RequestMethod.GET, value = "/inicio")
    public ModelAndView index() throws Exception {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");

        return mav;
    }
}
