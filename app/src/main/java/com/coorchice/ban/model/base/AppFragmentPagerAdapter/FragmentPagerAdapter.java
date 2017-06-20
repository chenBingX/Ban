package com.coorchice.ban.model.base.AppFragmentPagerAdapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Project Name:kratos
 * Author:Chice
 * Date:2016/12/19
 * Notes:
 */

public abstract class FragmentPagerAdapter extends PagerAdapter {
  private static final String TAG = "FragmentPagerAdapter";
  private static final boolean DEBUG = false;

  private final FragmentManager mFragmentManager;
  private FragmentTransaction mCurTransaction = null;
  private Fragment mCurrentPrimaryItem = null;

  public FragmentPagerAdapter(FragmentManager fm) {
    mFragmentManager = fm;
  }

  /**
   * Return the Fragment associated with a specified position.
   */
  public abstract Fragment getItem(int position);

  @Override
  public void startUpdate(ViewGroup container) {
    if (container.getId() == View.NO_ID) {
      throw new IllegalStateException("ViewPager with adapter " + this
        + " requires a view id");
    }
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    if (mCurTransaction == null) {
      mCurTransaction = mFragmentManager.beginTransaction();
    }

    final long itemId = getItemId(position);

    // Do we already have this fragment?
    String name = makeFragmentName(container.getId(), itemId);
    Fragment fragment = mFragmentManager.findFragmentByTag(name);
    if (fragment != null) {
      if (DEBUG) Log.v(TAG, "Attaching item #" + itemId + ": f=" + fragment);
      mCurTransaction.attach(fragment);
    } else {
      fragment = getItem(position);
      if (DEBUG) Log.v(TAG, "Adding item #" + itemId + ": f=" + fragment);
      mCurTransaction.add(container.getId(), fragment,
        makeFragmentName(container.getId(), itemId));
    }
    if (fragment != mCurrentPrimaryItem) {
      FragmentCompat.setMenuVisibility(fragment, false);
      FragmentCompat.setUserVisibleHint(fragment, false);
    }
    return fragment;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    if (mCurTransaction == null) {
      mCurTransaction = mFragmentManager.beginTransaction();
    }
    if (DEBUG) Log.v(TAG, "Detaching item #" + getItemId(position) + ": f=" + object
      + " v=" + ((Fragment)object).getView());
    mCurTransaction.detach((Fragment)object);
  }

  @Override
  public void setPrimaryItem(ViewGroup container, int position, Object object) {
    Fragment fragment = (Fragment)object;
    if (fragment != mCurrentPrimaryItem) {
      if (mCurrentPrimaryItem != null) {
        FragmentCompat.setMenuVisibility(mCurrentPrimaryItem, false);
        FragmentCompat.setUserVisibleHint(mCurrentPrimaryItem, false);
      }
      if (fragment != null) {
        FragmentCompat.setMenuVisibility(fragment, true);
        FragmentCompat.setUserVisibleHint(fragment, true);
      }
      mCurrentPrimaryItem = fragment;
    }
  }

  @Override
  public void finishUpdate(ViewGroup container) {
    if (mCurTransaction != null) {
      mCurTransaction.commitAllowingStateLoss();
      mCurTransaction = null;
      mFragmentManager.executePendingTransactions();
    }
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return ((Fragment)object).getView() == view;
  }

  @Override
  public Parcelable saveState() {
    return null;
  }

  @Override
  public void restoreState(Parcelable state, ClassLoader loader) {
  }

  public long getItemId(int position) {
    return position;
  }

  private static String makeFragmentName(int viewId, long id) {
    return "android:switcher:" + viewId + ":" + id;
  }
}
