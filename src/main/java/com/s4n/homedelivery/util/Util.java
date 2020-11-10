package com.s4n.homedelivery.util;

import java.util.Random;

public class Util {

    public static int generateTime() {
        Random r = new Random();
        int Low = 2;
        int High = 5;
        return r.nextInt(High-Low) + Low;
    }
}
