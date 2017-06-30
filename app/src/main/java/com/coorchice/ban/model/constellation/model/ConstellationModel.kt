package com.coorchice.ban.model.constellation.model

import com.coorchice.ban.model.base.IConstellationModel
import com.coorchice.ban.model.base.OnResponseListener
import com.coorchice.ban.network.DataModel.ConstellationInfo
import com.coorchice.ban.network.Net
import com.coorchice.ban.network.OnFailureCallback
import com.coorchice.ban.network.OnSuccessCallback

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/26
 * Notes:
 */
class ConstellationModel : IConstellationModel {

    override fun requestConstellationInfo(consName: String, type: String, onResponseListener: OnResponseListener<ConstellationInfo?>) {
        Net.getConstellationInfo(consName, type,
                object : OnSuccessCallback<ConstellationInfo?> {
                    override fun onSuccess(data: ConstellationInfo?) {
                        onResponseListener.onSuccess(data)
                    }

                },
                object : OnFailureCallback<ConstellationInfo?> {
                    override fun onFailure(message: String?) {
                        onResponseListener.onFailure(message)
                    }

                }
        )
    }
}