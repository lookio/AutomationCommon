package com.agent;

import java.util.Properties;

/**
 * Class Info:
 * <p/>
 * User: ilanm
 * Date: 6/24/14
 * Time: 9:35 AM
 */
public class PreConfiguredSite {


    private String siteId;
    private String siteUser;
    private String sitePass;
    private String domain ;
    private String siteURL;
    private String protocol = "https";
    private String emailAddress;
    private String appKey;
    private String appSecret;
    private String tokenKey;
    private String tokenSecret;
    private String host;
    private String server;


    public PreConfiguredSite(Properties props, String serverName) {
        this.siteId = props.getProperty("site.id");
        this.siteUser = props.getProperty("user.email");
        this.sitePass = props.getProperty("user.password");
        this.domain = props.getProperty("site.domain");
        this.siteURL = props.getProperty("site.url");
        this.emailAddress = props.getProperty("site.email");
        this.appKey = props.getProperty("site.appKey");
        this.appSecret = props.getProperty("site.appSecret");
        this.tokenKey = props.getProperty("site.tokenKey");
        this.tokenSecret = props.getProperty("site.tokenSecret");
        this.host = props.getProperty("host");
        this.server = serverName;
    }

    public String getSiteUser() {
        return siteUser;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getSitePass() {
        return sitePass;
    }

    public String getDomain() {
        return domain;
    }

    public String getServer() {
        return server;
    }

    public String getSiteURL() {
        return siteURL;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public String getHost() {
        return host;
    }

    public String getSiteId() {
        return siteId;
    }

}
