package com.config.lpadk;

import com.config.data.le.LeConfigData;
import com.liveperson.automation.usermanagement.entityoperations.CommonEntityOperations;
import com.sun.istack.Nullable;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by asih on 06/04/2015.
 */
public class JsonService {

    private static final JsonService INSTANCE = new JsonService();
    private static final Logger logger = Logger.getLogger(JsonService.class);


    StringBuilder baseRequest;

    private JsonService() {

    }

    public static JsonService getInstance() {
        return INSTANCE;
    }

    String getAgentRequest(LeConfigData.Site.UsersData.CreateUser user, JSONArray skills){
            return getRequest(user, skills);
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
                append("permissionGroup: " + PermissionType.AGENT.getPermissionType() + " ,");
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

    boolean handleResponse(HttpResponse response, String successMsg, String failMsg) throws IOException {
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
            logger.info(successMsg);
            ConfigInjector.getInstance().getCreator().updateConfigurationInSite();
            return true;
        } else {
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            logger.info(responseString);
            if (responseString.contains("unique")) {
                logger.warn(failMsg);
                return true;
            } else {
                return false;
            }
        }
    }

}
