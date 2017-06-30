package com.coorchice.ban.model.base

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.coorchice.ban.R
import kotlinx.android.synthetic.main.fragment_base.*

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/10
 * Notes:
 */
abstract class BaseFragment : Fragment() {

    var rootView: ViewGroup? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        rootView = inflater?.inflate(R.layout.fragment_base, container, false) as ViewGroup?
        rootView?.addView(inflater?.inflate(getContentViewResId(), null, false), 0)
        return rootView!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        layout_empty.setOnClickListener({
            reload()
        })
        initData()
        initView()
        addListener()
    }

    abstract fun getContentViewResId(): Int

    abstract fun initData()

    abstract fun initView()

    abstract fun addListener()

    fun showEmpty(show: Boolean){
        if (show)
            layout_empty.visibility = View.VISIBLE
        else
            layout_empty.visibility = View.GONE
    }

    fun showProgress(show: Boolean){
        if (show)
            progress_bar.visibility = View.VISIBLE
        else
            progress_bar.visibility = View.GONE
    }

    fun reload(){

    }


}