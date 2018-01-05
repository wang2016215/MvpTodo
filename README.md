# MvpTodo
基础mvpDemo 参考Googlemvp


Contract 作为契约类，能够更清晰的看到在Presenter层和View层中有哪些功能，方便我们以后的维护。
activity ，Fragment 作为V，负责的就是接收数据，更新界面。
model M，负责的是对数据的处理和回调，，而且M中没有V的引用，而和P的联系则是通过callback，可以再看下官方给的图。
Presenter 作为P，V向它请求数据，然后P再向M请求数据，通过回调得到数据之后在调用V进行界面的更新。

