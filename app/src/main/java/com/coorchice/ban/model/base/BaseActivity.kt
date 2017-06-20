package com.coorchice.ban.model.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/10
 * Notes:
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewResId())
        initData()
        initView()
        addListener()
    }

    abstract fun getContentViewResId(): Int

    abstract fun initData()

    abstract fun initView()

    abstract fun addListener()
}