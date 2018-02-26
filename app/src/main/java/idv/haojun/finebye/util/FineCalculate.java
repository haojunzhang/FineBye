package idv.haojun.finebye.util;

import android.util.Log;

public class FineCalculate {
    // way
    public static final int FREE_WAY = 0;
    public static final int HIGH_WAY = 1;
    public static final int NORMAL_WAY = 2;

    // traffic
    public static final int MOTO = 0;
    public static final int CAR = 1;

    // over speed
    public static final int OVER_0_20 = 0;
    public static final int OVER_20_40 = 1;
    public static final int OVER_40_60 = 2;
    public static final int OVER_60_80 = 3;
    public static final int OVER_80_100 = 4;
    public static final int OVER_100_up = 5;

    public static int calculateFine(int way, int traffic, int overSpeed) {
        switch (way) {
            case NORMAL_WAY:
                switch (traffic) {
                    case MOTO:
                        switch (overSpeed) {
                            case OVER_0_20:
                                return 1200;
                            case OVER_20_40:
                                return 1400;
                            case OVER_40_60:
                                return 1600;
                            default:
                                return overSpeed60(overSpeed);
                        }
                    case CAR:
                        switch (overSpeed) {
                            case OVER_0_20:
                                return 1600;
                            case OVER_20_40:
                                return 1800;
                            case OVER_40_60:
                                return 2000;
                            default:
                                return overSpeed60(overSpeed);
                        }
                }
                break;
            default:
                switch (overSpeed) {
                    case OVER_0_20:
                        return 3000;
                    case OVER_20_40:
                        return 3500;
                    case OVER_40_60:
                        return 5000;
                    default:
                        return overSpeed60(overSpeed);
                }
        }
        return 0;
    }

    private static int overSpeed60(int overSpeed) {
        switch (overSpeed) {
            case OVER_60_80:
                return 8000;
            case OVER_80_100:
                return 12000;
            case OVER_100_up:
                return 24000;
        }
        return 0;
    }
}
