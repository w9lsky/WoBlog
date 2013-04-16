/**   
 * AdminController
 *  
 * @author http://fuxinci.com
 * Date: May 21, 2012
 */
package org.fxc.woblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = AdminBaseCotroller.ADMIN_URL)
public class AdminController extends AdminBaseCotroller
{
    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView)
    {
        modelAndView.setViewName("admin");
        return forward(modelAndView);
    }
}
