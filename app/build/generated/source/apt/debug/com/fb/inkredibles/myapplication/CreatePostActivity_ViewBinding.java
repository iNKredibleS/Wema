// Generated code from Butter Knife. Do not modify!
package com.fb.inkredibles.myapplication;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CreatePostActivity_ViewBinding implements Unbinder {
  private CreatePostActivity target;

  private View view2131165350;

  private View view2131165351;

  private View view2131165229;

  private View view2131165228;

  private View view2131165227;

  @UiThread
  public CreatePostActivity_ViewBinding(CreatePostActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CreatePostActivity_ViewBinding(final CreatePostActivity target, View source) {
    this.target = target;

    View view;
    target.et_title = Utils.findRequiredViewAsType(source, R.id.Title, "field 'et_title'", EditText.class);
    target.et_message = Utils.findRequiredViewAsType(source, R.id.et_message, "field 'et_message'", EditText.class);
    view = Utils.findRequiredView(source, R.id.switch_give_rec, "field 'switch_give_rec' and method 'giveRec'");
    target.switch_give_rec = Utils.castView(view, R.id.switch_give_rec, "field 'switch_give_rec'", Switch.class);
    view2131165350 = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.giveRec(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.switch_pub_pri, "field 'switch_pub_pri' and method 'pubPri'");
    target.switch_pub_pri = Utils.castView(view, R.id.switch_pub_pri, "field 'switch_pub_pri'", Switch.class);
    view2131165351 = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.pubPri(p0, p1);
      }
    });
    target.pictureHolder = Utils.findRequiredViewAsType(source, R.id.pictureHolder, "field 'pictureHolder'", ImageView.class);
    target.tvGiveRec = Utils.findRequiredViewAsType(source, R.id.tv_give_rec, "field 'tvGiveRec'", TextView.class);
    target.tvPubPri = Utils.findRequiredViewAsType(source, R.id.tv_pub_pri, "field 'tvPubPri'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_post, "method 'postButtonClicked'");
    view2131165229 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.postButtonClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_gallery, "method 'gallery'");
    view2131165228 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.gallery();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_camera, "method 'camera'");
    view2131165227 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.camera();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CreatePostActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.et_title = null;
    target.et_message = null;
    target.switch_give_rec = null;
    target.switch_pub_pri = null;
    target.pictureHolder = null;
    target.tvGiveRec = null;
    target.tvPubPri = null;

    ((CompoundButton) view2131165350).setOnCheckedChangeListener(null);
    view2131165350 = null;
    ((CompoundButton) view2131165351).setOnCheckedChangeListener(null);
    view2131165351 = null;
    view2131165229.setOnClickListener(null);
    view2131165229 = null;
    view2131165228.setOnClickListener(null);
    view2131165228 = null;
    view2131165227.setOnClickListener(null);
    view2131165227 = null;
  }
}
