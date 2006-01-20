/*
 * Copyright [2005] [University Corporation for Advanced Internet Development, Inc.]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensaml.saml2.metadata.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.opensaml.common.SAMLObject;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.metadata.AssertionIDRequestService;
import org.opensaml.saml2.metadata.AuthnAuthorityDescriptor;
import org.opensaml.saml2.metadata.AuthnQueryService;
import org.opensaml.saml2.metadata.NameIDFormat;
import org.opensaml.xml.util.XMLObjectChildrenList;

/**
 * Concreate implementation of {@link org.opensaml.saml2.metadata.AuthnAuthorityDescriptor}
 */
public class AuthnAuthorityDescriptorImpl extends RoleDescriptorImpl implements AuthnAuthorityDescriptor {

    /** validUntil attribute */
    private GregorianCalendar validUntil;
    
    /** cacheDurection attribute */
    private Long cacheDuration;
    
    /** AuthnQueryService endpoints */
    private XMLObjectChildrenList<AuthnQueryService> authnQueryServices;
    
    /** AuthnQueryService endpoints */
    private XMLObjectChildrenList<AssertionIDRequestService> assertionIDRequestServices;
    
    /** NameID formats supported by this descriptor */
    private XMLObjectChildrenList<NameIDFormat> nameIDFormats;
    
    /**
     * Constrcutor
     */
    public AuthnAuthorityDescriptorImpl() {
        super(AuthnAuthorityDescriptor.LOCAL_NAME);
        setElementNamespacePrefix(SAMLConstants.SAML20MD_PREFIX);
        
        authnQueryServices = new XMLObjectChildrenList<AuthnQueryService>(this);
        assertionIDRequestServices = new XMLObjectChildrenList<AssertionIDRequestService>(this);
        nameIDFormats = new XMLObjectChildrenList<NameIDFormat>(this);
    }
    
    /*
     * @see org.opensaml.saml2.common.TimeBoundSAMLObject#isValid()
     */
    public boolean isValid() {
        return validUntil.before(GregorianCalendar.getInstance(TimeZone.getTimeZone("UTC")));
    }
    
    /*
     * @see org.opensaml.saml2.common.TimeBoundSAMLObject#getValidUntil()
     */
    public GregorianCalendar getValidUntil() {
        return validUntil;
    }

    /*
     * @see org.opensaml.saml2.common.TimeBoundSAMLObject#setValidUntil(java.util.GregorianCalendar)
     */
    public void setValidUntil(GregorianCalendar validUntil) {
        this.validUntil = prepareForAssignment(this.validUntil, validUntil);
    }

    /*
     * @see org.opensaml.saml2.common.CacheableSAMLObject#getCacheDuration()
     */
    public Long getCacheDuration() {
        return cacheDuration;
    }

    /*
     * @see org.opensaml.saml2.common.CacheableSAMLObject#setCacheDuration(java.lang.Long)
     */
    public void setCacheDuration(Long duration) {
        cacheDuration = prepareForAssignment(cacheDuration, duration);
    }
    
    /*
     * @see org.opensaml.saml2.metadata.AuthnAuthorityDescriptor#getAuthnQueryServices()
     */
    public List<AuthnQueryService> getAuthnQueryServices() {
        return authnQueryServices;
    }

    /*
     * @see org.opensaml.saml2.metadata.AssertionIDRequestDescriptorComp#getAssertionIDRequestServices()
     */
    public List<AssertionIDRequestService> getAssertionIDRequestServices() {
        return assertionIDRequestServices;
    }

    /*
     * @see org.opensaml.saml2.metadata.NameIDFormatDescriptorComp#getNameIDFormats()
     */
    public List<NameIDFormat> getNameIDFormats() {
        return nameIDFormats;
    }
    
    /*
     * @see org.opensaml.common.SAMLObject#getOrderedChildren()
     */
    public List<SAMLObject> getOrderedChildren(){
        ArrayList<SAMLObject> children = new ArrayList<SAMLObject>();
        
        children.addAll(super.getOrderedChildren());
        children.addAll(authnQueryServices);
        children.addAll(assertionIDRequestServices);
        children.addAll(nameIDFormats);
        
        return Collections.unmodifiableList(children);
    }
}