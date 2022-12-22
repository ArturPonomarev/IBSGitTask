package src.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtil {
    public static <T> T DeserializeFile(String filepath, Class<T> tClass) throws IOException {
        return new ObjectMapper().readValue(new File(filepath), tClass);
    }
}
