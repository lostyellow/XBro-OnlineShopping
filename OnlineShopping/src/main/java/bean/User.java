package bean;

public class User {
    private String UserName;
    private String Password;
    private String Name;
    private String Id_card;
    private String Sex;
    private String Tele;
    private String E_mail;
    private String Birth;
	private String UserName;
	private String Password;
	private String Name;
	private String Id_card;
	private String Sex;
	private String Tele;
	private String E_mail;
	private String Birth;
	private String Address;
	private String user_group;

	public String getUser_group() {
		return user_group;
	}

	public void setUser_group(String user_group) {
		this.user_group = user_group;
	}

    public User() {
        // TODO Auto-generated constructor stub
    }

    public User(String UserName, String Password) {
        this.UserName = UserName;
        this.Password = Password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId_card() {
        return Id_card;
    }

    public void setId_card(String id_card) {
        Id_card = id_card;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getTele() {
        return Tele;
    }

    public void setTele(String tele) {
        Tele = tele;
    }

    public String getE_mail() {
        return E_mail;
    }

    public void setE_mail(String e_mail) {
        E_mail = e_mail;
    }

    public String getBirth() {
        return Birth;
    }

    public void setBirth(String birth) {
        Birth = birth;
    }

	public String getAddress() {
		return Address;
	}

	public void setAddress(String adress) {
		Address = adress;
	}


}
