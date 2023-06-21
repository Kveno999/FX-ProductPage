package productpage.generalutils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;

@UtilityClass
public class PropertiesUtils {

    public static <T> T readJson(String filePath, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(filePath), clazz);
        } catch (IOException e) {
            System.out.println("Exception while reading json as model");
        }
        return null;
    }
}
