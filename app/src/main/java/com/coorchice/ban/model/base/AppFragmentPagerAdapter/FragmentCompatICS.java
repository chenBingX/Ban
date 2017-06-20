package com.coorchice.ban.model.base.AppFragmentPagerAdapter;

/**
 * Project Name:kratos
 * Author:Chice
 * Date:2016/12/19
 * Notes:
 */

import android.annotation.TargetApi;
import android.app.Fragment;
import android.support.annotation.RequiresApi;

@RequiresApi(14)
@TargetApi(14)
class FragmentCompatICS {
  public static void setMenuVisibility(Fragment f, boolean visible) {
    f.setMenuVisibility(visible);
  }
}
