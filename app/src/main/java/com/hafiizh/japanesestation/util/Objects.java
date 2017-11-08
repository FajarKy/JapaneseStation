package com.hafiizh.japanesestation.util;

/**
 * Created by HAFIIZH on 10/24/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class Objects {
    public static boolean equals(Object o1, Object o2) {
        if (o1 == null)
            return o2 == null;
        if (o2 == null)
            return false;
        return o1.equals(o2);
    }
}
