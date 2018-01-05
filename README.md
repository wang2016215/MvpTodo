# MvpTodo
基础mvpDemo 参考Googlemvp




## MVP模式

| 角色 | 说明 |
|:------------- |:------------- |
| Model | 主要做一些数据处理, 网路请求。Presenter 需要通过 Model 层存取、获取数据，Model是封装了数据库 Dao 层或者网络获取数据的角色，或者两种数据获取方式的集合。 |
| Presenter | 交互中间人，核心逻辑，处理 View 的业务逻辑，沟通 View 和 Model 的桥梁，Presenter 持有的 View、Model 引用都是抽象，它从 Model 层检索数据后返回给 View 层，使得 View 和 Model 没有耦合，也将业务逻辑从 View 层抽取出来，经常会执行耗时操作。 |
| View | 用户界面，Activity、Fragment 或者某个 View 控件，含有一个 Presenter 成员变量，通常 View 层需要实现一个逻辑接口，将 View 上的操作通过会转交给 Presenter 进行实现，最后 Presenter 调用 View 逻辑接口将结果返回给 View 元素。 |
|Contract| 契约类定义v和p的接口，能够更清晰的看到在Presenter层和View层中有哪些功能，方便我们以后的维护。|



![image](https://github.com/wang2016215/MvpTodo/blob/master/screenshots/20180105103858.png)

>## 为什么要用mvp？相比于mvc有哪些优点
>1.减少了Activity的职责，简化了Activity中的代码，将复杂的逻辑代码提取到了Presenter中进行处理。与之对应的好处就是，耦合度更低，

>2.Activity 代码变得更加简洁

  使用MVP之后，Activity就能瘦身许多了，基本上只有FindView、SetListener以及Init的代码。其他的就是对Presenter的调用，还有对View接口的实现。这种情形下阅读代码就容易多了，而且你只要看Presenter的接口，就能明白这个模块都有哪些业务，很快就能定位到具体代码。Activity变得容易看懂，容易维护，以后要调整业务、删减功能也就变得简单许多。

>3.方便进行单元测试
  一般单元测试都是用来测试某些新加的业务逻辑有没有问题，如果采用传统的代码风格（习惯性上叫做MV模式，少了P），我们可能要先在Activity里写一段测试代码，测试完了再把测试代码删掉换成正式代码，这时如果发现业务有问题又得换回测试代码，咦，测试代码已经删掉了！好吧重新写吧……
MVP中，由于业务逻辑都在Presenter里，我们完全可以写一个PresenterTest的实现类继承Presenter的接口，现在只要在Activity里把Presenter的创建换成PresenterTest，就能进行单元测试了，测试完再换回来即可。万一发现还得进行测试，那就再换成PresenterTest吧。

>4.避免 Activity 的内存泄露
  
