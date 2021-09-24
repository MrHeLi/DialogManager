package homer.dialogmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * MyDialog
 *
 * @author homer
 * @since 8/18/21
 */
public class MyDialog extends DialogAbs {

    boolean isCanShow = true;

    public MyDialog(int priority) {
        super(priority);
    }

    public void setCanShow(boolean isCanShow) {
        this.isCanShow = isCanShow;
    }

    @Override
    public boolean isCanShow() {
        // 这里实现具体
        return isCanShow;
    }

    @Override
    public boolean getShowState() {
        return false;
    }

    @Override
    public void show(Context context) {
        super.show(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("问题：");
        builder.setMessage("请问你满十八岁了吗?");
        builder.setCancelable(true);
        builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "你点击了是的", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //设置反面按钮
        builder.setNegativeButton("不是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "你点击了不是", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                isShowing = false;
                MyDialog.this.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        isShowing = true;
    }
}
