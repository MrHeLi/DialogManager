package homer.dialogmanager;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 弹窗管理者，采用单例模式，内部维护一个弹窗队列，使加入了该队列中的弹窗能够按照优先级弹出，且保证只能同时存在一个弹窗。
 * 需要注意的是mToggleSize大小，mDialogs必须达到mToggleSize时才能触发弹窗。在某个页面需要集中出现弹窗时，需要在一开始
 * 就预见可能的弹窗数量，并调用setToggleSize设置触发值，然后批量请求后台，是否触发弹窗，后台数据如果不需要触发某一个弹窗，
 * 则需要调用minusToggleSize,减去该弹窗占用的mToggleSize。
 *
 * @author homer
 * @since 8/11/21
 */
public class DialogManager {
    /* 弹出模式：连续弹出，一个弹完之后下一个立马弹出 */
    public static final int CONTINUE_STATE_SEQUENCE = 0;
    /* 弹出模式：单个弹出，一个弹完之后不立马弹出下一个 */
    public static final int CONTINUE_STATE_SINGLE = 1;

    private List<Dialog> mDialogs;
    // 弹窗弹出的必要条件：mDialogs.size必须达到该大小才能弹出。默认值为0
    private int mToggleSize = 0;

    private int mContinueState = CONTINUE_STATE_SEQUENCE;

    private static class DialogManagerHolder {
        private static final DialogManager INSTANCE = new DialogManager();
    }

    private DialogManager() {
    }

    /**
     * 设置触发大小
     *
     * @param toggleSize
     */
    public void setToggleSize(int toggleSize) {
        mToggleSize = toggleSize;
    }

    /**
     *  触发大小减一
     */
    public void minusToggleSize() {
        mToggleSize--;
        mToggleSize = mToggleSize < 0 ? 0 : mToggleSize;
    }

    private void setPopState(int state) {
        mContinueState = state;
    }

    public static final DialogManager getInstance() {
        return DialogManagerHolder.INSTANCE;
    }

    /**
     * 添加对话框
     *
     * @param dialog 对话框
     */
    public synchronized void addDialog(Dialog dialog, Context context) {
        if (mDialogs == null) {
            mDialogs = new ArrayList<>();
        }
        dialog.setOnDismissListener(dialog1 -> {
            Log.i("Homer", "下一个弹窗");
            mDialogs.remove(dialog1);
            minusToggleSize();
            if (mContinueState == CONTINUE_STATE_SEQUENCE) {
                showNext(0, context);
            }
        });
        mDialogs.add(dialog);
        // 按照优先级从高到低排序
        Collections.sort(mDialogs, (d1, d2) -> d1.getPriority() - d2.getPriority());
    }

    /**
     * 开始显示弹窗
     *
     * @param context
     */
    public void show(Context context) {
        if (mDialogs.size() < mToggleSize) { // 如果dialog没有达到触发数量，将不弹出。
            return;
        }
        showNext(0, context);
    }

    private void showNext(int index, Context context) {
        if (index >= mDialogs.size() || index < 0) {
            Log.i("Homer", "弹窗显示完毕");
            return;
        }

        Dialog dialog = mDialogs.get(index);
        if (dialog.getShowState()) {
            Log.i("Homer", "有正在显示的弹窗");
            return;
        }

        if (dialog.isCanShow()) {
            Log.i("Homer", "当前显示优先级：" + dialog.getPriority());
            dialog.show(context);
        } else {
            showNext(++index, context);
        }
    }

    /**
     * 删除列表中最后一个对话框
     */
    private synchronized void deleteDialog() {
        if (mDialogs == null || mDialogs.isEmpty()) {
            return;
        }
        Dialog dialog = mDialogs.get(mDialogs.size() - 1);
        mDialogs.remove(mDialogs.size() - 1);
    }

    interface OnDismissListener {
        void onDismiss(Dialog dialog);
    }
}
