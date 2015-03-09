
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
 *                             &lt;element name="siteUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="modify_site">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="siteUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="delete_site">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="siteUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
 *                                       &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    protected Site site;

    /**
     * Gets the value of the site property.
     *
     * @return
     *     possible object is
     *     {@link com.config.data.le.LeConfigData.Site }
     *
     */
    public Site getSite() {
        return site;
    }

    /**
     * Sets the value of the site property.
     *
     * @param value
     *     allowed object is
     *     {@link com.config.data.le.LeConfigData.Site }
     *
     */
    public void setSite(Site value) {
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
     *                   &lt;element name="siteUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="modify_site">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="siteUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="delete_site">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="siteUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
     *                             &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "modifySite",
        "deleteSite",
        "usersData",
        "visitorsData",
        "campaignData"
    })
    public static class Site {

        @XmlElement(name = "create_site", required = true)
        protected CreateSite createSite;
        @XmlElement(name = "modify_site", required = true)
        protected ModifySite modifySite;
        @XmlElement(name = "delete_site", required = true)
        protected DeleteSite deleteSite;
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
         *     {@link com.config.data.le.LeConfigData.Site.CreateSite }
         *
         */
        public CreateSite getCreateSite() {
            return createSite;
        }

        /**
         * Sets the value of the createSite property.
         *
         * @param value
         *     allowed object is
         *     {@link com.config.data.le.LeConfigData.Site.CreateSite }
         *
         */
        public void setCreateSite(CreateSite value) {
            this.createSite = value;
        }

        /**
         * Gets the value of the modifySite property.
         *
         * @return
         *     possible object is
         *     {@link com.config.data.le.LeConfigData.Site.ModifySite }
         *
         */
        public ModifySite getModifySite() {
            return modifySite;
        }

        /**
         * Sets the value of the modifySite property.
         *
         * @param value
         *     allowed object is
         *     {@link com.config.data.le.LeConfigData.Site.ModifySite }
         *
         */
        public void setModifySite(ModifySite value) {
            this.modifySite = value;
        }

        /**
         * Gets the value of the deleteSite property.
         *
         * @return
         *     possible object is
         *     {@link com.config.data.le.LeConfigData.Site.DeleteSite }
         *
         */
        public DeleteSite getDeleteSite() {
            return deleteSite;
        }

        /**
         * Sets the value of the deleteSite property.
         *
         * @param value
         *     allowed object is
         *     {@link com.config.data.le.LeConfigData.Site.DeleteSite }
         *
         */
        public void setDeleteSite(DeleteSite value) {
            this.deleteSite = value;
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
         * {@link com.config.data.le.LeConfigData.Site.UsersData }
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
         * {@link com.config.data.le.LeConfigData.Site.VisitorsData }
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
         * {@link com.config.data.le.LeConfigData.Site.CampaignData }
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
            protected CreateCampaign createCampaign;
            @XmlElement(name = "modify_campaign", required = true)
            protected ModifyCampaign modifyCampaign;
            @XmlElement(name = "delete_campaign", required = true)
            protected DeleteCampaign deleteCampaign;

            /**
             * Gets the value of the createCampaign property.
             *
             * @return
             *     possible object is
             *     {@link com.config.data.le.LeConfigData.Site.CampaignData.CreateCampaign }
             *
             */
            public CreateCampaign getCreateCampaign() {
                return createCampaign;
            }

            /**
             * Sets the value of the createCampaign property.
             *
             * @param value
             *     allowed object is
             *     {@link com.config.data.le.LeConfigData.Site.CampaignData.CreateCampaign }
             *
             */
            public void setCreateCampaign(CreateCampaign value) {
                this.createCampaign = value;
            }

            /**
             * Gets the value of the modifyCampaign property.
             *
             * @return
             *     possible object is
             *     {@link com.config.data.le.LeConfigData.Site.CampaignData.ModifyCampaign }
             *
             */
            public ModifyCampaign getModifyCampaign() {
                return modifyCampaign;
            }

            /**
             * Sets the value of the modifyCampaign property.
             *
             * @param value
             *     allowed object is
             *     {@link com.config.data.le.LeConfigData.Site.CampaignData.ModifyCampaign }
             *
             */
            public void setModifyCampaign(ModifyCampaign value) {
                this.modifyCampaign = value;
            }

            /**
             * Gets the value of the deleteCampaign property.
             *
             * @return
             *     possible object is
             *     {@link com.config.data.le.LeConfigData.Site.CampaignData.DeleteCampaign }
             *
             */
            public DeleteCampaign getDeleteCampaign() {
                return deleteCampaign;
            }

            /**
             * Sets the value of the deleteCampaign property.
             *
             * @param value
             *     allowed object is
             *     {@link com.config.data.le.LeConfigData.Site.CampaignData.DeleteCampaign }
             *
             */
            public void setDeleteCampaign(DeleteCampaign value) {
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
         *         &lt;element name="siteUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "siteUrl"
        })
        public static class CreateSite {

            @XmlElement(required = true)
            protected String siteId;
            @XmlElement(required = true)
            protected String siteUrl;

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
             * Gets the value of the siteUrl property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getSiteUrl() {
                return siteUrl;
            }

            /**
             * Sets the value of the siteUrl property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setSiteUrl(String value) {
                this.siteUrl = value;
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
         *         &lt;element name="siteUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "siteUrl"
        })
        public static class DeleteSite {

            @XmlElement(required = true)
            protected String siteId;
            @XmlElement(required = true)
            protected String siteUrl;

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
             * Gets the value of the siteUrl property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getSiteUrl() {
                return siteUrl;
            }

            /**
             * Sets the value of the siteUrl property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setSiteUrl(String value) {
                this.siteUrl = value;
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
         *         &lt;element name="siteUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "siteUrl"
        })
        public static class ModifySite {

            @XmlElement(required = true)
            protected String siteId;
            @XmlElement(required = true)
            protected String siteUrl;

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
             * Gets the value of the siteUrl property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getSiteUrl() {
                return siteUrl;
            }

            /**
             * Sets the value of the siteUrl property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setSiteUrl(String value) {
                this.siteUrl = value;
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
         *                   &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            protected CreateUser createUser;
            @XmlElement(name = "modify_user", required = true)
            protected ModifyUser modifyUser;
            @XmlElement(name = "delete_user", required = true)
            protected DeleteUser deleteUser;

            /**
             * Gets the value of the createUser property.
             *
             * @return
             *     possible object is
             *     {@link com.config.data.le.LeConfigData.Site.UsersData.CreateUser }
             *
             */
            public CreateUser getCreateUser() {
                return createUser;
            }

            /**
             * Sets the value of the createUser property.
             *
             * @param value
             *     allowed object is
             *     {@link com.config.data.le.LeConfigData.Site.UsersData.CreateUser }
             *
             */
            public void setCreateUser(CreateUser value) {
                this.createUser = value;
            }

            /**
             * Gets the value of the modifyUser property.
             *
             * @return
             *     possible object is
             *     {@link com.config.data.le.LeConfigData.Site.UsersData.ModifyUser }
             *
             */
            public ModifyUser getModifyUser() {
                return modifyUser;
            }

            /**
             * Sets the value of the modifyUser property.
             *
             * @param value
             *     allowed object is
             *     {@link com.config.data.le.LeConfigData.Site.UsersData.ModifyUser }
             *
             */
            public void setModifyUser(ModifyUser value) {
                this.modifyUser = value;
            }

            /**
             * Gets the value of the deleteUser property.
             *
             * @return
             *     possible object is
             *     {@link com.config.data.le.LeConfigData.Site.UsersData.DeleteUser }
             *
             */
            public DeleteUser getDeleteUser() {
                return deleteUser;
            }

            /**
             * Sets the value of the deleteUser property.
             *
             * @param value
             *     allowed object is
             *     {@link com.config.data.le.LeConfigData.Site.UsersData.DeleteUser }
             *
             */
            public void setDeleteUser(DeleteUser value) {
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
             *         &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
                "siteId",
                "skill"
            })
            public static class CreateUser {

                @XmlElement(required = true)
                protected String siteId;
                @XmlElement(required = true)
                protected List<String> skill;

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
            public static class DeleteUser {

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
            protected CreateVisitors createVisitors;
            @XmlElement(name = "modify_visitors", required = true)
            protected ModifyVisitors modifyVisitors;
            @XmlElement(name = "delete_visitors", required = true)
            protected DeleteVisitors deleteVisitors;

            /**
             * Gets the value of the createVisitors property.
             *
             * @return
             *     possible object is
             *     {@link com.config.data.le.LeConfigData.Site.VisitorsData.CreateVisitors }
             *
             */
            public CreateVisitors getCreateVisitors() {
                return createVisitors;
            }

            /**
             * Sets the value of the createVisitors property.
             *
             * @param value
             *     allowed object is
             *     {@link com.config.data.le.LeConfigData.Site.VisitorsData.CreateVisitors }
             *
             */
            public void setCreateVisitors(CreateVisitors value) {
                this.createVisitors = value;
            }

            /**
             * Gets the value of the modifyVisitors property.
             *
             * @return
             *     possible object is
             *     {@link com.config.data.le.LeConfigData.Site.VisitorsData.ModifyVisitors }
             *
             */
            public ModifyVisitors getModifyVisitors() {
                return modifyVisitors;
            }

            /**
             * Sets the value of the modifyVisitors property.
             *
             * @param value
             *     allowed object is
             *     {@link com.config.data.le.LeConfigData.Site.VisitorsData.ModifyVisitors }
             *
             */
            public void setModifyVisitors(ModifyVisitors value) {
                this.modifyVisitors = value;
            }

            /**
             * Gets the value of the deleteVisitors property.
             *
             * @return
             *     possible object is
             *     {@link com.config.data.le.LeConfigData.Site.VisitorsData.DeleteVisitors }
             *
             */
            public DeleteVisitors getDeleteVisitors() {
                return deleteVisitors;
            }

            /**
             * Sets the value of the deleteVisitors property.
             *
             * @param value
             *     allowed object is
             *     {@link com.config.data.le.LeConfigData.Site.VisitorsData.DeleteVisitors }
             *
             */
            public void setDeleteVisitors(DeleteVisitors value) {
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
