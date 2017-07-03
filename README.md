
![image](http://ogemdlrap.bkt.clouddn.com/ban_cover.png)  

> ## 这城市太寂寞，《伴》在你身边

# 《伴》之序
**《伴》** 是一款完全的Kotlin项目，她展示了部分**Kotlin** 的知识。诸如类、函数、变量、流程控制语句等基础知识点，以及单例、伴生、数据类、延迟注入、转型、扩展方法、扩展属性等进阶知识点。随着项目的发展，CoorChice期待加入更多有趣实用的特性进去。  

在 **《伴》** 中，CoorChice向你展示了如何使用Kotlin快速的构建一个App，以及如何轻松愉悦的、持续的进行迭代。如果你忍受够了Java各种冗余的、反人类的语法，如果你被Java圈住太久，想要尝尝自由的味道，那么你可以在 **《伴》** 中看到Kotlin是如何来解放一个程序员的。  

CoorChice在这里准备一些Kotlin的入门知识，顺利的话你将能很快的上手Kotlin。[秘术Kotlin【http://www.jianshu.com/nb/12698208】](http://www.jianshu.com/nb/12698208)。如果你想了解更多的进阶知识，可以在网上找到很多不错的资料。   

下面，CoorChice将会简单的介绍一下《伴》。如果你希望获得更多的信息和讨论，可以进入我的主页关注我。

# 《伴》之形
《伴》使用了纯Kotlin进行开发，总体架构选用了时下流行的、稳定的MVP架构。关于MVP已经有很多资料在网络中流传了，建议大家可以先查找相关资料熟悉一下。什么？怎么查找？这会是对你有用的一篇文章：[这十个搜索技巧至少价值100万！【http://www.jianshu.com/p/a2f0f5a39cc3】](http://www.jianshu.com/p/a2f0f5a39cc3)。  
## 使用到的库
下面是一些《伴》中用到的流行库：
- **Gson**，解析Json数据。
- **OkHttp3 + Retrofit2**，处理网络请求。
- **Fresco**，展示图片。
- **SuperTextView**，这是**CoorChice**喜爱的控件，他真是可以无处不在啊！如果你感兴趣的话，这是地址[从未如此惊艳！你好，SuperTextView (v1.3)【http://www.jianshu.com/p/1b91e11e441d】](http://www.jianshu.com/p/1b91e11e441d)。  

CoorChice想说的是，Kotlin完全可以兼容现有的流行库。你还在为Kotlin的生态不够全面而害怕尝试吗？Java能用的，Kotlin也能有办法使用。  

## 听说Kotlin和Anko更配哦

《伴》还使用Kotlin的专属扩展库**Anko**。  

Anko是Kotlin的一个扩展库，它包含了很多使用的工具和强大的特性。比如用DSL语言来在代码中创建布局，虽然CoorChice对它没什么好感，但它确实是一个有意思的东西。比起在xml中写布局，这个在代码中写布局的特性实在差太多了。  

但是，Anko有一个十分让人爱不释手的特性，就是布局id就可以直接操作控件实例。强烈建议大家尝试一下，真的是爱不释手。`findViewById()`，`ButterKnife`等等你都可以停止了。
```
fragment_joke.xml
<!--这是一个xml中的控件-->

<com.coorchice.library.SuperTextView
    android:id="@+id/btn_picture"
    android:layout_width="44.65dp"
    android:layout_height="44.65dp"
    app:corner="24dp"
    app:solid="@color/md_blue_200"
    android:text="@string/picture"
    android:gravity="center"
    android:textSize="@dimen/title"
    android:textColor="@color/md_white_1000"
    android:textStyle="bold"
    android:layout_alignParentBottom="true"
    android:layout_alignParentRight="true"
    android:layout_marginBottom="24.18dp"
    android:layout_marginRight="8dp"
    />
```
通过import在Activity或Fragment中导入布局文件：
```
import kotlinx.android.synthetic.main.fragment_joke.*

```
就是【`kotlinx.android.synthetic.main` + 布局文件 + *】的格式。  

然后，你可以这样来使用：

```
btn_picture.solid = ***
```
看到没，不用**findViewById()**，不用**ButterKnife** 。真是太棒了！  

当然，Anko还有许多有趣的特性，比如可以通过这个函数`dip()`，来快速的将dp转为px，你都不用自己去封装。相关资料可以到这里查看：[Kotlin/anko【https://github.com/Kotlin/anko#which-anko-libraries-are-available-and-1which-do-i-need】](https://github.com/Kotlin/anko#which-anko-libraries-are-available-and-1which-do-i-need)。  

# 遇见《伴》
目前，**《伴》** 共有4个模块：新闻头条，微信精选，轻松一刻，星座运势。她们被打造来陪伴寂寞的你（如果你寂寞的话）。下面CoorChice展示一下《伴》的大致面貌吧。 

## 新闻头条
将世界收入你的口袋中！足不出户而知天下事，大概说的就是这样吧。  

![0](http://ogemdlrap.bkt.clouddn.com/%E4%BC%B4-00.gif)   

  

## 微信精选
每日微信精选文章让你看到微信中不一样的世界。  
 
![1](http://ogemdlrap.bkt.clouddn.com/%E4%BC%B4-1.gif)

## 轻松一刻
随身的图文笑话，让你走到哪都是最有幽默感的那一个。  

![image](http://ogemdlrap.bkt.clouddn.com/%E4%BC%B4-2.gif)

## 星座运势
让你每天都可以了解自己的星座动态。  

![image](http://ogemdlrap.bkt.clouddn.com/%E4%BC%B4-3.gif)  

# 总结
> - 抽出空余时间写文章分享需要动力，还请各位看官动动小手 **【点个赞】**，给CoorChice点鼓励
> - CoorChice一直在不定期的创作新的干货，想要上车只需进到【个人主页】点个关注就好了哦。发车喽～

本篇CoorChice分享了自己的Kotlin项目《伴》，如果你现在任然感谢兴趣的话，下面是项目的地址：   

>  [《伴》【https://github.com/chenBingX/Ban】](https://github.com/chenBingX/Ban)  

或者，你可以在这个地址下载解压安装体验《伴》。    
> [《伴》http://ogemdlrap.bkt.clouddn.com/app-debug.apk.zip](http://ogemdlrap.bkt.clouddn.com/app-debug.apk.zip)

## 喜欢的话，记得顺手给个star，点个赞，鼓励下CoorChice哦！







