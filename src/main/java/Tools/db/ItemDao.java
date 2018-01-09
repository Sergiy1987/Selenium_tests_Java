package Tools.db;

import java.util.List;

public interface ItemDao {
    public static final String SQL_FIND_ALL = "select * from " + Item.TABLE_NAME;
    public static final String SQL_INSERT = "insert into " + Item.TABLE_NAME + " (" + Item.NAME_COLUMN + ", " + Item.COLOR_COLUMN + ") values (?, ?)";
    public static final String SQL_UPDATE = "update " + Item.TABLE_NAME + " set " + Item.NAME_COLUMN + " where " + Item.ID_COLUMN + " = ?";
    public static final String SQL_DELETE = "delete from " + Item.TABLE_NAME + " where " + Item.ID_COLUMN + " = ?";
    public static final String SQL_TRUNCATE ="TRUNCATE table " + Item.TABLE_NAME;
    public List<Item> findAll();
    public void insert(Item item);
    public void update(Item item);
    public void delete(Item item);
    public void truncate(Item item);
}