package com.example.demo.utilities;

import java.util.HashMap;
import java.util.Map;

public class MapStorage {
    Map<String, String> storage;

    public MapStorage() {
        storage = new HashMap<>();
    }
    public void add(final String key, final String value) {
        storage.put(key, value);
    }
    public String getValue(final String key) {
        return storage.get(key);
    }
}
