package com.coorchice.ban.model.welcome.view

import android.os.CountDownTimer
import android.view.View
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import com.coorchice.ban.R
import com.coorchice.ban.model.base.BaseActivity
import com.coorchice.ban.model.base.toMainActivity
import com.coorchice.ban.utils.AppUtils
import com.coorchice.ban.utils.alphaAnim
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : BaseActivity() {

    var timer: CountDownTimer? = null

    override fun getContentViewResId() = R.layout.activity_welcome

    override fun initData() {
        timer = object : CountDownTimer(3000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                tv_count_down.text = "00:0${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                tv_count_down.visibility = View.GONE
                btn_enter.visibility = View.VISIBLE
                alphaAnim(btn_enter, 0.2f, 1f, 800, LinearInterpolator()).start()
            }
        }.start()
    }

    override fun initView() {
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        tv_count_down.text = "00:03"
        sd_pic.setImageURI(AppUtils.getResUriString(R.drawable.welcome))
    }

    override fun addListener() {
        btn_enter.setOnClickListener {
            toMainActivity(this)
        }
    }

    override fun onDestroy() {
        timer?.cancel()
        super.onDestroy()
    }

}
