package jakarta.tutorial.dukestutoring.web.util;

import java.util.HashMap;
import java.util.Map;

import jakarta.faces.context.FacesContext;


public class EntityConverter {

    private static final String key = "dukestutoring.web.util.EntityConverter";

    public EntityConverter() {
    }

    protected Map<String, Object> getViewMap(FacesContext context) {
        Map<String, Object> viewMap = context.getViewRoot().getViewMap();
        @SuppressWarnings({"unchecked", "rawtypes"}) Map<String, Object> idMap = (Map) viewMap.get(
                key);
        if (idMap == null) {
            idMap = new HashMap<>();
            viewMap.put(key, idMap);
        }
        return idMap;
    }
}
