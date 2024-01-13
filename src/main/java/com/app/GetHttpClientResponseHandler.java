package com.app;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.json.JSONObject;


class GetHttpClientResponseHandler implements ResponseHandler<JSONObject> {
    public JSONObject handleResponse(final HttpResponse response) throws IOException {
        int status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
            HttpEntity entity = response.getEntity();
            if(entity == null) {
                return new JSONObject();
            } else {
                String jsonString = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                return new JSONObject(jsonString);
            }

        } else {
            return new JSONObject();
        }
    }
}
