package util;

import java.util.concurrent.TimeUnit;

public class Sleep {
    public static void sleepSec(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
