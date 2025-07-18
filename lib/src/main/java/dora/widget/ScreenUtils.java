package dora.widget;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.Field;

public final class ScreenUtils {

    private static ScreenUtils sInstance;

    private ScreenUtils() {
    }

    private static ScreenUtils getInstance() {
        if (sInstance == null) {
            synchronized (ScreenUtils.class) {
                if (sInstance == null) {
                    sInstance = new ScreenUtils();
                }
            }
        }
        return sInstance;
    }

    public static int getScreenWidth(Context context) {
        return getInstance().getScreenSize(context)[0];
    }

    public static int getScreenHeight(Context context) {
        return getInstance().getScreenSize(context)[1];
    }
    public static int getContentWidth(Context context) {
        return getInstance().getScreenSize(context, true)[0];
    }
    public static int getContentHeight(Context context) {
        return getInstance().getScreenSize(context, true)[1];
    }

    private int[] getScreenSize(Context context) {
        return getScreenSize(context, false);
    }

    private int[] getScreenSize(Context context, boolean useContentSize) {

        int[] size = new int[2];

        WindowManager w = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
// since SDK_INT = 1;
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;

        if (!useContentSize) {
            size[0] = widthPixels;
            size[1] = heightPixels - getStatusBarHeight(context);

            return size;
        }

// includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
            try {
                widthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                heightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (Exception ignored) {
            }
// includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 17)
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                widthPixels = realSize.x;
                heightPixels = realSize.y;
            } catch (Exception ignored) {
            }
        size[0] = widthPixels;
        size[1] = heightPixels;
        return size;
    }

    private int getStatusBarHeight(Context context) {
        Class<?> clazz;
        Object obj;
        Field field;
        int x, statusBarHeight = 0;
        try {
            clazz = Class.forName("com.android.internal.R$dimen");
            obj = clazz.newInstance();
            field = clazz.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }
}