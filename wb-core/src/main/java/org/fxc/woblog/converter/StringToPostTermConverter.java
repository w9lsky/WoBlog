package org.fxc.woblog.converter;

import org.fxc.woblog.Constants;
import org.fxc.woblog.domain.PostTerm;
import org.fxc.woblog.domain.Term;
import org.fxc.woblog.domain.enmu.Taxonomy;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>The description about this file.</p>
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 10/16/12
 * Since: 0.1
 */
public class StringToPostTermConverter implements Converter<String, PostTerm> {

    public PostTerm convert(String source) {
        PostTerm postTerm  = new PostTerm();
        if (source.startsWith(Constants.NEW_TAG_PREFIX)) {
            postTerm.setTermName(source.substring(Constants.NEW_TAG_PREFIX.length()));
        } else {
            postTerm.setTermId(Long.parseLong(source));
        }
        return postTerm;
    }
}
