package com.coorchice.ban.model.base.AppFragmentPagerAdapter; /**
 * Project Name:kratos
 * Author:Chice
 * Date:2016/12/19
 * Notes:
 */

import android.annotation.TargetApi;
import android.app.Fragment;
import android.support.annotation.RequiresApi;

@RequiresApi(23)
@TargetApi(23)
class FragmentCompat23 {
  public static void requestPermissions(Fragment fragment, String[] permissions,
                                        int requestCode) {
    fragment.requestPermissions(permissions, requestCode);
  }

  public static boolean shouldShowRequestPermissionRationale(Fragment fragment,
                                                             String permission) {
    return fragment.shouldShowRequestPermissionRationale(permission);
  }
}