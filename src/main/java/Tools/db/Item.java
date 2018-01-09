package Tools.db;

public class Item {
    public static final String TABLE_NAME = "test_DAO";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name_link";
    public static final String COLOR_COLUMN = "color";

    private int id;
    private String name_link;
    private String color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameColumn() {return name_link;}

    public void setNameColumn(String name_link) {
        this.name_link = name_link;
    }

    public String getColor() {
        return color;
    }

    public void setColumn(String color) {
        this.color = color;
    }

    public String toString() {
        return "Item[id=" + this.id +
                ", User_link=" + this.name_link +
                ", color=" + this.color +"]";
    }
}