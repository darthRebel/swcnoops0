package swcnoops.server.game;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameConstants {
    public Integer crystals_speed_up_coefficient;
    public Integer crystals_speed_up_exponent;
    public Integer crystals_speed_up_prestige_coefficient;
    public Integer crystals_speed_up_prestige_exponent;
    public Integer crystals_speed_up_building_lever_percentage;
    public Integer coef_exp_accuracy;

    public static GameConstants createFromBaseJson(List<Map<String, String>> gameConstants) throws Exception {
        GameConstants constants = new GameConstants();
        constants.setConstants(gameConstants);
        return constants;
    }

    private void setConstants(List<Map<String, String>> gameConstants) throws Exception {
        Map<String, Map<String, String>> gameConstantMap = createMap(gameConstants);

        Class<?> clazz = this.getClass();
        for (Field field : clazz.getFields()) {
            String fieldName = field.getName();
            if (gameConstantMap.containsKey(fieldName)) {
                set(field, this, gameConstantMap.get(fieldName).get("value"));
            }
        }
    }

    private void set(Field field, GameConstants config, String property) throws Exception {
        Object value = convertToObject(property, field.getType());
        field.set(config, value);
    }

    private Object convertToObject(String property, Class<?> type) {
        if (type == Integer.class) {
            return Integer.valueOf(property);
        } else if (type == String.class) {
            return property;
        }

        return null;
    }

    private Map<String, Map<String, String>> createMap(List<Map<String, String>> gameConstants) {
        Map<String, Map<String, String>> map = new HashMap<>();

        for (Map<String, String> constant : gameConstants) {
            map.put(constant.get("uid"), constant);
        }
        return map;
    }
}
