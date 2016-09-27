package webshop;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Amir i Masha on 2016.09.20..
 */
public interface ReportDao {
    public Map<Integer, Float> getSalesWithSum() throws SQLException;

    public Map<Integer, Float> getSalesByUser(User user) throws SQLException;
}
