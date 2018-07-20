// Generated code from Butter Knife. Do not modify!
package com.fb.inkredibles.myapplication;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131165225;

  private View view2131165226;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.usernameInput = Utils.findRequiredViewAsType(source, R.id.etUsername, "field 'usernameInput'", EditText.class);
    target.passwordInput = Utils.findRequiredViewAsType(source, R.id.etPassword, "field 'passwordInput'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btnLogIn, "field 'btnLogIn' and method 'login'");
    target.btnLogIn = Utils.castView(view, R.id.btnLogIn, "field 'btnLogIn'", Button.class);
    view2131165225 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.login();
      }
    });
    view = Utils.findRequiredView(source, R.id.btnSignUp, "field 'btnSignUp' and method 'launchSignUp'");
    target.btnSignUp = Utils.castView(view, R.id.btnSignUp, "field 'btnSignUp'", Button.class);
    view2131165226 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.launchSignUp();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.usernameInput = null;
    target.passwordInput = null;
    target.btnLogIn = null;
    target.btnSignUp = null;

    view2131165225.setOnClickListener(null);
    view2131165225 = null;
    view2131165226.setOnClickListener(null);
    view2131165226 = null;
  }
}
