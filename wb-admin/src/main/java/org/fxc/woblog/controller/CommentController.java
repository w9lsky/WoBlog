package org.fxc.woblog.controller;

import org.fxc.woblog.domain.Comment;
import org.fxc.woblog.domain.JsonResult;
import org.fxc.woblog.domain.PostComment;
import org.fxc.woblog.services.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * <p>CommentController.</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 11/2/12
 * Since: 0.1
 */
@Controller
public class CommentController {

    @Autowired
    private PostCommentService postCommentService;

    @RequestMapping(value = "addComment", method = RequestMethod.POST)
    public JsonResult addComment(ModelAndView modelAndView,
                                 @RequestParam String postId,
                                 @RequestBody Comment comment) {
        PostComment postComment = new PostComment(Long.parseLong(postId),comment);
        PostComment pc = postCommentService.save(postComment);

        return new JsonResult(true,"Wait for admin validate.");
    }
}
