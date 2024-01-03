package bean;

/*
 * 仅作为数据库的模拟
 * 存储静态数据
 * 做一些静态的数据库方法
 * 
 * **/
public class Database {
    private static String UserName = "TestStore";
    private static String Password = "123456abc";

    public String itemName;
    public String itemDiscription;
    public String imgURL;
    public Long price;

    public static void initUsers() {
        User su = new User(UserName, Password);
        UserList.users.add(su);
    }
}
