package com.coorchice.ban.model.constellation.view.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.app.Fragment
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import com.coorchice.ban.R
import com.coorchice.ban.model.base.AppFragmentPagerAdapter.FragmentPagerAdapter
import com.coorchice.ban.model.base.BaseFragment
import com.coorchice.ban.model.constellation.entry.Constellation
import com.coorchice.ban.model.constellation.entry.ConstellationDataType
import com.coorchice.ban.utils.*
import kotlinx.android.synthetic.main.fragment_constellation.*
import kotlinx.android.synthetic.main.layout_header_bar.*
import kotlinx.android.synthetic.main.layout_select_constellation.*
import kotlinx.android.synthetic.main.layout_header_bar.btn_back as btnHeaderBack

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/26
 * Notes:
 */
class ConstellationFragment : BaseFragment() {

    private val fragments = arrayOfNulls<Fragment>(5)
    private var constellation: Constellation? = null
    private var iconViews: List<View>? = null
    private var bgScaleAnim: AnimatorSet? = null
    private var iconRotateAnim: AnimatorSet? = null

    companion object {
        fun getInstance(): ConstellationFragment {
            val bundle = Bundle()
            val fragment = ConstellationFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getContentViewResId(): Int {
        return R.layout.fragment_constellation
    }

    override fun initData() {
        constellation = SpCacher.getConstellation()
    }

    override fun initView() {
        tv_title.text = resources.getString(R.string.btn_model_4)
        btnHeaderBack.visibility = View.INVISIBLE
        tv_time.text = "${formatCurrentDate("MM/dd")} ${getWeekOfCurrent()}"
        btn_right.visibility = View.VISIBLE
        btn_right.setImageResource(R.drawable.select_constellation)
        iv_constellation_bg.setImageURI(AppUtils.getResUriString(R.drawable.constellation_bg))

        iconViews = listOf(icon_aries, icon_taurus, icon_gemini, icon_cancer, icon_lion, icon_virgo, icon_libra, icon_scorpio, icon_sagittarius, icon_capricorn, icon_aquarius, icon_pisces)
        listenIconClick()
        for (i in 0..4) {
            fragments[i] = ConstellationDetailFragment.getInstance(constellation, ConstellationDataType.values()[i])
        }
        initTabLayoutAndVp()
        if (constellation == null) {
            playAnim()
        } else {
            layout_select_constellation.visibility = View.GONE
//            updateConstellation()
        }
    }

    private fun listenIconClick() {
        for (i in 0..11) {
            iconViews?.get(i)?.setOnClickListener({
                constellation = Constellation.values()[i]
                if (constellation != null) SpCacher.saveConstellation(constellation!!)
                updateConstellation()
                stopSelectConstellation()
            })
        }

    }

    private fun stopSelectConstellation() {
        val set = AnimatorSet()
        var anim0 = ScaleAnimation(layout_select_constellation, 1.5f, 300, LinearInterpolator())
        var anim1 = alphaAnim(layout_select_constellation, 1f, 0f, 300, LinearInterpolator())
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                bgScaleAnim?.end()
                iconRotateAnim?.end()
                layout_select_constellation.visibility = View.GONE
            }
        })
        set.playTogether(anim0, anim1)
        set.start()
    }

    fun updateConstellation() {
        setTheme()
        fragments.filterNotNull()
                .map { it as ConstellationDetailFragment }
                .forEach { it.updateConstellation(constellation) }
    }

    private fun initTabLayoutAndVp() {
        vp.offscreenPageLimit = fragments.size
        vp.adapter = object : FragmentPagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment? {
                var fragment: Fragment? = fragments[position]
                return fragment
            }

            override fun getCount(): Int {
                return 5
            }

            override fun getPageTitle(position: Int): CharSequence {
                return ConstellationDataType.values()[position].title
            }
        }
        for (i in 0..5) {
            layout_tab.addTab(layout_tab.newTab().setText(ConstellationDataType.values()[i].title))
        }
        setTheme()
        layout_tab.setupWithViewPager(vp)
    }

    private fun setTheme() {
        val selectedColor = constellation?.color ?: Constellation.AQUARIUS.color
        layout_tab.setSelectedTabIndicatorColor(selectedColor)
        layout_tab.setTabTextColors(resources.getColor(R.color.md_grey_400), selectedColor)
        tv_date.text = constellation?.date
        layout_header.setBackgroundColor(selectedColor)
        val icon = constellation?.icon
        if (icon != null) {
            iv_icon.setImageURI(AppUtils.getResUriString(icon))
        }
        val bg = constellation?.bg
        if (bg != null) {
            sd_bg.setImageURI(AppUtils.getResUriString(bg))
        }

    }

    override fun addListener() {

        btn_right.setOnClickListener({
            playAnim()
        })

        btn_back_select.setOnClickListener({
            stopSelectConstellation()
        })
    }

    fun playAnim() {
        layout_select_constellation.alpha = 1f
        layout_select_constellation.scaleX = 1f
        layout_select_constellation.scaleY = 1f
        layout_constellations.rotation = 0f
        layout_select_constellation.visibility = View.VISIBLE

        iv_constellation_bg.alpha = 0.1f
        tv_please_choose.visibility = View.GONE
        for (i in 0..11) {
            iconViews?.get(i)?.visibility = View.GONE
        }
        playAnim0()
    }

    fun playAnim0() {
        val set0 = AnimatorSet()
        var bgAnim1 = ScaleAnimation(iv_constellation_bg, 1.5f, 1f, 300, LinearInterpolator())
        var bgAnim2 = alphaAnim(iv_constellation_bg, 0.1f, 1f, 300, LinearInterpolator())
        set0.playTogether(bgAnim1, bgAnim2)
        set0.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                playAnim1()
            }
        })
        set0.start()
    }

    private fun playAnim1() {
        bgScaleAnim = ScaleAnimation(iv_constellation_bg, 1.3f, 1000 * 30, LinearInterpolator(), true)
        bgScaleAnim?.start()
        val set1 = AnimatorSet()
        val set1List = mutableListOf<Animator>()
        (0..11).mapTo(set1List) { getIconScaleAnim(iconViews?.get(it)) }
        for (i in 0..11) {
            listenIconScaleAnim(set1List[i], iconViews?.get(i))
        }
        set1.playSequentially(set1List)
        set1.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                tv_please_choose.visibility = View.VISIBLE
                iconRotateAnim = rotateAnim(layout_constellations, 0f, 360f, 1000 * 60 * 7, LinearInterpolator(), true)
                animation?.removeListener(this)
            }
        })
        set1.start()
    }

    private fun getIconScaleAnim(view: View?) = ScaleAnimation(view, 0.2f, 1f, 50L, LinearInterpolator())

    fun listenIconScaleAnim(anim: Animator, view: View?) {
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                view?.visibility = View.VISIBLE
                animation?.removeListener(this)
            }
        })
    }


}