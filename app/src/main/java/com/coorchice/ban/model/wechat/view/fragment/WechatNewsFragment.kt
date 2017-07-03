package com.coorchice.ban.model.wechat.view.fragment

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.coorchice.ban.R
import com.coorchice.ban.model.base.BaseFragment
import com.coorchice.ban.model.base.IWechatNewsFragment
import com.coorchice.ban.model.wechat.adapter.WechatNewsListAdapter
import com.coorchice.ban.model.wechat.presenter.WechatNewsFragmentPresenter
import com.coorchice.ban.network.DataModel.WechatNews
import com.coorchice.ban.utils.loge
import com.coorchice.library.SuperTextView
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout
import org.jetbrains.anko.dip
import org.jetbrains.anko.find
import org.jetbrains.anko.sp
import org.jetbrains.anko.textColor

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/12
 * Notes:
 */
class WechatNewsFragment : BaseFragment(), IWechatNewsFragment {

    private var wechatNewsList: RecyclerView? = null
    private var layoutRefresh: SuperSwipeRefreshLayout? = null
    private var tvTitle: TextView? = null

    private var wechatNewsListAdapter: WechatNewsListAdapter? = null

    val presenter = WechatNewsFragmentPresenter(this)

    companion object {
        fun getInstance(): WechatNewsFragment {
            val bundle = Bundle()
            val fragment = WechatNewsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getContentViewResId(): Int {
        return R.layout.fragment_wechat_news
    }

    override fun initData() {
        presenter.requestWechatNewsData(true)
    }

    override fun initView() {
        showEmpty(true)
        findView()
        initLayoutRefresh()
        initWechatNewsList()

    }

    private fun findView() {
        tvTitle = rootView?.find<TextView>(R.id.tv_title)
        tvTitle?.text = resources.getString(R.string.btn_model_2)
        rootView?.find<ImageView>(R.id.btn_back)?.visibility = View.INVISIBLE
        layoutRefresh = rootView?.find<SuperSwipeRefreshLayout>(R.id.layout_refresh)
        wechatNewsList = rootView?.find<RecyclerView>(R.id.rv_wechat_news_list)
    }

    private fun initLayoutRefresh() {
        setLoadView()
        layoutRefresh?.setOnPullRefreshListener(object : SuperSwipeRefreshLayout.OnPullRefreshListener {
            override fun onPullEnable(p0: Boolean) {
                loge("refresh -> onPullEnable")
            }

            override fun onPullDistance(p0: Int) {
                loge("refresh -> onPullDistance")

            }

            override fun onRefresh() {
                loge("refresh -> onRefresh")
                presenter.requestWechatNewsData(true)
            }
        })

        layoutRefresh?.setOnPushLoadMoreListener(object : SuperSwipeRefreshLayout.OnPushLoadMoreListener {
            override fun onPushDistance(p0: Int) {
                loge("loadmore -> onPushDistance")
            }

            override fun onPushEnable(p0: Boolean) {
                loge("loadmore -> onPushEnable")

            }

            override fun onLoadMore() {
                loge("loadmore -> onLoadMore")
                presenter.requestWechatNewsData(false)
            }

        })

    }

    private fun setLoadView() {
        var refreshFooterView = SuperTextView(activity)
        refreshFooterView.gravity = Gravity.CENTER
        refreshFooterView.text = "奋力加载中..."
        refreshFooterView.textColor = resources.getColor(R.color.md_black_1000)
        refreshFooterView.textSize = 12f
        val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        refreshFooterView.layoutParams = lp
        layoutRefresh?.setFooterView(refreshFooterView)
    }

    private fun initWechatNewsList() {
        wechatNewsList?.layoutManager = LinearLayoutManager(activity)
        wechatNewsList?.itemAnimator = DefaultItemAnimator()
        wechatNewsList?.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                var position = parent?.layoutManager?.getPosition(view)
                if (position == parent?.adapter?.itemCount?.minus(1)) {
                    outRect?.bottom = dip(16)
                }
            }
        })
        wechatNewsListAdapter = WechatNewsListAdapter(activity, emptyList())
        wechatNewsList?.adapter = wechatNewsListAdapter
    }

    override fun addListener() {
        tvTitle?.setOnClickListener{
            wechatNewsList?.smoothScrollToPosition(0)
        }
    }

    override fun updateWechatNewsData(data: List<WechatNews>?) {
        showEmpty(false)
        if (layoutRefresh?.isRefreshing == true) {
            layoutRefresh?.isRefreshing = false
        }
        layoutRefresh?.setLoadMore(false)
        wechatNewsListAdapter?.updateData(data)
    }

    override fun onSpecialSituation(message: String?) {
        if (layoutRefresh?.isRefreshing == true) {
            layoutRefresh?.isRefreshing = false
        }
        layoutRefresh?.setLoadMore(false)
    }
}