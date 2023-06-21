package productpage.databaseutils;

import lombok.experimental.UtilityClass;
import productpage.annotations.Column;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class MapperUtils {

    public static <T> List<T> mapResultSetToModelList(ResultSet resultSet, Class<T> clazz) {
        List<T> pojoList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                T pojoInstance = mapResultSetToModel(resultSet, clazz);
                pojoList.add(pojoInstance);
            }
        } catch (SQLException e) {
            System.out.printf("Exception while mapping ResultSet to Model:: %s%n", e.getMessage());
        }
        return pojoList;
    }

    public static <T> T mapResultSetToModel(ResultSet resultSet, Class<T> clazz) throws SQLException {
        T pojoInstance;
        try {
            pojoInstance = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new SQLException("Failed to create instance of POJO class", e);
        }

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            Object columnValue = resultSet.getObject(i);

            try {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(Column.class)) {
                        Column columnAnnotation = field.getAnnotation(Column.class);
                        String annotationValue = columnAnnotation.value();
                        if (annotationValue.equalsIgnoreCase(columnName)) {
                            field.setAccessible(true);
                            field.set(pojoInstance, columnValue);
                            break;
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                throw new SQLException("Failed to map column '" + columnName + "' to POJO field", e);
            }
        }
        return pojoInstance;
    }

}
