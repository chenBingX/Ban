package com.coorchice.ban.model.base

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/11
 * Notes:
 */
interface OnResponseListener<T> {
    fun onSuccess(data: T?)
    fun onFailure(message: String?)
}