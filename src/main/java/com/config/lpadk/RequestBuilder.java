package com.config.lpadk;

import com.config.data.le.LeConfigData;
import com.sun.istack.Nullable;
import org.json.JSONArray;

/**
 * Created by asih on 06/04/2015.
 */
public class RequestBuilder {

    static StringBuilder baseRequest;


    static String getAgentRequest(LeConfigData.Site.UsersData.CreateUser user, JSONArray skills){
        if(skills != null){
            return getRequest(user, skills);
        }else{
            return getRequest(user, null);
        }
    }

    @Nullable
    private static String getRequest(LeConfigData.Site.UsersData.CreateUser user, JSONArray skills){
        baseRequest = new StringBuilder().
                append("{displayName: ").append(user.getUser()).append(" ,").
                append("emailAddress: ").append(user.getEmail()).append(" ,").
                append("enabled: true ,").
                append("loginName: ").append(user.getEmail()).append(" ,").
                append("maxNumberOfChats: Unlimited ,").
                append("nickName: automation ,").
                append("password: ").append(user.getPassword()).append(" ,").
                append("permissionGroup: " + ConfigInitializer.PermissionType.AGENT.getPermissionType() + " ,");
        if(skills != null) {
            baseRequest.append("skills: " + skills + "}");
        }else {
            baseRequest.append("skills: []}");
        }
        return baseRequest.toString();
    }

}
//"{displayName: " + user.getUser() + " ,emailAddress: qa@qa.com ,enabled: true ,loginName: " + user.getEmail() + " ,maxNumberOfChats: Unlimited ,nickName: automation ,password: 12345678 ,permissionGroup: " + PermissionType.AGENT.getPermissionType() + " ,skills: []}"