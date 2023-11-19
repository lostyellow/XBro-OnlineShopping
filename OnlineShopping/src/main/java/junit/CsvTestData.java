package junit;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import bean.Goods;

public class CsvTestData {
	private String Filename;
	
	public CsvTestData(String filename) {
		Filename = filename;// TODO Auto-generated constructor stub
	}
	
	public String getFilename() {
		return Filename;
	}

	public void setFilename(String filename) {
		Filename = filename;
	}
	
	public List<Goods> addGoodReader(){
		List<Goods> goodList = new ArrayList<Goods>();
		
		try {
			FileInputStream fis = new FileInputStream(Filename);
			InputStreamReader isr = new InputStreamReader(fis,StandardCharsets.UTF_8);
			CSVReader csvreader = new CSVReader(isr);
			
			Goods g = null;
			String[] nextLine;
			
			csvreader.readNext();
			
			while(null != (nextLine = csvreader.readNext())) {
				g = new Goods();
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
			FileInputStream fis = new FileInputStream(Filename);
			InputStreamReader isr = new InputStreamReader(fis,StandardCharsets.UTF_8);
			CSVReader csvreader = new CSVReader(isr);
			
			String[] nextLine;
			csvreader.readNext();
			
			while(null!=(nextLine = csvreader.readNext())) {
				String expected = nextLine[7];
				
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
		List<Goods> goodList = new ArrayList<Goods>();
		List<String> expectedList = new ArrayList<String>();
		
		CsvTestData td = new CsvTestData("src/main/java/example/上架商品单元测试用例.csv");
		
		goodList.addAll(td.addGoodReader());
		expectedList.addAll(td.expectedReader());
		
		for (Goods good : goodList) {
			System.out.println(good.toString());
		}
		for (String string : expectedList) {
			System.out.println(string);
		}
	}
}
