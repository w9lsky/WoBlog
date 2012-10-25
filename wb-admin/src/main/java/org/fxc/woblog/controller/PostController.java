/**   
 * PostController
 *  
 * @author http://fuxinci.com
 * Date: May 21, 2012
 */
package org.fxc.woblog.controller;

import javax.validation.Valid;

import com.sun.org.apache.bcel.internal.classfile.Constant;
import org.apache.log4j.Logger;
import org.fxc.woblog.Constants;
import org.fxc.woblog.domain.ComplexJsonResult;
import org.fxc.woblog.domain.JsonResult;
import org.fxc.woblog.domain.Post;
import org.fxc.woblog.services.PostService;
import org.fxc.woblog.util.SimpleError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = AdminBaseCotroller.ADMIN_URL)
public class PostController extends AdminBaseCotroller
{
    @Autowired
    private PostService postService;

    private static final String POST_PAGE = "PostPage";

    private static final Logger logger = Logger.getLogger(TermController.class);

    @RequestMapping(value = "post", method = RequestMethod.GET)
    public ModelAndView unspecified(ModelAndView modelAndView) {
        return listPost(modelAndView);
    }

    @RequestMapping(value = "listPost", method = RequestMethod.GET)
    public ModelAndView listPost(ModelAndView modelAndView)
    {
        Page<Post> postPage = postService.listPost(Constants.DEFAULT_PAGE_SIZE, Constants.DEFAULT_PAGE_INDEX);
        
        modelAndView.addObject(POST_PAGE,postPage);
        modelAndView.setViewName(Constants.VIEW_LIST_POST);
        return super.forward(modelAndView);
    }

    @RequestMapping(value = "savePost", method = RequestMethod.GET)
    public ModelAndView savePost(ModelAndView modelAndView) {
        if (modelAndView.getModel().isEmpty()) {
            modelAndView.addObject(new Post());
        }
        if (modelAndView.getView() == null) {
            modelAndView.setViewName(Constants.VIEW_EDIT_POST);
        }
        return super.forward(modelAndView);
    }

    @RequestMapping(value = "savePost", method = RequestMethod.POST)
    public ModelAndView savePost(@Valid Post post,
                                   BindingResult bindingResult,
                                   ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                logger.error(error.getDefaultMessage());
            }
            modelAndView.addObject(post);
            modelAndView.setViewName(Constants.VIEW_EDIT_POST);
            return super.forward(modelAndView);
        }

        try {
            postService.save(post);
            modelAndView.setViewName(Constants.VIEW_EDIT_POST);
            return editPost(modelAndView, post.getId().toString());
        } catch (Exception e) {
            bindingResult.addError(new SimpleError(new String[]{"Add post error"}));
            modelAndView.addObject(post);
            modelAndView.setViewName(Constants.VIEW_EDIT_POST);
            return super.forward(modelAndView);
        }
    }

    @RequestMapping(value = "editPost")
    public ModelAndView editPost(ModelAndView modelAndView,@RequestParam String postId)
    {
        if (modelAndView.getView() == null) {
            modelAndView.setViewName(Constants.VIEW_EDIT_POST);
        }
        modelAndView.addObject(postService.findPost(Long.parseLong(postId)));
        
        return savePost(modelAndView);
    }

    //TODO deletePost method, the transaction is missing.
    @RequestMapping(value = "deletePost", method = RequestMethod.POST)
    public JsonResult deletePost(ModelAndView modelAndView, @RequestBody List<String> idList)
    {
        ComplexJsonResult jsonResult = new ComplexJsonResult();
        
        postService.deletePost(idList);
        modelAndView.setViewName(Constants.VIEW_LIST_POST);
        
        jsonResult.setSuccessList(idList);
        jsonResult.setSuccess(true);
        jsonResult.setMessage(getLocalMessage("delete.post.success"));
        return jsonResult;
    }
}
