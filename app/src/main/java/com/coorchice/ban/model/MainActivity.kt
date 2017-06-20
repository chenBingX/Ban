package com.coorchice.ban.model

import android.app.Fragment
import android.os.Bundle
import com.coorchice.ban.R
import com.coorchice.ban.model.base.BaseActivity
import com.coorchice.ban.model.news.view.fragment.NewsFragment
import com.coorchice.ban.model.wechat.view.fragment.WechatNewsFragment
import com.coorchice.ban.network.DataModel.NewsResponse
import com.coorchice.ban.network.Net
import com.coorchice.ban.network.OnFailureCallback
import com.coorchice.ban.network.OnSuccessCallback
import com.coorchice.ban.utils.Pop
import com.coorchice.ban.utils.loge
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.textColor

class MainActivity : BaseActivity() {
    val NEWS = 0
    val WECHAT = 1
    val JOKE = 2
    val CONSTELLATION = 3
    val DREAM = 4

    private var currentShowFragment: Fragment? = null
    private var currentModel: Int? = null
    val TAG_NEWS_FRAGMENT = "tag_news_fragment"
    private var newsFragment: Fragment? = null
    private var wechatNewsFragment: WechatNewsFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getContentViewResId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        Net.getNewsData("top",
                object : OnSuccessCallback<NewsResponse?> {
                    override fun onSuccess(data: NewsResponse?) {
                        loge(Gson().toJson(data))
                    }
                },
                object : OnFailureCallback<NewsResponse?> {
                    override fun onFailure(message: String?) {
                    }
                }
        )
    }

    override fun initView() {
        selectModel(NEWS)
        showModel(NEWS)
    }

    override fun addListener() {
        btn_model_1.setOnClickListener {
            selectModel(NEWS)
            showModel(NEWS)
            Pop(it)
        }
        btn_model_2.setOnClickListener {
            selectModel(WECHAT)
            showModel(WECHAT)
            Pop(it)

        }
        btn_model_3.setOnClickListener {
            selectModel(JOKE)
            showModel(JOKE)
            Pop(it)

        }
        btn_model_4.setOnClickListener {
            selectModel(CONSTELLATION)
            showModel(CONSTELLATION)
            Pop(it)

        }
        btn_model_5.setOnClickListener {
            selectModel(DREAM)
            showModel(DREAM)
            Pop(it)

        }
    }

    private fun showModel(model: Int) {
        if (currentModel == model) {
            return
        }
        if (currentShowFragment != null) {
            fragmentManager.beginTransaction()
                    .hide(currentShowFragment)
                    .commitAllowingStateLoss()
        }
        when (model) {
            NEWS -> {
                if (newsFragment == null) {
                    newsFragment = NewsFragment.getInstance()
                }
                showFragment(newsFragment, model)
            }
            WECHAT -> {
                if (wechatNewsFragment == null) {
                    wechatNewsFragment = WechatNewsFragment.getInstance()
                }
                showFragment(wechatNewsFragment, model)
            }
            JOKE -> {
                currentShowFragment = null
                currentModel = model
            }
            CONSTELLATION -> {
                currentShowFragment = null
                currentModel = model
            }
            DREAM -> {
                currentShowFragment = null
                currentModel = model
            }

        }
    }

    private fun showFragment(fragment: Fragment?, model: Int) {
        if (!(fragment?.isAdded as Boolean)) {
            fragmentManager.beginTransaction()
                    .add(R.id.layout_fragment, fragment, TAG_NEWS_FRAGMENT)
                    .commitAllowingStateLoss()
        }
        if (fragment?.isDetached as Boolean) {
            fragmentManager.beginTransaction().attach(fragment).commitAllowingStateLoss()
        } else if (fragment?.isHidden!!) {
            fragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss()
        }
        currentShowFragment = fragment
        currentModel = model
    }

    private fun selectModel(model: Int) {
        resetAllModelBtnState()
        changeSelectedModelBtnState(model)
    }

    private fun resetAllModelBtnState() {
        btn_model_1.drawable = resources.getDrawable(R.drawable.news)
        btn_model_2.drawable = resources.getDrawable(R.drawable.wechat)
        btn_model_3.drawable = resources.getDrawable(R.drawable.joke)
        btn_model_4.drawable = resources.getDrawable(R.drawable.constellation)
        btn_model_5.drawable = resources.getDrawable(R.drawable.dream)

        btn_model_1.textColor = resources.getColor(R.color.home_bottom_bt_text)
        btn_model_2.textColor = resources.getColor(R.color.home_bottom_bt_text)
        btn_model_3.textColor = resources.getColor(R.color.home_bottom_bt_text)
        btn_model_4.textColor = resources.getColor(R.color.home_bottom_bt_text)
        btn_model_5.textColor = resources.getColor(R.color.home_bottom_bt_text)
    }

    private fun changeSelectedModelBtnState(model: Int) {
        when (model) {
            NEWS -> {
                btn_model_1.drawable = resources.getDrawable(R.drawable.news_select)
                btn_model_1.textColor = resources.getColor(R.color.home_bottom_bt_text_select)
            }
            WECHAT -> {
                btn_model_2.drawable = resources.getDrawable(R.drawable.wechat_select)
                btn_model_2.textColor = resources.getColor(R.color.home_bottom_bt_text_select)
            }
            JOKE -> {
                btn_model_3.drawable = resources.getDrawable(R.drawable.joke_select)
                btn_model_3.textColor = resources.getColor(R.color.home_bottom_bt_text_select)
            }
            CONSTELLATION -> {
                btn_model_4.drawable = resources.getDrawable(R.drawable.constellation_select)
                btn_model_4.textColor = resources.getColor(R.color.home_bottom_bt_text_select)
            }
            DREAM -> {
                btn_model_5.drawable = resources.getDrawable(R.drawable.dream_select)
                btn_model_5.textColor = resources.getColor(R.color.home_bottom_bt_text_select)
            }

        }
    }


}
