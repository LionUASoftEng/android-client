package org.npc.lion_client_ui.api.interfaces;

import org.json.JSONObject;

public interface LoadFromJsonInterface<T> {
    T loadFromJson(JSONObject rawJsonObject);
}
