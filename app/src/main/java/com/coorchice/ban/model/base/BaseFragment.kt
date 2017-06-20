package com.coorchice.ban.model.base

import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/10
 * Notes:
 */
abstract class BaseFragment : Fragment() {

    var rootView: View? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        rootView = inflater?.inflate(getContentViewResId(), container, false)
        initData()
        initView()
        addListener()
        return rootView!!
    }

    abstract fun getContentViewResId(): Int

    abstract fun initData()

    abstract fun initView()

    abstract fun addListener()
}