package com.harium.annebeth.core.state;

import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class GameState {

    private Map<String, String> state = new HashMap<>();
    private final SharedPreferences preferences;

    public GameState(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void save(String key, String value) {
        state.put(key, value);
    }

    public String load(String key) {
        String value = state.get(key);
        if (value == null) {
            return "";
        }
        return value;
    }

    public void export() {
        SharedPreferences.Editor editor = preferences.edit();
        for (Map.Entry<String, String> entry: state.entrySet()) {
            editor.putString(entry.getKey(), entry.getValue());
        }
        editor.commit();
    }

}
