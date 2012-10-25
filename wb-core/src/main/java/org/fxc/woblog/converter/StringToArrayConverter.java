/**
 * StringToTaxonomyConverter
 *
 * @author http://fuxinci.com
 * Date: Aug 20, 2012
 */

package org.fxc.woblog.converter;

import org.springframework.core.convert.converter.Converter;

public class StringToArrayConverter  implements Converter<String, String[]> {

    public String[] convert(String source) {
        return source.indexOf(",") != -1 ? source.split(",") : source.split("_");
    }
}