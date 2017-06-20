package com.coorchice.ban.model.base

import android.content.Context
import com.coorchice.ban.model.web.activity.WebActivity

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/12
 * Notes:
 */

fun toWebActivity(context: Context, url: String?, title: String?) {
    context.startActivity(WebActivity.getIntent(context, url, title))
}
