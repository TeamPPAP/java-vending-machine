package infrastructure;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonLoader<T> {
    private static final Gson gson = new Gson();
    private final Class<T> type;

    public JsonLoader(Class<T> type) {
        this.type = type;
    }

    public List<T> load(String path) {
        List<T> list = new ArrayList<>();
        try (InputStream inputStream = getInputStream(path)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found");
            }
            list = getObject(inputStream);
        } catch (IOException e) {
            System.out.println("Error while loading " + path + ": " + e.getMessage());
        }
        return list;
    }

    private InputStream getInputStream(String path) {
        return getClass().getClassLoader().getResourceAsStream(path);
    }

    private List<T> getObject(InputStream inputStream) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
            Type listType = TypeToken.getParameterized(List.class, type).getType();
            return gson.fromJson(reader, listType);
        }
    }
}
