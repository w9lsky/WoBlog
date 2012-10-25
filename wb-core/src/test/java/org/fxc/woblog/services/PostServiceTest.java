package org.fxc.woblog.services;

import junit.framework.Assert;
import org.fxc.woblog.domain.Post;
import org.fxc.woblog.domain.PostTerm;
import org.fxc.woblog.domain.Term;
import org.fxc.woblog.domain.enmu.Taxonomy;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.List;
import java.util.Random;

/**
 * <p>The description about this file.</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 9/21/12
 * Since: 0.1
 */

@ContextConfiguration("/dispatcher-servlet.xml")
public class PostServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private PostService postService;

    @Test
    public void testSave(){
        Random random = new Random();
        int flag = random.nextInt();

        Post post = new Post();
        post.setTitle("title_" + flag);
        post.setContent("content_" + flag);

        PostTerm postTerm = new PostTerm();
        postTerm.setTermId(301L);
        post.addPostTerm(postTerm);

        Post acutal = postService.save(post);

        Assert.assertNotNull(acutal.getId());

        System.out.println(acutal.getId() + "\t" + acutal.getTitle() + "\t" + post.getContent());
    }

    @Test
    public void testListPost(){
        Page<Post> postPage = postService.listPost(10, 0);

        Assert.assertTrue(postPage.getSize() != 0);

        for(Post post : postPage.getContent()){
            System.out.println(post.getId() + "\t" + post.getTitle() + "\t" + post.getContent());
//            post.setPostTerms(null);
            post.getPostTerms().clear();
            Long expectedId = post.getId();

            Post acutal = postService.save(post);
            Assert.assertNotNull(acutal);
            Assert.assertEquals(expectedId,acutal.getId());

        }
    }
}
