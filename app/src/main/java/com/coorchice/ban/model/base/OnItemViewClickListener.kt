package com.coorchice.ban.model.base

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/11
 * Notes:
 */
interface OnItemViewClickListener<T> {
    fun OnItemViewClick(data: T, position: Int)
}