javac -encoding utf-8 -classpath %CATALINA_HOME%/../lib/jsp-api.jar;%CATALINA_HOME%/../lib/servlet-api.jar;./webapp/WEB-INF/lib/jsmartcom_zh_CN.jar;./webapp/WEB-INF/lib/jstl.jar;./webapp/WEB-INF/lib/slf4j-api-2.0.3.jar;./webapp/WEB-INF/lib/slf4j-log4j12-1.7.21.jar;./webapp/WEB-INF/lib/sqlite-jdbc-3.43.2.2.jar;./webapp/WEB-INF/lib/standard.jar; @source.txt -Xlint:unchecked -d ./webapp/WEB-INF/classes
cd webapp
jar cvf ../OnlineShopping.war .
cd ..