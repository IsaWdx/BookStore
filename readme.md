# 一家教育书店

## 功能

+ 注册和登录(Registration)
Customer：注册支持中文用户名&真名，输入用户名后立即查重，密码SHA1加密。注册之后立刻以当前用户名登录。注册后可用密码和用户名登录。

Administrator：用户可以以管理员身份登录，可以与用户身份同时存在，并同时登出。不可以注册，后台更改。

+ 购书(Ordering)及购书推荐(buying suggestions)
在以Customer权限登录时可以执行。购买时输入购买种类数，及每本书的isbn，购买数量，可以购买图书。若输入有误，则重新输入。添加order及orderbooks图书购买记录，计算价格，更改book库存。

购买图书后，自动弹出购买过本订单图书的其他读者所购买的当前读者未购买的书。

+ 新书到货(New Book)
Administrator权限下登录可以执行。检查isbn码长度，输入和图书有关的所有数据，有误则重新输入。

+ 增加库存(New book arrival)
Administrator权限下登录可以执行。

+ 用户反馈(Feedback Recordings)
Customer权限下登录可以执行，输入isbn，打分1-10分，以及可以选择的留言，输入有误则重新输入。用户不购买此书也可以反馈。

+ 反馈打分(Usefulness Ratings)
Customer权限下登录可以执行。Java限制用户对自己留言的打分，打分0-2分，若有误重新输入。
+ 用户信任(Trust Recordings)
用户不能给自己信任，否则重新输入，-1表示不相信，1表示相信。

+	图书浏览(Book Browsing)
不要求登录。用户被要求输入可以插入sql语句的查询条件。如title like"%cho%" and subject="child" and publisher="new" and (author="Li" or author="Wang")。可以输入a,b,c来选择三种排列顺序。

+ 有效反馈(Useful Feedbacks)
    不要求登录。输入isbn查询即可，用户可以选择1-10条记录。
+ 购书推荐(Buying Suggestions)
合并到功能2。

+ 二度分离(2-degree Separation)
不要求登录。输入两个作者名即可，如’施伯乐’，’汪卫’显示度数为1。

+ 历史数据(Statistics)
管理员权限登录。输入三种选择及m可以查看。

+ 用户奖励(User Award)
管理员权限登录。输入两种选择及m可以查看。


##运行

####命令行

1. javac fudandb/*.java

2. In windows system: java -cp "./mysql.jar;." fudandb.BookStore   In Linux/Unix system: java -cp ./mysql.jar:. fudandb.BookStore

3. Or you can use an IDE (such as Ecilipse) as we have demoed in class. Remeber to add mysql.jar as an external jar to your project's Java build path.

#### JSP (Servlet: TOMCAT)

我酷炫的JSP的网页部分找不到了，非常难过。