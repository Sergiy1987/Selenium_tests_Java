package Tools.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    private DB db;
    public ItemDaoImpl() {
        this.db = new DB();
    }

    public List<Item> findAll() {
        List<Item> result = new ArrayList<Item>();
        Connection connection = null;
        try {
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt(Item.ID_COLUMN));
                item.setNameColumn(rs.getString(Item.NAME_COLUMN));
                item.setColumn(rs.getString(Item.COLOR_COLUMN));
                result.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeConnection(connection);
        }
        return result;
    }

     public void insert(Item item) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getNameColumn());
            statement.setString(2, item.getColor());
            statement.execute();

            ResultSet generatedkeys = statement.getGeneratedKeys();
            if (generatedkeys.next()) {
                item.setId(generatedkeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeConnection(connection);
        }
    }

    public void update(Item item) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, item.getNameColumn());
            statement.setInt(2, item.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeConnection(connection);
        }
    }

    public void delete(Item item) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, item.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeConnection(connection);
        }
    }
    public void truncate(Item item) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_TRUNCATE);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeConnection(connection);
        }
    }
}