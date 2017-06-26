package com.coorchice.ban.model.wechat.model

import com.coorchice.ban.model.base.IWeChatNewsModel
import com.coorchice.ban.model.base.OnResponseListener
import com.coorchice.ban.network.DataModel.WechatNewsResponse
import com.coorchice.ban.network.Net
import com.coorchice.ban.network.OnFailureCallback
import com.coorchice.ban.network.OnSuccessCallback

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/12
 * Notes:
 */
class WechatNewsModel : IWeChatNewsModel {

    override fun requestWechatNewsData(pno: Int, ps: Int, onResponseListener: OnResponseListener<WechatNewsResponse>) {
        Net.getWechatNewsData(pno, ps,
                object:OnSuccessCallback<WechatNewsResponse?>{
                    override fun onSuccess(data: WechatNewsResponse?) {
                        onResponseListener.onSuccess(data)
                    }

                },
                object :OnFailureCallback<WechatNewsResponse?>{
                    override fun onFailure(message: String?) {
                        onResponseListener.onFailure(message)
                    }

                }
                )
    }
}