package org.hawkular.client.android.util;

import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ViewAnimator;

import butterknife.ButterKnife;

public final class ViewDirector
{
	private final Activity activity;
	private final Fragment fragment;

	private final int animatorId;

	public static ViewDirector of(@NonNull Activity activity, @IdRes int animatorId) {
		return new ViewDirector(activity, null, animatorId);
	}

	public static ViewDirector of(@NonNull Fragment fragment, @IdRes int animatorId) {
		return new ViewDirector(null, fragment, animatorId);
	}

	private ViewDirector(Activity activity, Fragment fragment, int animatorId) {
		this.activity = activity;
		this.fragment = fragment;

		this.animatorId = animatorId;
	}

	public void show(@IdRes int viewId) {
		ViewAnimator animator = findView(animatorId);
		View view = findView(viewId);

		if (animator.getDisplayedChild() != animator.indexOfChild(view)) {
			animator.setDisplayedChild(animator.indexOfChild(view));
		}
	}

	private <T extends View> T findView(int viewId) {
		if (activity != null) {
			return ButterKnife.findById(activity, viewId);
		} else {
			return ButterKnife.findById(fragment.getView(), viewId);
		}
	}
}