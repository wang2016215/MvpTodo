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
