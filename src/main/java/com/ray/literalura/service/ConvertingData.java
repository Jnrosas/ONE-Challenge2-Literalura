package com.ray.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertingData implements IConvertingData{
   private ObjectMapper objectMapper = new ObjectMapper();

   @Override
   public <T> T getData(String json, Class<T> clase) {
      try {
         return objectMapper.readValue(json.toString(), clase);
      } catch (JsonProcessingException e) {
         throw new RuntimeException(e);
      }
   }
}
