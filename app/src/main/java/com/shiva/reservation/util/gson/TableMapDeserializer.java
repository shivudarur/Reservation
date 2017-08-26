package com.shiva.reservation.util.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.shiva.reservation.model.TableMap;

import java.lang.reflect.Type;

/**
 * Created by shivananda.darura on 25/08/17.
 */

public class TableMapDeserializer implements JsonDeserializer<TableMap> {
    @Override
    public TableMap deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jdc)
        throws JsonParseException {
        return new TableMap(jsonElement.getAsBoolean());
    }
}

