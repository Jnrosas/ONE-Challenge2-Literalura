package com.ray.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumingAPI {
   public String getData(String url) {
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();
      HttpResponse response = null;
      try {
         response = client.send(request, HttpResponse.BodyHandlers.ofString());
      } catch (IOException | InterruptedException e) {
         throw new RuntimeException(e);
      }
      String json = (String) response.body();
      return json;
   }
}
