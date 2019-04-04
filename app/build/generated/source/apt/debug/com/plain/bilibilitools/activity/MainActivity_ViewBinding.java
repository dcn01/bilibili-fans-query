// Generated code from Butter Knife. Do not modify!
package com.plain.bilibilitools.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.plain.bilibilitools.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131165218;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.mEtUserId = Utils.findRequiredViewAsType(source, R.id.et_user_id, "field 'mEtUserId'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_search, "field 'mBtnSearch' and method 'onClick'");
    target.mBtnSearch = Utils.castView(view, R.id.btn_search, "field 'mBtnSearch'", Button.class);
    view2131165218 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mCvResult = Utils.findRequiredViewAsType(source, R.id.cv_result, "field 'mCvResult'", CardView.class);
    target.mTvResult = Utils.findRequiredViewAsType(source, R.id.tv_result, "field 'mTvResult'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mEtUserId = null;
    target.mBtnSearch = null;
    target.mCvResult = null;
    target.mTvResult = null;

    view2131165218.setOnClickListener(null);
    view2131165218 = null;
  }
}
