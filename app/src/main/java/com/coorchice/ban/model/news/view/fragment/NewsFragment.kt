package com.coorchice.ban.model.news.view.fragment

import android.app.Fragment
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.coorchice.ban.R
import com.coorchice.ban.model.base.BaseFragment
import com.coorchice.ban.model.base.OnItemViewClickListener
import com.coorchice.ban.model.base.AppFragmentPagerAdapter.FragmentPagerAdapter
import com.coorchice.ban.model.news.adapter.NewsNavigatorListAdapter
import com.coorchice.ban.model.news.entry.NewsNavigation
import kotlinx.android.synthetic.main.layout_header_bar.*
import org.jetbrains.anko.dip
import org.jetbrains.anko.find

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/10
 * Notes:
 */
class NewsFragment : BaseFragment() {

    private var newsNavigationList: RecyclerView? = null
    private var vpNewsListFragment: ViewPager? = null

    private var newsNavigatorListAdapter: NewsNavigatorListAdapter? = null
    private var currentModel: Int = 0
    private val fragments = arrayOfNulls<Fragment>(6)

    companion object {
        fun getInstance(): Fragment {
            val bundle = Bundle()
            val instance = NewsFragment()
            instance.arguments = bundle
            return instance
        }
    }

    override fun getContentViewResId(): Int {
        return R.layout.fragment_news
    }

    override fun initData() {
    }


    override fun initView() {
        findView()
        btn_back.visibility = View.INVISIBLE
        tv_title.text = resources.getString(R.string.btn_model_1)
//        header_line.visibility = View.GONE
        initNewNavigationList()
        initVpNewsListFragment()
    }

    private fun findView() {
        newsNavigationList = rootView?.find<RecyclerView>(R.id.rv_news_navigation)
        vpNewsListFragment = rootView?.find<ViewPager>(R.id.vp_news_list_fragment)
    }

    private fun initNewNavigationList() {
        newsNavigationList?.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        newsNavigationList?.itemAnimator = DefaultItemAnimator()
        newsNavigationList?.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                val position = parent?.layoutManager?.getPosition(view)
                if (position == 0) {
                    outRect?.left = dip(16)
                } else {
                    outRect?.left = dip(8f)
                }
                if (position == parent?.layoutManager?.childCount) {
                    outRect?.right = dip(16)
                }
            }
        })
        newsNavigatorListAdapter = NewsNavigatorListAdapter(activity)
        newsNavigatorListAdapter?.onItemViewClickListener =
                object : OnItemViewClickListener<NewsNavigation> {
                    override fun OnItemViewClick(data: NewsNavigation, position: Int) {
                        currentModel = position
                        vpNewsListFragment?.setCurrentItem(position, true)
                        newsNavigatorListAdapter?.selectedItem = position
                    }

                }
        newsNavigationList?.adapter = newsNavigatorListAdapter
    }

    private fun initVpNewsListFragment() {
        vpNewsListFragment?.offscreenPageLimit = 6
        vpNewsListFragment?.adapter = object : FragmentPagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment {
                var fragment: Fragment? = fragments[position]
                if (fragment == null) {
                    fragment = NewsListFragment.getInstance(NewsNavigation.values()[position].requestCode)
                    fragments[position] = fragment
                }
                return fragment
            }

            override fun getCount(): Int {
                return NewsNavigation.values().size
            }
        }
        vpNewsListFragment?.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(position: Int) {
                currentModel = position
                newsNavigationList?.smoothScrollToPosition(position)
                newsNavigatorListAdapter?.selectedItem = position
            }

        })
        vpNewsListFragment?.currentItem = 0
    }

    override fun addListener() {

    }

}

