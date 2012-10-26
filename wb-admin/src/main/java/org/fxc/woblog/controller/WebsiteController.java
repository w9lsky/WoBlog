package org.fxc.woblog.controller;

import org.fxc.woblog.Constants;
import org.fxc.woblog.domain.Post;
import org.fxc.woblog.domain.PostTerm;
import org.fxc.woblog.services.PostService;
import org.fxc.woblog.services.PostTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>The description about this file.</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 10/23/12
 * Since: 0.1
 */

@Controller
public class WebsiteController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostTermService postTermService;
    
    /**
     *
     * @param modelAndView
     * @param catId 菜单ID，在菜单功能未添加前适用分类目录ID代替
     * @param tagId 标签ID
     * @param postId 文章ID
     * @param pageId 页面ID
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView website(ModelAndView modelAndView,
                                @RequestParam(value = "cat",required=false) String catId,
                                @RequestParam(value = "tag",required=false) String tagId,
                                @RequestParam(value = "post",required=false) String postId,
                                @RequestParam(value = "page",required=false) String pageId,
                                @RequestParam(value = "pageIndex",required=false,defaultValue = "1")Long pageIndex)
    {
        // the value is from database or cache.
        boolean hasBefore = false;

        if (postId != null||pageId!=null) {
            Post post = postService.findPost(Long.parseLong(postId==null?pageId:postId));
            if (post == null) {
                modelAndView.setViewName("notFound");
            } else {
                modelAndView.addObject("Post", post);
                modelAndView.setViewName("singlShow");
            }
            return modelAndView;
        }

        if (tagId != null || catId != null) {
            Long searchId;
            String url;
            if (tagId != null) {
                searchId = Long.parseLong(tagId);
                url = "tag=" + tagId;
            } else {
                searchId = Long.parseLong(catId);
                url = "cat=" + catId;
            }
            Page<PostTerm> postPage = postTermService.listPostByTermId(searchId, pageIndex.intValue() == 0 ? 0 : pageIndex.intValue() - 1, 5);
            if (postPage.getTotalElements() == 0) {
                modelAndView.setViewName("notFound");
            } else {
                modelAndView.addObject("TermName",((PostTerm)(postPage.getContent().get(0))).getTerm().getName());
                modelAndView.addObject("RequestUrl", url);
                modelAndView.addObject("PostTermPage", postPage);
                modelAndView.setViewName("multiShow");
            }
            return modelAndView;
        }


        if (hasBefore) {
            modelAndView.setViewName("before");
        } else {
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }

}
