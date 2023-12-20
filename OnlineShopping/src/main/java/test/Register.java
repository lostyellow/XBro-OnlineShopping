package test;

import java.util.regex.Pattern;

public class Register {
	public String register(String name, String id_card, String tele, String e_mail) {
		// TODO Auto-generated method stub
		try {
			if(telrule(tele)&&idrule(id_card)&&namerule(name)&&e_mailrule(e_mail)) {
				return "allsuccess";
			}else if(telrule(tele)&&idrule(id_card)&&e_mailrule(e_mail)) {
				return "namefail";
			}else if(telrule(tele)&&e_mailrule(e_mail)) {
				return "idfail";
			}else if(e_mailrule(e_mail)) {
				return "telfail";
			}else {
				return "fail";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}


	public boolean telrule(String tel) {
		if(tel.length() == 11) {
			String telRegex="^1(3[0-9]|5[0-3,5-9]|7[1-3,5-8]|8[0-9])\\d{8}$";
			Pattern pattern = Pattern.compile(telRegex);
			return pattern.matcher(tel).matches();
		}else {
			return false;
		}
	}

	public boolean idrule(String id) {
		String idRegex18 ="^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
		Pattern pattern = Pattern.compile(idRegex18);
		return pattern.matcher(id).matches();

	}

	public boolean namerule(String name) {
		String nameRegex ="^[\\u4e00-\\u9fa5]{2,4}$";
		Pattern pattern = Pattern.compile(nameRegex);
		return pattern.matcher(name).matches();
	}

	public boolean e_mailrule(String e_mail) {
	    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";


	    Pattern pattern = Pattern.compile(emailRegex);

	    return pattern.matcher(e_mail).matches();
	}
}
