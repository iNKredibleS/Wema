// Generated code from Butter Knife. Do not modify!
package com.fb.inkredibles.myapplication;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131165224;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.btGrid = Utils.findRequiredViewAsType(source, R.id.btGrid, "field 'btGrid'", Button.class);
    view = Utils.findRequiredView(source, R.id.btnCreate, "field 'button' and method 'launchCreate'");
    target.button = Utils.castView(view, R.id.btnCreate, "field 'button'", Button.class);
    view2131165224 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.launchCreate();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btGrid = null;
    target.button = null;

    view2131165224.setOnClickListener(null);
    view2131165224 = null;
  }
}
