package org.fxc.controller;

/**
 * AdminController
 *
 * @author 傅心�?
 * Date: May 29, 2012
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class IndexController {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView)
    {
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
