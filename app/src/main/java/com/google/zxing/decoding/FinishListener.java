package com.google.zxing.decoding;

import android.app.Activity;
import android.content.DialogInterface;

/**
 * @author peaut
 * @package com.google.zxing.decoding
 * @fileName FinishListener
 * @date on  2019/3/21  16:41
 */
public final class FinishListener
        implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener, Runnable {

    private final Activity activityToFinish;

    public FinishListener(Activity activityToFinish) {
        this.activityToFinish = activityToFinish;
    }

    public void onCancel(DialogInterface dialogInterface) {
        run();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        run();
    }

    public void run() {
        activityToFinish.finish();
    }

}