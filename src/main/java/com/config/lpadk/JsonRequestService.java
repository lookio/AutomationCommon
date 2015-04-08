package com.config.lpadk;

import com.config.data.le.LeConfigData;
import com.liveperson.automation.usermanagement.entityoperations.CommonEntityOperations;
import com.sun.istack.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by asih on 06/04/2015.
 */
public class JsonRequestService {

    private static final JsonRequestService INSTANCE = new JsonRequestService();

    StringBuilder baseRequest;

    private JsonRequestService() {

    }

    public static JsonRequestService getInstance() {
        return INSTANCE;
    }

    String getAgentRequest(LeConfigData.Site.UsersData.CreateUser user, JSONArray skills){
        if(skills != null){
            return getRequest(user, skills);
        }else{
            return getRequest(user, null);
        }
    }

    @Nullable
    String getRequest(LeConfigData.Site.UsersData.CreateUser user, JSONArray skills){
        baseRequest = new StringBuilder().
                append("{displayName: ").append(user.getUser()).append(" ,").
                append("emailAddress: ").append(user.getEmail()).append(" ,").
                append("enabled: true ,").
                append("loginName: ").append(user.getEmail()).append(" ,").
                append("maxNumberOfChats: Unlimited ,").
                append("nickName: automation ,").
                append("password: ").append(user.getPassword()).append(" ,").
                append("permissionGroup: " + ConfigInjector.PermissionType.AGENT.getPermissionType() + " ,");
        if(skills != null) {
            baseRequest.append("skills: " + skills + "}");
        }else {
            baseRequest.append("skills: []}");
        }
        return baseRequest.toString();
    }

    String getId(String objKey, String expKey, String confType, CommonEntityOperations commonEntityOperations) throws Exception {
        String skillResponse;
        JSONArray jsonArray;
        JSONObject jsonObject;
        JSONObject exprObject;

        skillResponse = getSkillResponse(commonEntityOperations);

        String result= handleFirstSkill(expKey, getSkillResponse(commonEntityOperations));
        if (result != null) {
            return result;
        }else{
            jsonObject = new JSONObject(skillResponse);
            jsonArray = jsonObject.getJSONArray(confType);
            for (int i = 0; i < jsonArray.length(); i++) {
                exprObject = jsonArray.getJSONObject(i);
                if (exprObject.get(objKey).equals(expKey))
                    return exprObject.get("id").toString();
            }
        }
        return null;
    }

    private String handleFirstSkill(String expKey, String skillResponse){
        if(expKey.equalsIgnoreCase("mobile")){
            return skillResponse.substring(skillResponse.indexOf("id") + 5, skillResponse.indexOf("\",\"name\":\"mobile"));
        }
        return null;
    }

    private String getSkillResponse(CommonEntityOperations commonEntityOperations){
        return commonEntityOperations.getEntity(commonEntityOperations.getResourceUrl().substring(0, commonEntityOperations.getResourceUrl().indexOf('?')));
    }

}
