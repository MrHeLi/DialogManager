package homer.dialogmanager;

import android.content.Context;

/**
 * DialogAbs
 *
 * @author homer
 * @since 8/18/21
 */
abstract public class DialogAbs implements Dialog {
    protected int mPriority = 0;
    protected DialogManager.OnDismissListener mListener;
    protected boolean isShowing = false; // 弹窗是否显示

    public DialogAbs(int priority) {
        mPriority = priority;
    }

    @Override
    public int getPriority() {
        return mPriority;
    }

    @Override
    abstract public boolean isCanShow();

    @Override
    public void show(Context context) {
        isShowing = true;
    }

    @Override
    public void dismiss() {
        if (mListener != null) {
            mListener.onDismiss(this);
        }
    }

    @Override
    public void setOnDismissListener(DialogManager.OnDismissListener listener) {
        mListener = listener;
    }

    @Override
    public boolean getShowState() {
        return isShowing;
    }
}
