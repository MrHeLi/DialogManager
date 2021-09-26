package homer.dialogmanager;

import android.text.TextUtils;
import android.util.Log;

/**
 * 日志工具类
 *
 * @author homer
 * @since 9/26/21
 */
public class LogUtil {
    private static final String TAG = "DialogManager";

    private static boolean isDebug() {
        return DialogManager.getInstance().isDebug();
    }

    public static void i(String msg) {
        i(null, msg);
    }

    public static void w(String msg) {
        i(null, msg);
    }

    public static void e(String msg) {
        i(null, msg);
    }

    public static void i(String tag, String msg) {
        if (!isDebug()) {
            return;
        }

        if (TextUtils.isEmpty(msg)) {
            msg = "";
        }
        if (TextUtils.isEmpty(tag)) {
            Log.i(TAG, msg + "");
        }
    }

    public static void w(String tag, String msg) {
        if (!isDebug()) {
            return;
        }

        if (TextUtils.isEmpty(msg)) {
            msg = "";
        }
        if (TextUtils.isEmpty(tag)) {
            Log.w(TAG, msg + "");
        }
    }

    public static void e(String tag, String msg) {
        if (!isDebug()) {
            return;
        }

        if (TextUtils.isEmpty(msg)) {
            msg = "";
        }
        if (TextUtils.isEmpty(tag)) {
            Log.e(TAG, msg + "");
        }
    }
}
