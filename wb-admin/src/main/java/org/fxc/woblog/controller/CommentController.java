package org.fxc.woblog.controller;

import org.fxc.woblog.domain.Comment;
import org.fxc.woblog.domain.JsonResult;
import org.fxc.woblog.domain.enmu.CommentStatus;
import org.fxc.woblog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * <p>CommentController.</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 11/2/12
 * Since: 0.1
 */
@Controller
@RequestMapping(value = AdminBaseCotroller.ADMIN_URL)
public class CommentController  extends AdminBaseCotroller{

    @Autowired
    private CommentService commentService;
    

    @RequestMapping(value = "addComment", method = RequestMethod.POST)
    public JsonResult addComment(@RequestBody Comment comment) {
        Comment pc = commentService.save(comment);

        return new JsonResult(true,"Wait for admin validate.");
    }

    @RequestMapping(value = "editComment", method = RequestMethod.POST)
    public JsonResult editComment(@RequestParam String status,
                                  @RequestBody List<Map<String,Object>> commentList) {
        int count = commentService.edit(commentList, status);
        if (commentList.size() == count) {
            return new JsonResult(true, "Edit Successfully.");
        } else {
            return new JsonResult(false, "Unknown Error.");
        }
    }

    @RequestMapping(value = "listComment", method = RequestMethod.GET)
    public ModelAndView listComment(ModelAndView modelAndView,
                                    @RequestParam(value = "status",required = false) String status,
                                    @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {

        Page<Comment> postCommentPage = commentService.listComment(status == null ? null : CommentStatus.valueOf(status), pageIndex - 1, pageSize);
        modelAndView.addObject("PostCommentPage", postCommentPage);

        Map<Object,Object> countMap = commentService.getCountByStatus();
        modelAndView.addObject("CountMap",countMap);

        modelAndView.addObject("Status",status);
        modelAndView.setViewName("listComment");
        return super.forward(modelAndView);
    }


}
