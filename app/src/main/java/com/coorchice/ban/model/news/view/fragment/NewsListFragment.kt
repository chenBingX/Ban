package com.coorchice.ban.model.news.view.fragment

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.coorchice.ban.R
import com.coorchice.ban.model.base.BaseFragment
import com.coorchice.ban.model.base.INewsListFragment
import com.coorchice.ban.model.news.adapter.NewsListAdapter
import com.coorchice.ban.model.news.presenter.NewsListFragmentPresenter
import com.coorchice.ban.network.DataModel.News
import com.coorchice.ban.utils.loge
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout
import org.jetbrains.anko.dip
import org.jetbrains.anko.find

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/11
 * Notes:
 */
class NewsListFragment : BaseFragment(), INewsListFragment {

    val presenter: NewsListFragmentPresenter = NewsListFragmentPresenter(this)

    private var newsList: RecyclerView? = null
    private var layoutRefresh: SuperSwipeRefreshLayout? = null

    var newsListAdapter: NewsListAdapter? = null

    companion object {
        val KEY_TYPE = "key_type"
        fun getInstance(type: String): NewsListFragment {
            val bundle = Bundle()
            bundle.putString(KEY_TYPE, type)
            val fragment = NewsListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getContentViewResId(): Int {
        return R.layout.fragment_news_list
    }

    override fun initData() {
        presenter.requestNewsData(arguments.getString(KEY_TYPE))
    }

    override fun initView() {
        findView()
        initNewsList()
    }


    private fun findView() {
        layoutRefresh = rootView?.find<SuperSwipeRefreshLayout>(R.id.layout_refresh)
        newsList = rootView?.find<RecyclerView>(R.id.rv_news_list)
    }

    private fun initNewsList() {
        newsList?.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        newsList?.itemAnimator = DefaultItemAnimator()
        newsList?.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                val position = parent?.layoutManager?.getPosition(view)
                if (position == 0) {
                    outRect?.top = dip(11)
                }
                if (position == parent?.adapter?.itemCount?.minus(1)) {
                    outRect?.bottom = dip(11)
                } else {
                    outRect?.bottom = dip(3)
                }
            }
        })
        newsListAdapter = NewsListAdapter(activity, emptyList())
        newsList?.adapter = newsListAdapter
    }


    override fun addListener() {
        layoutRefresh?.setOnPullRefreshListener(object : SuperSwipeRefreshLayout.OnPullRefreshListener {
            override fun onPullEnable(p0: Boolean) {
                loge("refresh -> onPullEnable")
            }

            override fun onPullDistance(p0: Int) {
                loge("refresh -> onPullDistance")

            }

            override fun onRefresh() {
                loge("refresh -> onRefresh")
                presenter.requestNewsData(arguments.getString(KEY_TYPE))
            }
        })
    }

    override fun updateNewsData(data: List<News>?) {
        if (layoutRefresh?.isRefreshing == true) {
            layoutRefresh?.isRefreshing = false
        }
        newsListAdapter?.updateData(data)
    }
}
