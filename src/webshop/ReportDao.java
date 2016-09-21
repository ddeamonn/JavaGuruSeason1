package webshop;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Amir i Masha on 2016.09.20..
 */
public interface ReportDao {
    public List getSalesWithSum() throws SQLException;
}
