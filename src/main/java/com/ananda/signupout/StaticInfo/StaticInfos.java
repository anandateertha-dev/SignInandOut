package com.ananda.signupout.StaticInfo;

import java.util.Random;

public class StaticInfos {
    public static boolean loginStatus = false;

    public static int generateRandom6DigitNumber() {
        Random random = new Random();

        int min = 100000;
        int max = 999999;
        return random.nextInt(max - min + 1) + min;
    }
}
