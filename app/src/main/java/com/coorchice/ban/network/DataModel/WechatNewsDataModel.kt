package com.coorchice.ban.network.DataModel

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/12
 * Notes:
 */
/*
  "list": []
  "totalPage": 16,
  "ps": 20,
  "pno": 1
*/
data class WechatNewsResponse(var list: List<WechatNews>,
                              var totalPage : Int,
                              var ps : Int,
                              var pno : Int)
/*
{
    "id": "wechat_20150401071581",
    "title": "号外：集宁到乌兰花的班车出事了！！！！！",
    "source": "内蒙那点事儿",
    "firstImg": "http://zxpic.gtimg.com/infonew/0/wechat_pics_-214279.jpg/168",
    "mark": "",
    "url": "http://v.juhe.cn/weixin/redirect?wid=wechat_20150401071581"
}*/
data class WechatNews(var id : String,
                      var title : String,
                      var source : String,
                      var firstImg : String,
                      var mark : String,
                      var url : String)