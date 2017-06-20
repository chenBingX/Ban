package com.coorchice.ban.model.base.AppFragmentPagerAdapter;

import java.util.Arrays;

import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.os.BuildCompat;

/**
 * Project Name:kratos
 * Author:Chice
 * Date:2016/12/19
 * Notes:
 */

public class FragmentCompat {
  interface FragmentCompatImpl {
    void setMenuVisibility(Fragment f, boolean visible);
    void setUserVisibleHint(Fragment f, boolean deferStart);
    void requestPermissions(Fragment fragment, String[] permissions, int requestCode);
    boolean shouldShowRequestPermissionRationale(Fragment fragment, String permission);
  }

  static class BaseFragmentCompatImpl implements FragmentCompatImpl {
    @Override
    public void setMenuVisibility(Fragment f, boolean visible) {
    }
    @Override
    public void setUserVisibleHint(Fragment f, boolean deferStart) {
    }
    @Override
    public void requestPermissions(final Fragment fragment, final String[] permissions,
                                   final int requestCode) {
      Handler handler = new Handler(Looper.getMainLooper());
      handler.post(new Runnable() {
        @Override
        public void run() {
          final int[] grantResults = new int[permissions.length];

          Context context = fragment.getActivity();
          if (context != null) {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();

            final int permissionCount = permissions.length;
            for (int i = 0; i < permissionCount; i++) {
              grantResults[i] = packageManager.checkPermission(
                permissions[i], packageName);
            }
          } else {
            Arrays.fill(grantResults, PackageManager.PERMISSION_DENIED);
          }

          ((FragmentCompat.OnRequestPermissionsResultCallback) fragment).onRequestPermissionsResult(
            requestCode, permissions, grantResults);
        }
      });
    }
    @Override
    public boolean shouldShowRequestPermissionRationale(Fragment fragment, String permission) {
      return false;
    }
  }

  static class ICSFragmentCompatImpl extends BaseFragmentCompatImpl {
    @Override
    public void setMenuVisibility(Fragment f, boolean visible) {
      FragmentCompatICS.setMenuVisibility(f, visible);
    }
  }

  static class ICSMR1FragmentCompatImpl extends ICSFragmentCompatImpl {
    @Override
    public void setUserVisibleHint(Fragment f, boolean deferStart) {
      FragmentCompatICSMR1.setUserVisibleHint(f, deferStart);
    }
  }

  static class MncFragmentCompatImpl extends ICSMR1FragmentCompatImpl {
    @Override
    public void requestPermissions(Fragment fragment, String[] permissions, int requestCode) {
      FragmentCompat23.requestPermissions(fragment, permissions, requestCode);
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(Fragment fragment, String permission) {
      return FragmentCompat23.shouldShowRequestPermissionRationale(fragment, permission);
    }
  }

  static class NFragmentCompatImpl extends MncFragmentCompatImpl {
    @Override
    public void setUserVisibleHint(Fragment f, boolean deferStart) {
      FragmentCompatApi24.setUserVisibleHint(f, deferStart);
    }
  }

  static final FragmentCompatImpl IMPL;
  static {
    if (BuildCompat.isAtLeastN()) {
      IMPL = new NFragmentCompatImpl();
    } else if (Build.VERSION.SDK_INT >= 23) {
      IMPL = new MncFragmentCompatImpl();
    } else if (android.os.Build.VERSION.SDK_INT >= 15) {
      IMPL = new ICSMR1FragmentCompatImpl();
    } else if (android.os.Build.VERSION.SDK_INT >= 14) {
      IMPL = new ICSFragmentCompatImpl();
    } else {
      IMPL = new BaseFragmentCompatImpl();
    }
  }

  public interface OnRequestPermissionsResultCallback {
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults);
  }

  public static void setMenuVisibility(Fragment f, boolean visible) {
    IMPL.setMenuVisibility(f, visible);
  }

  public static void setUserVisibleHint(Fragment f, boolean deferStart) {
    IMPL.setUserVisibleHint(f, deferStart);
  }

  public static void requestPermissions(@NonNull Fragment fragment,
                                        @NonNull String[] permissions, int requestCode) {
    IMPL.requestPermissions(fragment, permissions, requestCode);
  }

  public static boolean shouldShowRequestPermissionRationale(@NonNull Fragment fragment,
                                                             @NonNull String permission) {
    return IMPL.shouldShowRequestPermissionRationale(fragment, permission);
  }
}