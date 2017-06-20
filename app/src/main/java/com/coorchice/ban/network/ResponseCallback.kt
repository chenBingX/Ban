package com.coorchice.ban.network

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/10
 * Notes:
 */
interface OnSuccessCallback<T> {
    fun onSuccess(data: T?)
}

interface OnFailureCallback<T> {
    fun onFailure(message: String?)
}