package webshop;

import java.sql.SQLException;

/**
 * Created by Amir i Masha on 2016.10.04..
 */
public interface UserUI {
    public void showFormUser() throws SQLException;

    public User getCurrentUser();
}
