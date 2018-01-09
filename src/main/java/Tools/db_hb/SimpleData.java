package Tools.db_hb;

import javax.persistence.*;

@Entity
@Table(name="SimpleData")
public class SimpleData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="UserData", nullable = false)
    private String UserData;
    @Column(name="Password", nullable = false)
    private String Password;

    public SimpleData() {}

    public SimpleData(String UserData, String Password) {
        this.UserData = UserData;
        this.Password = Password;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserData() {
        return UserData;
    }

    public void setUserData(String UserData) {
        this.UserData = UserData;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    @Override
    public String toString() {
        return "SimpleData {" +
                "id=" + id +
                ", UserData=" + UserData +
                ", Password=" + Password +
                '}';
    }
}
