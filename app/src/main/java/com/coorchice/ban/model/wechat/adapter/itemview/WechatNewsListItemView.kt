package com.coorchice.ban.model.wechat.adapter.itemview

import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.coorchice.ban.R
import com.coorchice.ban.model.base.toWebActivity
import com.coorchice.ban.network.DataModel.WechatNews
import kotlinx.android.synthetic.main.item_view_wechat_news_list.view.*

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/12
 * Notes:
 */
class WechatNewsListItemView(context:Context):RelativeLayout(context){

    init {
        LayoutInflater.from(context).inflate(R.layout.item_view_wechat_news_list,this,true)
        initView()
    }

    private fun initView() {

    }

    fun setData(data:WechatNews?, position:Int){
        tv_title.text = data?.title
        tv_source.text = data?.source
        pic.setImageURI(data?.firstImg)
        setOnClickListener {
            toWebActivity(context, data?.url, data?.title)
        }
    }


}