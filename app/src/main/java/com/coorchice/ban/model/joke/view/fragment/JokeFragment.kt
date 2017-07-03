package com.coorchice.ban.model.joke.view.fragment

import android.animation.AnimatorSet
import android.app.Fragment
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.coorchice.ban.R
import com.coorchice.ban.model.base.BaseFragment
import com.coorchice.ban.utils.ScaleAnimation
import com.coorchice.ban.utils.floatJumpAnim
import com.coorchice.library.SuperTextView
import org.jetbrains.anko.find

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/24
 * Notes:
 */
class JokeFragment : BaseFragment() {

    private val JOKE_WORD = 1
    private val JOKE_PICTURE = 2


    private var tvTitle: TextView? = null
    private var btnWord: SuperTextView? = null
    private var btnPicture: SuperTextView? = null

    private var currentShowFragment: Fragment? = null
    private var currentModel: Int? = null
    private var jokeWordFragment: JokeWordFragment? = null
    private var jokePictureFragment: JokePictureFragment? = null

    companion object {
        fun getInstance(): JokeFragment {
            val bundle = Bundle()
            val fragment = JokeFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getContentViewResId(): Int {
        return R.layout.fragment_joke
    }

    override fun initData() {
    }

    override fun initView() {
        findViews()
        showModel(JOKE_WORD)
        btnWord?.alpha = 1f
        btnPicture?.alpha = 0.5f
        if (btnWordAnim == null) {
            btnWordAnim = floatJumpAnim(btnWord)
        } else {
            btnWordAnim?.start()
        }
        if (btnPictureAnim != null) {
            btnPictureAnim?.cancel()
        }
    }


    private fun findViews() {
        tvTitle = rootView?.find<TextView>(R.id.tv_title)
        tvTitle?.text = resources.getString(R.string.btn_model_3)
        rootView?.find<ImageView>(R.id.btn_back)?.visibility = View.INVISIBLE

        btnWord = rootView?.find<SuperTextView>(R.id.btn_word)
        btnPicture = rootView?.find<SuperTextView>(R.id.btn_picture)
    }


    private fun showModel(model: Int) {
        if (currentModel == model) {
            return
        }
        if (currentShowFragment != null) {
            childFragmentManager.beginTransaction()
                    .hide(currentShowFragment)
                    .commitAllowingStateLoss()
        }
        when (model) {
            JOKE_WORD -> {
                if (jokeWordFragment == null) {
                    jokeWordFragment = JokeWordFragment.getInstance()
                }
                showFragment(jokeWordFragment, model)
            }
            JOKE_PICTURE -> {
                if (jokePictureFragment == null) {
                    jokePictureFragment = JokePictureFragment.getInstance()
                }
                showFragment(jokePictureFragment, model)
            }
        }
    }


    private fun showFragment(fragment: Fragment?, model: Int) {
        if (!(fragment?.isAdded as Boolean)) {
            childFragmentManager.beginTransaction()
                    .add(R.id.layout_fragment, fragment)
                    .commitAllowingStateLoss()
        }
        if (fragment?.isDetached) {
            childFragmentManager.beginTransaction().attach(fragment).commitAllowingStateLoss()
        } else if (fragment?.isHidden) {
            childFragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss()
        }
        currentShowFragment = fragment
        currentModel = model
    }

    private var btnWordAnim: AnimatorSet? = null
    private var btnPictureAnim: AnimatorSet? = null

    override fun addListener() {
        btnWord?.setOnClickListener({
            if (currentModel != JOKE_WORD) {
                showModel(JOKE_WORD)
                btnWord?.alpha = 1f
                btnPicture?.alpha = 0.5f
                if (btnWordAnim == null) {
                    btnWordAnim = floatJumpAnim(btnWord)
                } else {
                    btnWordAnim?.start()
                }
                if (btnPictureAnim != null) {
                    btnPictureAnim?.cancel()
                    btnPicture?.scaleX = 1f
                    btnPicture?.scaleY = 1f
                }
            }
        })

        btnPicture?.setOnClickListener({
            if (currentModel != JOKE_PICTURE) {
                showModel(JOKE_PICTURE)
                btnWord?.alpha = 0.5f
                btnPicture?.alpha = 1f
                if (btnWordAnim != null) {
                    btnWordAnim?.cancel()
                    btnWord?.scaleX = 1f
                    btnWord?.scaleY = 1f
                }
                if (btnPictureAnim == null) {
                    btnPictureAnim = floatJumpAnim(btnPicture)
                } else {
                    btnPictureAnim?.start()
                }
            }

        })
    }

}