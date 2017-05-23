package com.example.georgi.shop.Helpers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Georgi on 22-May-17.
 */

public class JsonDateDeserializer implements JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String s = json.getAsJsonPrimitive().getAsString();
        DateFormat df = new SimpleDateFormat("MMM dd, yyyy", Locale.UK);
        java.util.Date startDate;
        try {
            startDate = df.parse(s);
            Date d = new Date(startDate.getTime());
            return d;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
