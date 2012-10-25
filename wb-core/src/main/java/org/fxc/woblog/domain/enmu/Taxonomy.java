/**
 * Taxonomy
 *
 * @author fuxinci.com
 * Date: May 21, 2012
 */
package org.fxc.woblog.domain.enmu;

public enum Taxonomy {
    CATEGORY, TAG;

    public static Taxonomy from(int key) {
        switch (key) {
            case 0:
                return Taxonomy.CATEGORY;
            case 1:
                return Taxonomy.TAG;

        }
        throw new RuntimeException("No Found!");
    }
}
