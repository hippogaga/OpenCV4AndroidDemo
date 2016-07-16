package com.topleex.opencv4androiddemo;

/**
 * Created by topleex on 16/7/13.
 */

public class OpenCVHelper {
    static {
        System.loadLibrary("OpenCV");
    }

    public static native int[] gray(int[] buf, int w, int h);
    public static native int[] binary(int[] buf, int w, int h);
    public static native int[] gauss(int[] buf, int w, int h);
    public static native int[] median(int[] buf, int w, int h);
    public static native int[] average(int[] buf, int w, int h);

    public static native int[] erode(int[] buf, int w, int h);
    public static native int[] dilation(int[] buf, int w, int h);
    public static native int[] open(int[] buf, int w, int h);
    public static native int[] close(int[] buf, int w, int h);

    public static native int[] sobel(int[] buf, int w, int h);

    public static native int[] histo(int[] buf, int w, int h);
}
