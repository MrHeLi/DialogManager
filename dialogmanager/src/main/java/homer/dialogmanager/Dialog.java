package homer.dialogmanager;

import android.content.Context;

import androidx.annotation.CallSuper;

/**
 * 对话框管理期调用接口，需要配合DialogAbc和DialogManager使用
 *
 * @author homer
 * @since 8/11/21
 */
public interface Dialog {
    /**
     * 获取优先级 0 ～ 正无穷，0表示高优先级
     *
     * @return
     */
    int getPriority();

    /**
     * 是否满足弹出条件，比如是否处于某个TAB。
     *
     * @return 任意条件皆可弹出，返回true，否则根据条件返回
     */
    boolean isCanShow();

    /**
     * 获取当前弹窗状态
     *
     * @return
     */
    boolean getShowState();

    /**
     * 显示弹窗， 具体子类应该调用自己的显示方法，并且调用super函数
     *
     * @param context
     */
    @CallSuper
    void show(Context context);

    /**
     * 取消弹窗，具体子类应该调用自己的dismiss方法取消显示，并调用对应的super函数
     */
    @CallSuper
    void dismiss();

    void setOnDismissListener(DialogManager.OnDismissListener listener);
}
