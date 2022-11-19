# threebody
练手的 Java 项目

## 如何运行

编译全部源代码文件，然后命令行运行`java ThreeBody`即可

## 思考

这个项目是我在看了《代码整洁之道》后，对之前 C++ 项目的重构。其中的一点心得在这里说两句。

0. Vim 不装插件是真的不好用呜呜呜，尤其是文件树插件，为了切换各个文件，鬼知道我小拇指受了多少罪呜呜呜
0. 请不要被很长的标识符吓到，这是 Uncle Bob 提倡的，在《代码整洁之道》里面，标识符的长度有很多都是 20 个字符左右
0. `GameManager.java:26`，这里的方法是后来重构过的，为此我新增了一个`GravitationCalculator`类来取消`Body`类的多个函数间的丑陋的多层传参，保持了代码的整洁
0. `GameManager.java:8`，这里我本来是分别调用了三个`init-()`方法，但是这样写就遮掩了`DataGenerator.get8ShapedStableStructure()`和`GravitationCalculator()`方法之间的时序耦合，不利于后续的升级迭代，故此处把原来在`init-()`方法中的方法体 copy 到了`GameManager()`下
0. 在编写`Body.equals()`时，遇到了浮点数判定问题。为了最大化代码复用，这里新增了一个`DoubleEqualJudger`类，并提供了两个方法以判断浮点数是否相等
0. 这里还有许多代码没有重构：`GameWindow.java`、`ThreeBody.java`、`Vector2.java`，欢迎后人提交pr
