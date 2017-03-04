package General;

import java.sql.ResultSet;

/**
 * Created by Tymek on 04.03.2017.
 */
public class ResultSetStorage {
    private ResultSet set;

    public ResultSetStorage(ResultSet set){
        this.set = set;
    }

    public ResultSet getSet() {
        return this.set;
    }
}
