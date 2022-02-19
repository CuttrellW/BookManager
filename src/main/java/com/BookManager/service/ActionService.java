package com.bookmanager.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service

public class ActionService {

    public String getIntentName(String body) throws Exception {
        // convert body to JSONObject
        JSONObject bodyJsonObject = new JSONObject(body);
        // get query result
        JSONObject queryResulObject = bodyJsonObject.getJSONObject("queryResult");
        // retrieve intent object
        JSONObject intentObject = queryResulObject.getJSONObject("intent");
        // get display name
        return intentObject.get("displayName").toString();
    }
}
