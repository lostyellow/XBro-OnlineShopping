package auto;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import bean.Good;
import bean.User;

public class CsvTestData {
	private String fileName;
	
	public CsvTestData() {
		// TODO Auto-generated constructor stub
	}

	public CsvTestData(String fileName) {
		super();
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	List<User> registerTestData(){
		List<User> ul = new ArrayList<User>();
		try {
			FileInputStream fis = new FileInputStream(fileName);
			InputStreamReader isr = new InputStreamReader(fis,StandardCharsets.UTF_8);
			CSVReader csvreader = new CSVReader(isr);
			
			User u = null;
			String[] nextLine;
			csvreader.readNext();
			
			while(null != (nextLine = csvreader.readNext())) {
				u = new User();
				String name = nextLine[0];
				String id_card = nextLine[1];
				String tele = nextLine[2];
				String e_mail = nextLine[3];
				String expected = nextLine[4];
				
				u.setName(name);
				u.setId_card(id_card);
				u.setTele(tele);
				u.setE_mail(e_mail);
				
				ul.add(u);
			}
			isr.close();
			fis.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ul;
	} 
	
	public List<Good> addGoodReader(){
		List<Good> goodList = new ArrayList<Good>();
		
		try {
			FileInputStream fis = new FileInputStream(fileName);
			InputStreamReader isr = new InputStreamReader(fis,StandardCharsets.UTF_8);
			CSVReader csvreader = new CSVReader(isr);
			
			Good g = null;
			String[] nextLine;
			
			csvreader.readNext();
			
			while(null != (nextLine = csvreader.readNext())) {
				g = new Good();
				String itemName = nextLine[0];
				String itemDescription = nextLine[1];
				String imgURL = nextLine[2];
				String number = nextLine[3];
				String date = nextLine[4];
				Boolean isPres;
				
				if(nextLine[5].equals("true")) {
					isPres = true;
				}else {
					isPres = false;
				}
				Float price = Float.parseFloat(nextLine[6]);
				String expected = nextLine[7];
				
				g.setItemName(itemName);
				g.setItemDescription(itemDescription);
				g.setImgURL(imgURL);
				g.setNumber(number);
				g.setDate(date);
				g.setIsPres(isPres);
				g.setIsFrozen(false);
				g.setPrice(price);
				
				goodList.add(g);
			}
			isr.close();
			fis.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return goodList;
	}
	
	public List<String> expectedReader(){
		List<String> expectedList = new ArrayList<String>();
		
		try {
			FileInputStream fis = new FileInputStream(fileName);
			InputStreamReader isr = new InputStreamReader(fis,StandardCharsets.UTF_8);
			CSVReader csvreader = new CSVReader(isr);
			
			String[] nextLine;
			String[] header = csvreader.readNext();
			int columnIndex = -1;
			
			for(int i = 0;i<header.length;i++) {
				if (header[i].equals("expected")) {
		            columnIndex = i;
		            break;
		        }
			}
			
			while(null!=(nextLine = csvreader.readNext())) {
				String expected = nextLine[columnIndex];
				
				expectedList.add(expected);
			}
			isr.close();
			fis.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return expectedList;
	}
	
	public static void main(String[] args) {
		List<Good> goodList = new ArrayList<Good>();
		List<String> expectedList = new ArrayList<String>();
		
		CsvTestData td = new CsvTestData("src/main/java/example/上架商品单元测试用例.csv");
		
		goodList.addAll(td.addGoodReader());
		expectedList.addAll(td.expectedReader());
		
		for (Good good : goodList) {
			System.out.println(good.toString());
		}
		for (String string : expectedList) {
			System.out.println(string);
		}
	}
}
