package com.coorchice.ban.model.constellation.view.fragment

import android.animation.AnimatorSet
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ProgressBar
import android.widget.TextView
import com.coorchice.ban.R
import com.coorchice.ban.model.base.BaseFragment
import com.coorchice.ban.model.base.IConstellationFragment
import com.coorchice.ban.model.constellation.entry.Constellation
import com.coorchice.ban.model.constellation.entry.ConstellationDataType
import com.coorchice.ban.model.constellation.presenter.ConstellationFragmentPresenter
import com.coorchice.ban.network.DataModel.ConstellationInfo
import com.coorchice.ban.utils.playTranslationYAnim
import kotlinx.android.synthetic.main.fragment_constellation_detail_today.*
import kotlinx.android.synthetic.main.layout_week_constellation.*
import org.jetbrains.anko.textColor

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/27
 * Notes:
 */
class ConstellationDetailFragment : BaseFragment(), IConstellationFragment {

    val presenter = ConstellationFragmentPresenter(this)
    var constellation: Constellation? = null
    var constellationDataType: ConstellationDataType? = null

    companion object {
        val KEY_CONSTELLATION = "key_constellation"
        val KEY_TYPE = "key_type"

        fun getInstance(constellation: Constellation?, constellationDataType: ConstellationDataType): ConstellationDetailFragment {
            val bundle = Bundle()
            bundle.putSerializable(KEY_CONSTELLATION, constellation)
            bundle.putSerializable(KEY_TYPE, constellationDataType)
            val fragment = ConstellationDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getContentViewResId(): Int = R.layout.fragment_constellation_detail_today

    override fun initData() {
        constellation = arguments.getSerializable(KEY_CONSTELLATION) as? Constellation
        constellationDataType = arguments.getSerializable(KEY_TYPE) as? ConstellationDataType
        if (constellation != null && constellationDataType != null) {
            presenter.requestConstellationData(constellation?.type as String, constellationDataType?.type as String)
        }
        updateTheme()
    }

    override fun initView() {
        showEmpty(true)
    }

    override fun addListener() {
    }

    fun updateTheme() {
        if (constellation != null) {
            tv_lucky_color.textColor = constellation!!.color
            tv_lucky_number.textColor = constellation!!.color
            tv_match_constellation.textColor = constellation!!.color
            platform_color.solid = constellation!!.color
            platform_number.solid = constellation!!.color
            platform_match_constellation.solid = constellation!!.color
        }
    }

    private var floatAnimColor: AnimatorSet? = null
    private var floatAnimNumber: AnimatorSet? = null
    private var floatAnimMatch_constellation: AnimatorSet? = null

    override fun updateConstellationData(data: ConstellationInfo?) {
        showEmpty(false)
        var summary = data?.summary ?: ""
        tv_description.text = "  " + summary
        setLuckyData(data?.all, tv_lucky_index_all, tv_info_all, pb_all)
        setLuckyData(data?.love, tv_lucky_index_love, tv_info_love, pb_love)
        setLuckyData(data?.work, tv_lucky_index_work, tv_info_work, pb_work)
        setLuckyData(data?.money, tv_lucky_index_money, tv_info_money, pb_money)
        setLuckyData(data?.health, tv_lucky_index_health, tv_info_health, pb_health)
        setDifferent(data)
        tv_lucky_color.text = data?.color
        tv_lucky_number.text = data?.number
        tv_match_constellation.text = data?.QFriend ?: "无"
    }

    private fun setDifferent(data: ConstellationInfo?) {
        if (data?.love != null) {
            if (data.love?.length!! > 3) {
                layout_week_constellation.visibility = View.VISIBLE
                tv_description.visibility = View.GONE
                layout_lucky_index.visibility = View.GONE
                floater_1.visibility = View.GONE
                floater_2.visibility = View.GONE
                floater_3.visibility = View.GONE
            } else {
                layout_week_constellation.visibility = View.GONE
                tv_description.visibility = View.VISIBLE
                layout_lucky_index.visibility = View.VISIBLE
                floater_1.visibility = View.VISIBLE
                floater_2.visibility = View.VISIBLE
                floater_3.visibility = View.VISIBLE
                floatAnimColor?.end()
                floatAnimNumber?.end()
                floatAnimMatch_constellation?.end()
                val from = 10f
                val to = -20f
                floatAnimColor = playTranslationYAnim(platform_color, from, to, 3 * 1000, AccelerateDecelerateInterpolator(), true)
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    floatAnimNumber = playTranslationYAnim(platform_number, from, to, 3 * 1000, AccelerateDecelerateInterpolator(), true)
                }, 800L)
                handler.postDelayed({
                    floatAnimMatch_constellation = playTranslationYAnim(platform_match_constellation, from, to, 3 * 1000, AccelerateDecelerateInterpolator(), true)
                    handler.removeCallbacksAndMessages(null)
                }, 1600L)
            }

        }
    }

    fun setLuckyData(percent: String?, tvLuckyIndex: TextView, tvInfo: TextView, pb: ProgressBar) {
        if (percent != null) {
            if (percent.length > 3) {
                tvInfo.text = percent
                pb.visibility = View.GONE
            } else {
                pb.visibility = View.VISIBLE
                tvLuckyIndex.text = percent
                pb.max = 100
                pb.progress = percent.substring(0, percent.length - 1).toInt()
            }
        } else {
            tvInfo.text = "无"
        }
    }

    fun updateConstellation(data: Constellation?) {
        this.constellation = data
        if (constellation != null && constellationDataType != null) {
            presenter.requestConstellationData(constellation?.type as String, constellationDataType?.type as String)
        }
        updateTheme()
    }

}