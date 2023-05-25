package com.example.ksat.service.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.math.BigDecimal;

public class MoneyDeserialize extends StdDeserializer<BigDecimal> {

    public MoneyDeserialize() {
        this(null);
    }

    public MoneyDeserialize(Class<?> t) {
        super(t);
    }

    @Override
    public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        var amount = new BigDecimal(jsonParser.getText());

        if (amount.scale() != 2) {
            throw new IllegalArgumentException("Given amount is: " + jsonParser.getText());
        }

        return amount;
    }
}
