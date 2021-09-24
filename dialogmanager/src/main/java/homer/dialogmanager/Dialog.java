package homer.dialogmanager;

import android.content.Context;

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

    void show(Context context);

    void dismiss();

    void setOnDismissListener(DialogManager.OnDismissListener listener);
}
