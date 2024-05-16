package com.ray.literalura.service;

public interface IConvertingData {
   <T> T getData(String json, Class<T> clase);
}
