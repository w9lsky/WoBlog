package org.fxc.woblog.controller;

import org.fxc.woblog.Constants;
import org.fxc.woblog.domain.*;
import org.fxc.woblog.services.CommentService;
import org.fxc.woblog.services.PostService;
import org.fxc.woblog.services.PostTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private CommentService commentService;

    /**
     * @param modelAndView
     * @param catId        菜单ID，在菜单功能未添加前适用分类目录ID代替
     * @param tagId        标签ID
     * @param postId       文章ID
     * @param pageId       页面ID
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView website(ModelAndView modelAndView,
                                @RequestParam(value = "cat", required = false) String catId,
                                @RequestParam(value = "tag", required = false) String tagId,
                                @RequestParam(value = "post", required = false) String postId,
                                @RequestParam(value = "page", required = false) String pageId,
                                @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Long pageIndex,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        // the value is from database or cache.
        boolean hasBefore = false;

        // singlShow page
        if (postId != null || pageId != null) {
            Post post = postService.findPost(Long.parseLong(postId == null ? pageId : postId));
            if (post == null) {
                modelAndView.setViewName("notFound");
                return modelAndView;
            }
            Page<Comment> commentPage = commentService.listComment(Long.parseLong(postId == null ? pageId : postId), 0, 5);
            modelAndView.addObject("CommentPage", commentPage);

            Post nextPost = postService.findNext(post);
            modelAndView.addObject("NextPost", nextPost);

            Post previousPost = postService.findPrevious(post);
            modelAndView.addObject("PreviousPost", previousPost);

            modelAndView.addObject("Post", post);
            modelAndView.setViewName("singlShow");
            return modelAndView;
        }

        // multiShow page
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
            Page<PostTerm> postTermPage = postTermService.listPostByTermId(searchId, pageIndex.intValue() == 0 ? 0 : pageIndex.intValue() - 1, pageSize);
            if (postTermPage.getTotalElements() == 0) {
                modelAndView.setViewName("notFound");
            } else {
                if (catId == null) {
                    modelAndView.addObject("TermName", ((PostTerm) (postTermPage.getContent().get(0))).getTerm().getName());
                }
                modelAndView.addObject("RequestUrl", url);

                // from PostTerm page to Post page
                List<Post> postList = new ArrayList<Post>();
                for (PostTerm postTerm : postTermPage.getContent()) {
                    postList.add(postTerm.getPost());
                }

                modelAndView.addObject("PostPage", new PageImpl<Post>(postList, null, postTermPage.getTotalElements()));
                modelAndView.setViewName("multiShow");
            }
            return modelAndView;
        }


        // index page
        if (hasBefore) {
            modelAndView.setViewName("index");
        } else {
            Page<Post> postPage = postService.listPost(pageIndex.intValue() == 0 ? 0 : pageIndex.intValue() - 1, pageSize);
            if (postPage.getTotalElements() == 0) {
                modelAndView.setViewName("notFound");
            } else {
                modelAndView.addObject("PostPage", postPage);
                modelAndView.setViewName("multiShow");
            }
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "ajaxListPost")
    public JsonResult listTerm(ComplexJsonResult<Post> jsonResult) {
        jsonResult.setSuccessList(postService.listPost(Constants.DEFAULT_PAGE_INDEX, Constants.DEFAULT_PAGE_SIZE).getContent());
        return jsonResult;
    }
}
