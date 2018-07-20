// Generated code from Butter Knife. Do not modify!
package com.fb.inkredibles.myapplication;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SignupActivity_ViewBinding implements Unbinder {
  private SignupActivity target;

  private View view2131165230;

  @UiThread
  public SignupActivity_ViewBinding(SignupActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SignupActivity_ViewBinding(final SignupActivity target, View source) {
    this.target = target;

    View view;
    target.et_email = Utils.findRequiredViewAsType(source, R.id.et_email, "field 'et_email'", EditText.class);
    target.et_username = Utils.findRequiredViewAsType(source, R.id.et_username, "field 'et_username'", EditText.class);
    target.et_password = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'et_password'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_signup, "method 'signupbtn'");
    view2131165230 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.signupbtn();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SignupActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.et_email = null;
    target.et_username = null;
    target.et_password = null;

    view2131165230.setOnClickListener(null);
    view2131165230 = null;
  }
}
