/**
 * StringToTaxonomyConverter
 *
 * @author http://fuxinci.com
 * Date: Aug 20, 2012
 */
package org.fxc.woblog.converter;

import org.fxc.woblog.domain.enmu.Taxonomy;
import org.springframework.core.convert.converter.Converter;

public class StringToTaxonomyConverter implements Converter<String, Taxonomy> {

    public Taxonomy convert(String source) {
        return Enum.valueOf(Taxonomy.class, source.trim().toUpperCase());
    }
}