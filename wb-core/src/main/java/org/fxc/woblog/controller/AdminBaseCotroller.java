/**
 * BaseCotroller
 *
 * @author 傅心词
 * Date: May 15, 2012
 */
package org.fxc.woblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public abstract class AdminBaseCotroller {
    public static final String ADMIN_PATH = "admin/";
    public static final String ADMIN_URL = "wb_admin";

    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;
    
    @Autowired
    private CookieLocaleResolver localeResolver;

    @Autowired
    private HttpServletRequest request;

    public String forward(String page) {
        return ADMIN_PATH + page;
    }

    protected String getLocalMessage(String code, Object... obj) {
        return messageSource.getMessage(code, obj, localeResolver.resolveLocale(request));
    }

    protected final ModelAndView forward(ModelAndView modelAndView) {
        modelAndView.addObject("admin_url", ADMIN_URL);
        modelAndView.setViewName(ADMIN_PATH + modelAndView.getViewName());
        return modelAndView;
    }

}
