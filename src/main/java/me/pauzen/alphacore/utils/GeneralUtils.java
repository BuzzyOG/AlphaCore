/*
 *  Created by Filip P. on 2/7/15 1:02 PM.
 */

package me.pauzen.alphacore.utils;

import java.io.File;
import java.util.Collection;

public class GeneralUtils {

    public static String toFileName(String... path) {
        StringBuilder pathBuilder = new StringBuilder();

        for (String location : path) {
            pathBuilder.append(File.separatorChar);
            pathBuilder.append(location);
        }

        return pathBuilder.toString();
    }

    public static <T> boolean toggleContainment(Collection<T> collection, T object) {
        if (collection.contains(object)) {
            collection.remove(object);
            return false;
        }
        else {
            collection.add(object);
            return true;
        }
    }

    public static <T> boolean setContainment(Collection<T> collection, T object, boolean containment) {
        if (containment) {
            if (!collection.contains(object)) {
                collection.add(object);
            }
        }
        else {
            collection.remove(object);
        }
        return containment;
    }

}
