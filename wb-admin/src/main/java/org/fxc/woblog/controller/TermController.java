/**
 * TermController
 *
 * @author http://fuxinci.com
 * Date: July 16, 2012
 */
package org.fxc.woblog.controller;


import org.apache.log4j.Logger;
import org.fxc.woblog.Constants;
import org.fxc.woblog.domain.ComplexJsonResult;
import org.fxc.woblog.domain.JsonResult;
import org.fxc.woblog.services.TermService;
import org.fxc.woblog.domain.Term;
import org.fxc.woblog.util.SimpleError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = AdminBaseCotroller.ADMIN_URL)
public class TermController extends AdminBaseCotroller {

    private final String TERM_LIST = "TermList";
    private final String SUCCESS = "term";
    private final String TAXONOMY = "taxonomy";

    private static final Logger logger = Logger.getLogger(TermController.class);

    @Autowired
    private TermService termService;

    @RequestMapping(value = "term", method = RequestMethod.GET)
    public ModelAndView unspecified(ModelAndView modelAndView, @RequestParam String taxonomy) {
        if (!modelAndView.getModel().containsKey("term")) {
            modelAndView.addObject(new Term());
        }
        return listTerm(modelAndView, taxonomy, Constants.DEFAULT_PAGE_SIZE, Constants.DEFAULT_PAGE_INDEX);
    }

    @ResponseBody
    @RequestMapping(value = "ajaxListTerm")
    public JsonResult listTerm(@RequestParam String taxonomy, ComplexJsonResult<Term> jsonResult) {
        jsonResult.setSuccessList(termService.findTerm(taxonomy));
        return jsonResult;
    }

    @RequestMapping(value = "listTerm")
    public ModelAndView listTerm(ModelAndView modelAndView, @RequestParam String taxonomy,
                                 @RequestParam(value = "pageSize", defaultValue = Constants.PAGE_SIZE) int pageSize,
                                 @RequestParam(value = "pageIndex", defaultValue = Constants.PAGE_INDEX) int pageIndex) {
        try {
            Page<Term> terms = termService.findTerm(taxonomy, pageSize, pageIndex);
            modelAndView.addObject(TERM_LIST, terms);
            if (modelAndView.getViewName() == null) {
                modelAndView.setViewName(SUCCESS);
            }

            return forward(modelAndView, taxonomy);
        } catch (Exception e) {
            logger.error("List term error:" + taxonomy, e);
            modelAndView.setViewName(Constants.VIEW_NOT_FOUND);
            return forward(modelAndView, taxonomy);
        }
    }

    @RequestMapping(value = "addTerm")
    public ModelAndView addTerm(ModelAndView modelAndView, @RequestParam String taxonomy, @Valid Term term, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                logger.error(error.getDefaultMessage());
            }

            modelAndView.addObject(term);
            return unspecified(modelAndView, taxonomy);
        }

        try {
            termService.save(term);

            modelAndView.setView(new RedirectView(SUCCESS));
            modelAndView.addObject(TAXONOMY, taxonomy);
            return modelAndView;

        } catch (Exception e) {
            modelAndView.addObject(term);
            bindingResult.addError(new SimpleError(new String[]{"add." + taxonomy + ".error"}));
            return unspecified(modelAndView, taxonomy);
        }
    }

    @RequestMapping(value = "deleteTerm")
    public JsonResult deleteTerm(ModelAndView modelAndView, @RequestParam String taxonomy,
                                     @RequestBody List<String> idList) {
        ComplexJsonResult jsonResult = new ComplexJsonResult();
        if (idList == null) {
            jsonResult.setMessage(getLocalMessage("delete." + taxonomy + ".fail", "null"));
            return jsonResult;
        }

        // delete term by id. if fail, save the id
        //TODO the property errorIds is invalid now.
        StringBuilder errorIds = new StringBuilder();
        for (int i = idList.size() - 1; i > -1; i--) {
            try {
                termService.delete(Integer.parseInt(idList.get(i)));
            } catch (Exception e) {
                if (errorIds.length() > 0) {
                    errorIds.append(",");
                }
                errorIds.append(idList.get(i));
                idList.remove(i);
            }
        }

        jsonResult.setSuccessList(idList);
        jsonResult.setSuccess(errorIds.length() == 0);

        if (errorIds.length() == 0) {
            jsonResult.setMessage(getLocalMessage("delete." + taxonomy + ".success"));
        } else {
            if (errorIds.length() == 1 && Integer.parseInt(errorIds.toString()) == Constants.INVALID_ID) {
                jsonResult.setMessage(getLocalMessage("delete.default.category"));
            } else {
                jsonResult.setMessage(getLocalMessage("delete." + taxonomy + ".fail", errorIds.toString()));
            }
        }
        return jsonResult;
    }


    protected ModelAndView forward(ModelAndView modelAndView, String taxonomy) {
        modelAndView.addObject(TAXONOMY, taxonomy);
        return super.forward(modelAndView);
    }
}
