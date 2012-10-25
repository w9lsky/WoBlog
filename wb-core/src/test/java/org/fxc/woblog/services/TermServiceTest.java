package org.fxc.woblog.services;

import org.fxc.woblog.domain.Post;
import org.fxc.woblog.domain.Term;
import org.fxc.woblog.domain.enmu.Taxonomy;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.List;
import java.util.Random;

/**
 * <p>The description about this file.</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 9/19/12
 * Since: 0.1
 */

@ContextConfiguration("/dispatcher-servlet.xml")
public class TermServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private TermService termService;
    
    private static final String taxonomy = "CATEGORY";
    private static Term data = null;
        
    @Test
    public void testSave(){
    	Random random = new Random();
    	int flag = random.nextInt(9999);
    	Term term = new Term();
    	term.setName("name_"+flag);
    	term.setSlug("slug_"+flag);
    	term.setTaxonomy(Taxonomy.CATEGORY);

//        Post post = new Post();
//        post.setTitle("title_" + flag);
//        post.setContent("content_" + flag);
//        term.addPost(post);
        
    	data = termService.save(term);
    	Assert.assertNotNull(data.getId());
    	
    	System.out.println(data.getId() + "\t" + data.getName() + "\t" + data.getParent());
    }
    
    
    @Test
    public void testFindTerm() {        
//    	Assert.assertNotNull(data);
    	
        List<Term> termList = termService.findTerm(Taxonomy.CATEGORY.toString());
        
        Assert.assertNotNull(termList);
        
        for (Term term : termList) {
            System.out.println(term.getId() + "\t" + term.getName() + "\t" + term.getParent());
        }
    }
}
