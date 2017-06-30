package com.coorchice.ban.model

import android.app.Fragment
import android.os.Bundle
import com.coorchice.ban.R
import com.coorchice.ban.model.base.BaseActivity
import com.coorchice.ban.model.constellation.view.fragment.ConstellationFragment
import com.coorchice.ban.model.joke.view.fragment.JokeFragment
import com.coorchice.ban.model.news.view.fragment.NewsFragment
import com.coorchice.ban.model.wechat.view.fragment.WechatNewsFragment
import com.coorchice.ban.utils.Pop
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.textColor

class MainActivity : BaseActivity() {
    private val NEWS = 0
    private val WECHAT = 1
    private val JOKE = 2
    private val CONSTELLATION = 3
    private val DREAM = 4

    private var currentShowFragment: Fragment? = null
    private var currentModel: Int? = null
    private var newsFragment: Fragment? = null
    private var wechatNewsFragment: WechatNewsFragment? = null
    private var jokeFragment: JokeFragment? = null
    private var constellationFragment: ConstellationFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getContentViewResId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
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
                if (jokeFragment == null) {
                    jokeFragment = JokeFragment.getInstance()
                }
                showFragment(jokeFragment, model)
            }
            CONSTELLATION -> {
                if (constellationFragment == null) {
                    constellationFragment = ConstellationFragment.getInstance()
                }
                showFragment(constellationFragment, model)
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
                    .add(R.id.layout_fragment, fragment)
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
