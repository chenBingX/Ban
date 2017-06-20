package com.coorchice.ban.model.wechat.presenter

import com.coorchice.ban.model.base.IWechatNewsFragment
import com.coorchice.ban.model.base.OnResponseListener
import com.coorchice.ban.model.wechat.model.WechatNewsModel
import com.coorchice.ban.network.DataModel.WechatNews
import com.coorchice.ban.network.DataModel.WechatNewsResponse
import com.coorchice.ban.utils.loge

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/12
 * Notes:
 */
class WechatNewsFragmentPresenter(var view: IWechatNewsFragment) {

    val wechatNewsModel = WechatNewsModel()
    val datas = mutableListOf<WechatNews>()
    val ps: Int = 20
    var pno: Int = 0
    var total: Int = 1

    fun requestWechatNewsData(refresh: Boolean) {
        pno++
        if (refresh) {
            pno = 1
            datas.clear()
        }
        if (pno <= total) {
            wechatNewsModel.requestWechatNewsData(pno, ps,
                    object : OnResponseListener<WechatNewsResponse> {
                        override fun onSuccess(data: WechatNewsResponse?) {
                            total = data?.totalPage as Int
                            datas.addAll(data?.list)
                            val newData = mutableListOf<WechatNews>()
                            newData.addAll(datas)
                            view.updateWechatNewsData(newData)
                        }

                        override fun onFailure(message: String?) {
                            loge(message ?: "无错误信息")
                            view.onSpecialSituation(message)
                        }
                    })
        } else {
            view.onSpecialSituation("没有了！")
            loge("没有了！")
        }
    }


}