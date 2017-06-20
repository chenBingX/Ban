package com.coorchice.ban.model.base.AppFragmentPagerAdapter;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.support.annotation.RequiresApi;

/**
 * Project Name:kratos
 * Author:Chice
 * Date:2016/12/19
 * Notes:
 */

@RequiresApi(24)
@TargetApi(24)
class FragmentCompatApi24 {
  public static void setUserVisibleHint(Fragment f, boolean isVisible) {
    f.setUserVisibleHint(isVisible);
  }
}
