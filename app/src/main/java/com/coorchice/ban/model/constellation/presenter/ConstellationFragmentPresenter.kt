package com.coorchice.ban.model.constellation.presenter

import com.coorchice.ban.model.base.IConstellationFragment
import com.coorchice.ban.model.base.OnResponseListener
import com.coorchice.ban.model.constellation.model.ConstellationModel
import com.coorchice.ban.network.DataModel.ConstellationInfo
import com.coorchice.ban.utils.loge

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/26
 * Notes:
 */
class ConstellationFragmentPresenter(var view: IConstellationFragment) {
    val model = ConstellationModel()

    fun requestConstellationData(consName: String, type: String){
        model.requestConstellationInfo(consName, type, object: OnResponseListener<ConstellationInfo?>{

            override fun onSuccess(data: ConstellationInfo?) {
                view.updateConstellationData(data)
            }

            override fun onFailure(message: String?) {
                if (message != null) {
                    loge(message)
                }
            }

        })
    }
}