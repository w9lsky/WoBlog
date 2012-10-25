/**   
 * Term
 *  
 * @author fuxinci.com
 * Date: May 21, 2012
 */
package org.fxc.woblog.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.fxc.woblog.Constants;
import org.fxc.woblog.domain.enmu.Taxonomy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="wb_terms")
public class Term extends BaseModel
{

    public Term() {
    }

    public Term(String tagName) {
        this.name = tagName;
        this.slug = tagName;
        this.taxonomy = Taxonomy.TAG;
    }
    
    /**
     * 标签、分类名称
     */
    @NotNull
    @Size(min=1, max=32)
    private String name;
    
    /**
     *一个由英文及数字组成的缩略名
     */
    @NotNull
    @Size(min=1, max=32)
    private String slug;
    
    /**
     * 标签、分类:TAG、CATEGORY
     */
    private Taxonomy taxonomy;
    
    /**
     * 描述信息
     */
    @Size(min=0, max=256)
    private String description;
    
    /**
     * 父标签id
     */
    private int parent;
    
    /**
     * 累计计数器
     */
    private int count;


//    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "termSet", fetch = FetchType.LAZY)
//    private Set<Post> postSet = new HashSet<Post>();
//
//    public void addPost(Post post){
//        postSet.add(post);
//    }
//
//    @JsonIgnore
//    public Set<Post> getPostSet() {
//        return postSet;
//    }
//
//    public void setPostSet(Set<Post> postSet) {
//        this.postSet = postSet;
//    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getSlug()
    {
        return slug;
    }
    
    public void setSlug(String slug)
    {
        this.slug = slug;
    }
    
    public Taxonomy getTaxonomy()
    {
        return taxonomy;
    }

    public void setTaxonomy(Taxonomy taxonomy)
    {
        this.taxonomy = taxonomy;
    }
    
    public int getParent()
    {
        return parent;
    }
    
    public void setParent(int parent)
    {
        this.parent = parent;
    }
    
    public int getCount()
    {
        return count;
    }
    
    public void setCount(int count)
    {
        this.count = count;
    }
}
