package com.coorchice.ban.model.joke.view.fragment

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.coorchice.ban.R
import com.coorchice.ban.model.base.BaseFragment
import com.coorchice.ban.model.base.IJokeFragment
import com.coorchice.ban.model.joke.adapter.JokeWordListAdapter
import com.coorchice.ban.model.joke.presenter.JokeFragmentPresenter
import com.coorchice.ban.network.DataModel.JokeWord
import com.coorchice.ban.utils.loge
import com.coorchice.library.SuperTextView
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout
import org.jetbrains.anko.dip
import org.jetbrains.anko.find
import org.jetbrains.anko.textColor

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/25
 * Notes:
 */
class JokeWordFragment : BaseFragment(), IJokeFragment<JokeWord> {

    private var layoutRefresh: SuperSwipeRefreshLayout? = null
    private var jokeWordList: RecyclerView? = null

    private val presenter: JokeFragmentPresenter<JokeWord> = JokeFragmentPresenter(this)
    private var jokeWordListAdapter: JokeWordListAdapter? = null

    companion object {
        fun getInstance(): JokeWordFragment {
            val bundle = Bundle()
            val fragment = JokeWordFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getContentViewResId(): Int {
        return R.layout.fragment_joke_word
    }

    override fun initData() {
        presenter.requestJokeWordData(true)
    }

    override fun initView() {
        showEmpty(true)
        findViews()
        initLayoutRefresh()
        initJokeWordList()
    }

    private fun findViews() {
        layoutRefresh = rootView?.find<SuperSwipeRefreshLayout>(R.id.layout_refresh)
        jokeWordList = rootView?.find<RecyclerView>(R.id.rv_joke_word_list)

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
                presenter.requestJokeWordData(true)
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
                presenter.requestJokeWordData(false)
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


    private fun initJokeWordList() {
        jokeWordList?.layoutManager = LinearLayoutManager(activity)
        jokeWordList?.itemAnimator = DefaultItemAnimator()
        jokeWordList?.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                var position = parent?.layoutManager?.getPosition(view)
                if (position == 0) {
                    outRect?.top = dip(16)
                }
                outRect?.left = dip(10)
                outRect?.right = dip(10)
                outRect?.bottom = dip(16)
                if (position == parent?.adapter?.itemCount?.minus(1)) {
                    outRect?.bottom = dip(26)
                }
            }
        })
        jokeWordListAdapter = JokeWordListAdapter(activity, emptyList())
        jokeWordList?.adapter = jokeWordListAdapter
    }

    override fun addListener() {

    }

    override fun updateJokeData(data: List<JokeWord>?) {
        showEmpty(false)
        if (layoutRefresh?.isRefreshing == true) {
            layoutRefresh?.isRefreshing = false
        }
        layoutRefresh?.setLoadMore(false)
        jokeWordListAdapter?.updateData(data)
    }
}