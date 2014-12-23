CarMonitor
==========
/**
* by YuYang 
* 2014/12/19
**/
新部署方法：
1.从github上clone源码: git clone git@github.com:yijiezhen/CarMonitor.git（太慢就采用http协议）
++++++
2014/12/23  从自己的repos clone   git clone git@github.com:ichimaruGin/CarMonitor.git

2.首先找到CarMonitorCore/db/latest.sql，使用下面的语句代替原来的新增User，授权操作
#grant all privileges on `car_monitor_db`.* to `car_db_user`@`localhost` identified by 'password';
#flush privileges; 
用root身份登录数据库，source file.

3.CarMonitorCore是可以直接运行的。根目录下面的startNio.sh 或者 startInBackend.sh（后台运行）

4.由于CarMonitorWeb要用到CarMonitorCore中的类，故将CarMonitorCore打包。打包方式，进入CarMonitorCore目录，mvn install.
成功后会在maven的repos目录下面出现这个jar.

5.CarMonitorWeb，放到eclipse中需要引入gwt的几个jar包，同时引入4中的CarMonitorCore的jar包；用ant打成war包
由于师兄的环境与我不一样，所以修改build.xml文件中的路径。然后在CarMonitorWeb目录下面ant war即可，生成CarMonitor.war。

6.将CarMonitor.war送到服务器的/home/sxfj/java/CarMonitor/war目录下面，执行：
rm -rf CarMonitor;
unzip CarMonitor.war -d CarMonitor;即可

http://115.239.182.18:9090/CarMonitor/

++++++
2014/12/23
默认的协议中不能用16进制数据，而是用Long的字符串来代替，再用Long.parse(String s)转换
已在EventProcessor类中用Long.parse(String s，int redix)替代

