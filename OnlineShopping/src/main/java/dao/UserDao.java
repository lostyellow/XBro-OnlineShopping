package dao;

import bean.*;

public interface UserDao{
/*对Users表的操作*/
public void businessRegister(User user);//seller注册功能
public void userRegister(User user);//user注册
public String onlyOneUser();//判断是否只有一个商家
public UserList findAll();//遍历数据库查找账户密码
public String checkLogin(String username, String password);//username不能重复
public int findUser_ID(User user);//查找卖家ID
public User findUserByID(int userID);//通过ID查找用户
public void changePwd(int userId, String newPwd);//查看旧密码是否正确
public String findUser_PWD(String realname, String id_card);//查找卖家密码
public UserList findBuyer_information();//查看所有注册用户信息

}
