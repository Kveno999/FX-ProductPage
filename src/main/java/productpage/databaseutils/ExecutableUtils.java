package productpage.databaseutils;

import lombok.experimental.UtilityClass;

import java.sql.*;
import java.util.Collections;
import java.util.List;

import static productpage.databaseutils.MapperUtils.mapResultSetToModelList;

@UtilityClass
public class ExecutableUtils {

    public static <T> List<T> select(String query, Class<T> pojoClass) {
        try (Statement statement = ConnectionManager.getInstance().getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            return mapResultSetToModelList(resultSet, pojoClass);
        } catch (SQLException e) {
            System.out.println("Exception while selecting table");
        }
        return Collections.emptyList();
    }

}
