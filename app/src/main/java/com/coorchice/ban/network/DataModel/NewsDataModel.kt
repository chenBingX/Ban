package com.coorchice.ban.network.DataModel

import java.io.Serializable

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/10
 * Notes:
 */
data class NewsResponse(var stat: String, var data: List<News>) : Serializable

/**
 * uniquekey : ac785f3e552174dcfd9e6f39d0e34851
 * title : “文化和自然遗产日”上的多彩活动
 * date : 2017-06-10 15:47
 * category : 头条
 * author_name : 新华社
 * url : http://mini.eastday.com/mobile/170610154749674.html
 * thumbnail_pic_s : http://01.imgmini.eastday.com/mobile/20170610/20170610154749_ba360d808776f31c89ddc6ef07a30f2c_3_mwpm_03200403.jpeg
 * thumbnail_pic_s02 : http://01.imgmini.eastday.com/mobile/20170610/20170610154749_08142394310026ce3403c923bdcbffa6_1_mwpm_03200403.jpeg
 * thumbnail_pic_s03 : http://01.imgmini.eastday.com/mobile/20170610/20170610154749_d59cbc2d5129e00c1292e1c5af7082bb_6_mwpm_03200403.jpeg
 */

data class News(var uniquekey: String,
                var title: String,
                var date: String,
                var category: String,
                var author_name: String,
                var url: String,
                var thumbnail_pic_s: String,
                var thumbnail_pic_s02: String,
                var thumbnail_pic_s03: String) : Serializable


