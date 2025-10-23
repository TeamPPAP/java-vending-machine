package machine.vending.infrastructure;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonLoader<T> {
    private static final Gson gson = new Gson();
    private final Class<T> type;

    public JsonLoader(Class<T> type) {
        this.type = type;
    }

    public List<T> load(String path) {
        InputStream inputStream = loadResourceStream(path);
        return parseJsonToList(inputStream, path);
    }

    private InputStream loadResourceStream(String path) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(path);
        
        if (stream == null) {
            throw new IllegalArgumentException("파일을 찾을 수 없습니다: " + path);
        }
        
        return stream;
    }

    private List<T> parseJsonToList(InputStream inputStream, String path) {
        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
            Type listType = TypeToken.getParameterized(List.class, type).getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            throw new IllegalStateException("파일 파싱 중 오류가 발생했습니다: " + path, e);
        }
    }
}
