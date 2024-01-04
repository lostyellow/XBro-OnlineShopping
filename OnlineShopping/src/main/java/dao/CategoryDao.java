package dao;

public interface CategoryDao {
public int findSubIDBySubName(String subCategory);
public int findPSIDByPIDAndSID(int parentID, int subID);
}
