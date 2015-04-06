
package com.config.data.le;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="site">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="create_site">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="isExtentExpiration" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                             &lt;element name="creationType">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="CreateNew"/>
 *                                   &lt;enumeration value="ConnectToExisting"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="users_data" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="create_user">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="userType">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;enumeration value="Administrator"/>
 *                                             &lt;enumeration value="Agent"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="skill" maxOccurs="unbounded">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;enumeration value="Manager"/>
 *                                             &lt;enumeration value="Sales"/>
 *                                             &lt;enumeration value="Support"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="modify_user">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="delete_user">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="deleteSkillData" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="deleteUserData" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="visitors_data" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="create_visitors">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="modify_visitors">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="delete_visitors">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="campaign_data" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="create_campaign">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="modify_campaign">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="delete_campaign">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "site"
})
@XmlRootElement(name = "le_config_data")
public class LeConfigData {

    @XmlElement(required = true)
    protected LeConfigData.Site site;

    /**
     * Gets the value of the site property.
     *
     * @return
     *     possible object is
     *     {@link LeConfigData.Site }
     *
     */
    public LeConfigData.Site getSite() {
        return site;
    }

    /**
     * Sets the value of the site property.
     *
     * @param value
     *     allowed object is
     *     {@link LeConfigData.Site }
     *
     */
    public void setSite(LeConfigData.Site value) {
        this.site = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="create_site">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="isExtentExpiration" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *                   &lt;element name="creationType">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="CreateNew"/>
     *                         &lt;enumeration value="ConnectToExisting"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="users_data" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="create_user">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="userType">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;enumeration value="Administrator"/>
     *                                   &lt;enumeration value="Agent"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="skill" maxOccurs="unbounded">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;enumeration value="Manager"/>
     *                                   &lt;enumeration value="Sales"/>
     *                                   &lt;enumeration value="Support"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="modify_user">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="delete_user">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="deleteSkillData" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="deleteUserData" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="visitors_data" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="create_visitors">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="modify_visitors">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="delete_visitors">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="campaign_data" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="create_campaign">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="modify_campaign">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="delete_campaign">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "createSite",
        "usersData",
        "visitorsData",
        "campaignData"
    })
    public static class Site {

        @XmlElement(name = "create_site", required = true)
        protected LeConfigData.Site.CreateSite createSite;
        @XmlElement(name = "users_data", required = true)
        protected List<UsersData> usersData;
        @XmlElement(name = "visitors_data", required = true)
        protected List<VisitorsData> visitorsData;
        @XmlElement(name = "campaign_data", required = true)
        protected List<CampaignData> campaignData;

        /**
         * Gets the value of the createSite property.
         *
         * @return
         *     possible object is
         *     {@link LeConfigData.Site.CreateSite }
         *
         */
        public LeConfigData.Site.CreateSite getCreateSite() {
            return createSite;
        }

        /**
         * Sets the value of the createSite property.
         *
         * @param value
         *     allowed object is
         *     {@link LeConfigData.Site.CreateSite }
         *
         */
        public void setCreateSite(LeConfigData.Site.CreateSite value) {
            this.createSite = value;
        }

        /**
         * Gets the value of the usersData property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the usersData property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUsersData().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link LeConfigData.Site.UsersData }
         *
         *
         */
        public List<UsersData> getUsersData() {
            if (usersData == null) {
                usersData = new ArrayList<UsersData>();
            }
            return this.usersData;
        }

        /**
         * Gets the value of the visitorsData property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the visitorsData property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getVisitorsData().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link LeConfigData.Site.VisitorsData }
         *
         *
         */
        public List<VisitorsData> getVisitorsData() {
            if (visitorsData == null) {
                visitorsData = new ArrayList<VisitorsData>();
            }
            return this.visitorsData;
        }

        /**
         * Gets the value of the campaignData property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the campaignData property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCampaignData().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link LeConfigData.Site.CampaignData }
         *
         *
         */
        public List<CampaignData> getCampaignData() {
            if (campaignData == null) {
                campaignData = new ArrayList<CampaignData>();
            }
            return this.campaignData;
        }


        /**
         * <p>Java class for anonymous complex type.
         *
         * <p>The following schema fragment specifies the expected content contained within this class.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="create_campaign">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="modify_campaign">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="delete_campaign">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "createCampaign",
            "modifyCampaign",
            "deleteCampaign"
        })
        public static class CampaignData {

            @XmlElement(name = "create_campaign", required = true)
            protected LeConfigData.Site.CampaignData.CreateCampaign createCampaign;
            @XmlElement(name = "modify_campaign", required = true)
            protected LeConfigData.Site.CampaignData.ModifyCampaign modifyCampaign;
            @XmlElement(name = "delete_campaign", required = true)
            protected LeConfigData.Site.CampaignData.DeleteCampaign deleteCampaign;

            /**
             * Gets the value of the createCampaign property.
             *
             * @return
             *     possible object is
             *     {@link LeConfigData.Site.CampaignData.CreateCampaign }
             *
             */
            public LeConfigData.Site.CampaignData.CreateCampaign getCreateCampaign() {
                return createCampaign;
            }

            /**
             * Sets the value of the createCampaign property.
             *
             * @param value
             *     allowed object is
             *     {@link LeConfigData.Site.CampaignData.CreateCampaign }
             *
             */
            public void setCreateCampaign(LeConfigData.Site.CampaignData.CreateCampaign value) {
                this.createCampaign = value;
            }

            /**
             * Gets the value of the modifyCampaign property.
             *
             * @return
             *     possible object is
             *     {@link LeConfigData.Site.CampaignData.ModifyCampaign }
             *
             */
            public LeConfigData.Site.CampaignData.ModifyCampaign getModifyCampaign() {
                return modifyCampaign;
            }

            /**
             * Sets the value of the modifyCampaign property.
             *
             * @param value
             *     allowed object is
             *     {@link LeConfigData.Site.CampaignData.ModifyCampaign }
             *
             */
            public void setModifyCampaign(LeConfigData.Site.CampaignData.ModifyCampaign value) {
                this.modifyCampaign = value;
            }

            /**
             * Gets the value of the deleteCampaign property.
             *
             * @return
             *     possible object is
             *     {@link LeConfigData.Site.CampaignData.DeleteCampaign }
             *
             */
            public LeConfigData.Site.CampaignData.DeleteCampaign getDeleteCampaign() {
                return deleteCampaign;
            }

            /**
             * Sets the value of the deleteCampaign property.
             *
             * @param value
             *     allowed object is
             *     {@link LeConfigData.Site.CampaignData.DeleteCampaign }
             *
             */
            public void setDeleteCampaign(LeConfigData.Site.CampaignData.DeleteCampaign value) {
                this.deleteCampaign = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             *
             * <p>The following schema fragment specifies the expected content contained within this class.
             *
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             *
             *
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "siteId"
            })
            public static class CreateCampaign {

                @XmlElement(required = true)
                protected String siteId;

                /**
                 * Gets the value of the siteId property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getSiteId() {
                    return siteId;
                }

                /**
                 * Sets the value of the siteId property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setSiteId(String value) {
                    this.siteId = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             *
             * <p>The following schema fragment specifies the expected content contained within this class.
             *
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             *
             *
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "siteId"
            })
            public static class DeleteCampaign {

                @XmlElement(required = true)
                protected String siteId;

                /**
                 * Gets the value of the siteId property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getSiteId() {
                    return siteId;
                }

                /**
                 * Sets the value of the siteId property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setSiteId(String value) {
                    this.siteId = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             *
             * <p>The following schema fragment specifies the expected content contained within this class.
             *
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             *
             *
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "siteId"
            })
            public static class ModifyCampaign {

                @XmlElement(required = true)
                protected String siteId;

                /**
                 * Gets the value of the siteId property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getSiteId() {
                    return siteId;
                }

                /**
                 * Sets the value of the siteId property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setSiteId(String value) {
                    this.siteId = value;
                }

            }

        }


        /**
         * <p>Java class for anonymous complex type.
         *
         * <p>The following schema fragment specifies the expected content contained within this class.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="isExtentExpiration" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
         *         &lt;element name="creationType">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="CreateNew"/>
         *               &lt;enumeration value="ConnectToExisting"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "siteId",
            "isExtentExpiration",
            "creationType"
        })
        public static class CreateSite {

            @XmlElement(required = true)
            protected String siteId;
            protected boolean isExtentExpiration;
            @XmlElement(required = true)
            protected String creationType;

            /**
             * Gets the value of the siteId property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getSiteId() {
                return siteId;
            }

            /**
             * Sets the value of the siteId property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setSiteId(String value) {
                this.siteId = value;
            }

            /**
             * Gets the value of the isExtentExpiration property.
             *
             */
            public boolean isIsExtentExpiration() {
                return isExtentExpiration;
            }

            /**
             * Sets the value of the isExtentExpiration property.
             *
             */
            public void setIsExtentExpiration(boolean value) {
                this.isExtentExpiration = value;
            }

            /**
             * Gets the value of the creationType property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getCreationType() {
                return creationType;
            }

            /**
             * Sets the value of the creationType property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setCreationType(String value) {
                this.creationType = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         *
         * <p>The following schema fragment specifies the expected content contained within this class.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="create_user">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="userType">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;enumeration value="Administrator"/>
         *                         &lt;enumeration value="Agent"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="skill" maxOccurs="unbounded">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;enumeration value="Manager"/>
         *                         &lt;enumeration value="Sales"/>
         *                         &lt;enumeration value="Support"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="modify_user">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="delete_user">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="deleteSkillData" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="deleteUserData" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "createUser",
            "modifyUser",
            "deleteUser"
        })
        public static class UsersData {

            @XmlElement(name = "create_user", required = true)
            protected LeConfigData.Site.UsersData.CreateUser createUser;
            @XmlElement(name = "modify_user", required = true)
            protected LeConfigData.Site.UsersData.ModifyUser modifyUser;
            @XmlElement(name = "delete_user", required = true)
            protected LeConfigData.Site.UsersData.DeleteUser deleteUser;

            /**
             * Gets the value of the createUser property.
             *
             * @return
             *     possible object is
             *     {@link LeConfigData.Site.UsersData.CreateUser }
             *
             */
            public LeConfigData.Site.UsersData.CreateUser getCreateUser() {
                return createUser;
            }

            /**
             * Sets the value of the createUser property.
             *
             * @param value
             *     allowed object is
             *     {@link LeConfigData.Site.UsersData.CreateUser }
             *
             */
            public void setCreateUser(LeConfigData.Site.UsersData.CreateUser value) {
                this.createUser = value;
            }

            /**
             * Gets the value of the modifyUser property.
             *
             * @return
             *     possible object is
             *     {@link LeConfigData.Site.UsersData.ModifyUser }
             *
             */
            public LeConfigData.Site.UsersData.ModifyUser getModifyUser() {
                return modifyUser;
            }

            /**
             * Sets the value of the modifyUser property.
             *
             * @param value
             *     allowed object is
             *     {@link LeConfigData.Site.UsersData.ModifyUser }
             *
             */
            public void setModifyUser(LeConfigData.Site.UsersData.ModifyUser value) {
                this.modifyUser = value;
            }

            /**
             * Gets the value of the deleteUser property.
             *
             * @return
             *     possible object is
             *     {@link LeConfigData.Site.UsersData.DeleteUser }
             *
             */
            public LeConfigData.Site.UsersData.DeleteUser getDeleteUser() {
                return deleteUser;
            }

            /**
             * Sets the value of the deleteUser property.
             *
             * @param value
             *     allowed object is
             *     {@link LeConfigData.Site.UsersData.DeleteUser }
             *
             */
            public void setDeleteUser(LeConfigData.Site.UsersData.DeleteUser value) {
                this.deleteUser = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             *
             * <p>The following schema fragment specifies the expected content contained within this class.
             *
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="userType">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;enumeration value="Administrator"/>
             *               &lt;enumeration value="Agent"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="skill" maxOccurs="unbounded">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;enumeration value="Manager"/>
             *               &lt;enumeration value="Sales"/>
             *               &lt;enumeration value="Support"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             *
             *
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "userType",
                "user",
                "password",
                "email",
                "skill"
            })
            public static class CreateUser {

                @XmlElement(required = true)
                protected String userType;
                @XmlElement(required = true)
                protected String user;
                @XmlElement(required = true)
                protected String password;
                @XmlElement(required = true)
                protected String email;
                @XmlElement(required = true)
                protected List<String> skill;

                /**
                 * Gets the value of the userType property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUserType() {
                    return userType;
                }

                /**
                 * Sets the value of the userType property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUserType(String value) {
                    this.userType = value;
                }

                /**
                 * Gets the value of the user property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUser() {
                    return user;
                }

                /**
                 * Sets the value of the user property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUser(String value) {
                    this.user = value;
                }

                /**
                 * Gets the value of the password property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getPassword() {
                    return password;
                }

                /**
                 * Sets the value of the password property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setPassword(String value) {
                    this.password = value;
                }

                /**
                 * Gets the value of the email property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getEmail() {
                    return email;
                }

                /**
                 * Sets the value of the email property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setEmail(String value) {
                    this.email = value;
                }

                /**
                 * Gets the value of the skill property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the skill property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getSkill().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link String }
                 *
                 *
                 */
                public List<String> getSkill() {
                    if (skill == null) {
                        skill = new ArrayList<String>();
                    }
                    return this.skill;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             *
             * <p>The following schema fragment specifies the expected content contained within this class.
             *
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="deleteSkillData" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="deleteUserData" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             *
             *
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "siteId",
                "deleteSkillData",
                "deleteUserData"
            })
            public static class DeleteUser {

                @XmlElement(required = true)
                protected String siteId;
                @XmlElement(required = true)
                protected String deleteSkillData;
                @XmlElement(required = true)
                protected String deleteUserData;

                /**
                 * Gets the value of the siteId property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getSiteId() {
                    return siteId;
                }

                /**
                 * Sets the value of the siteId property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setSiteId(String value) {
                    this.siteId = value;
                }

                /**
                 * Gets the value of the deleteSkillData property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getDeleteSkillData() {
                    return deleteSkillData;
                }

                /**
                 * Sets the value of the deleteSkillData property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setDeleteSkillData(String value) {
                    this.deleteSkillData = value;
                }

                /**
                 * Gets the value of the deleteUserData property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getDeleteUserData() {
                    return deleteUserData;
                }

                /**
                 * Sets the value of the deleteUserData property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setDeleteUserData(String value) {
                    this.deleteUserData = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             *
             * <p>The following schema fragment specifies the expected content contained within this class.
             *
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             *
             *
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "siteId"
            })
            public static class ModifyUser {

                @XmlElement(required = true)
                protected String siteId;

                /**
                 * Gets the value of the siteId property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getSiteId() {
                    return siteId;
                }

                /**
                 * Sets the value of the siteId property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setSiteId(String value) {
                    this.siteId = value;
                }

            }

        }


        /**
         * <p>Java class for anonymous complex type.
         *
         * <p>The following schema fragment specifies the expected content contained within this class.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="create_visitors">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="modify_visitors">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="delete_visitors">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "createVisitors",
            "modifyVisitors",
            "deleteVisitors"
        })
        public static class VisitorsData {

            @XmlElement(name = "create_visitors", required = true)
            protected LeConfigData.Site.VisitorsData.CreateVisitors createVisitors;
            @XmlElement(name = "modify_visitors", required = true)
            protected LeConfigData.Site.VisitorsData.ModifyVisitors modifyVisitors;
            @XmlElement(name = "delete_visitors", required = true)
            protected LeConfigData.Site.VisitorsData.DeleteVisitors deleteVisitors;

            /**
             * Gets the value of the createVisitors property.
             *
             * @return
             *     possible object is
             *     {@link LeConfigData.Site.VisitorsData.CreateVisitors }
             *
             */
            public LeConfigData.Site.VisitorsData.CreateVisitors getCreateVisitors() {
                return createVisitors;
            }

            /**
             * Sets the value of the createVisitors property.
             *
             * @param value
             *     allowed object is
             *     {@link LeConfigData.Site.VisitorsData.CreateVisitors }
             *
             */
            public void setCreateVisitors(LeConfigData.Site.VisitorsData.CreateVisitors value) {
                this.createVisitors = value;
            }

            /**
             * Gets the value of the modifyVisitors property.
             *
             * @return
             *     possible object is
             *     {@link LeConfigData.Site.VisitorsData.ModifyVisitors }
             *
             */
            public LeConfigData.Site.VisitorsData.ModifyVisitors getModifyVisitors() {
                return modifyVisitors;
            }

            /**
             * Sets the value of the modifyVisitors property.
             *
             * @param value
             *     allowed object is
             *     {@link LeConfigData.Site.VisitorsData.ModifyVisitors }
             *
             */
            public void setModifyVisitors(LeConfigData.Site.VisitorsData.ModifyVisitors value) {
                this.modifyVisitors = value;
            }

            /**
             * Gets the value of the deleteVisitors property.
             *
             * @return
             *     possible object is
             *     {@link LeConfigData.Site.VisitorsData.DeleteVisitors }
             *
             */
            public LeConfigData.Site.VisitorsData.DeleteVisitors getDeleteVisitors() {
                return deleteVisitors;
            }

            /**
             * Sets the value of the deleteVisitors property.
             *
             * @param value
             *     allowed object is
             *     {@link LeConfigData.Site.VisitorsData.DeleteVisitors }
             *
             */
            public void setDeleteVisitors(LeConfigData.Site.VisitorsData.DeleteVisitors value) {
                this.deleteVisitors = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "siteId"
            })
            public static class CreateVisitors {

                @XmlElement(required = true)
                protected String siteId;

                /**
                 * Gets the value of the siteId property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getSiteId() {
                    return siteId;
                }

                /**
                 * Sets the value of the siteId property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setSiteId(String value) {
                    this.siteId = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "siteId"
            })
            public static class DeleteVisitors {

                @XmlElement(required = true)
                protected String siteId;

                /**
                 * Gets the value of the siteId property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getSiteId() {
                    return siteId;
                }

                /**
                 * Sets the value of the siteId property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setSiteId(String value) {
                    this.siteId = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "siteId"
            })
            public static class ModifyVisitors {

                @XmlElement(required = true)
                protected String siteId;

                /**
                 * Gets the value of the siteId property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getSiteId() {
                    return siteId;
                }

                /**
                 * Sets the value of the siteId property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setSiteId(String value) {
                    this.siteId = value;
                }

            }

        }

    }

}
