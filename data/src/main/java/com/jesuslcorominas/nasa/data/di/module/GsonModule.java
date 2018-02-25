package com.jesuslcorominas.nasa.data.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.jesuslcorominas.nasa.common.util.DateUtil;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.lang.reflect.Type;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Modulo de Dagger 2 que provee de las dependencias necesarias para configurar correctamente Gson
 * y el parseo de los resultados de las peticiones remotas
 *
 * @author Jesús López Corominas
 */
@Module
public class GsonModule {

    /**
     * Provee del objeto Gson a utilizar en el parseo de las respuestas de las peticiones remotas
     *
     * @param dateTimeJsonSerializer   El serializador de {@link DateTime} a String
     * @param dateTimeJsonDeserializer El deserializador de String a {@link DateTime}
     * @return EL objeto Gson correctamente configurado
     */
    @Singleton
    @Provides
    public Gson provideGson(JsonSerializer<DateTime> dateTimeJsonSerializer, JsonDeserializer<DateTime> dateTimeJsonDeserializer) {
        return new GsonBuilder().
                serializeNulls().
                setPrettyPrinting().
                registerTypeAdapter(DateTime.class, dateTimeJsonDeserializer).
                registerTypeAdapter(DateTime.class, dateTimeJsonSerializer).
                create();
    }

    /**
     * Provee del serializador de {@link DateTime} a String
     *
     * @return El serializador de {@link DateTime} a String
     */
    @Singleton
    @Provides
    JsonSerializer<DateTime> provideDateTimeJsonSerializer() {
        return new JsonSerializer<DateTime>() {
            @Override
            public JsonElement serialize(DateTime src, Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(src.toString(DateUtil.DATE_FORMAT));
            }
        };
    }

    /**
     * Provee del deserializador de String a {@link DateTime}
     *
     * @return el deserializador de String a {@link DateTime}
     */
    @Singleton
    @Provides
    JsonDeserializer<DateTime> provideDateTimeJsonDeserializer() {
        return new JsonDeserializer<DateTime>() {
            @Override
            public DateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                if (json.getAsJsonPrimitive().isNumber()) {
                    return new DateTime(json.getAsLong());
                }

                return DateTime.parse(json.getAsString(), DateTimeFormat.forPattern(DateUtil.DATE_FORMAT));
            }
        };
    }

}
