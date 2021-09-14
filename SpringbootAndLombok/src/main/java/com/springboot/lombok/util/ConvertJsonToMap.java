package com.springboot.lombok.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonObject;



public class ConvertJsonToMap {

	
    //public static Map<String, Object> jsonToMap(JsonObject json) {
	public Map<String, Object> jsonToMap(JsonObject json) {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JsonObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }
    
    //public static Map<String, Object> toMap(JsonObject object) throws JsonException {
	public Map<String, Object> toMap(JsonObject object) throws JsonException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keySet().iterator();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JsonArray) {
                value = toList((JsonArray) value);
            }

            else if(value instanceof JsonObject) {
                value = toMap((JsonObject) value);
            }
            map.put(key, value);
        }
        return map;
    }
    
    //public static List<Object> toList(JsonArray array) {
	public List<Object> toList(JsonArray array) {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.size(); i++) {
            Object value = array.get(i);
            if(value instanceof JsonArray) {
                value = toList((JsonArray) value);
            }

            else if(value instanceof JsonObject) {
                value = toMap((JsonObject) value);
            }
            list.add(value);
        }
        return list;
    }
	
}
