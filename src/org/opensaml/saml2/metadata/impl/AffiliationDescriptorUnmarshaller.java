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

import org.joda.time.DateTime;
import org.joda.time.chrono.ISOChronology;
import org.opensaml.common.impl.AbstractSAMLObjectUnmarshaller;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.common.CacheableSAMLObject;
import org.opensaml.saml2.common.Extensions;
import org.opensaml.saml2.common.TimeBoundSAMLObject;
import org.opensaml.saml2.metadata.AffiliateMember;
import org.opensaml.saml2.metadata.AffiliationDescriptor;
import org.opensaml.saml2.metadata.KeyDescriptor;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.UnmarshallingException;
import org.opensaml.xml.util.DatatypeHelper;
import org.opensaml.xml.util.XMLHelper;
import org.w3c.dom.Attr;

/**
 * A thread safe Unmarshaller for {@link org.opensaml.saml2.metadata.AffiliationDescriptor}s.
 */
public class AffiliationDescriptorUnmarshaller extends AbstractSAMLObjectUnmarshaller {

    /**
     * Constructor
     */
    public AffiliationDescriptorUnmarshaller() {
        super(SAMLConstants.SAML20MD_NS, AffiliationDescriptor.DEFAULT_ELEMENT_LOCAL_NAME);
    }

    /**
     * Constructor
     * 
     * @param namespaceURI
     * @param elementLocalName
     */
    protected AffiliationDescriptorUnmarshaller(String namespaceURI, String elementLocalName) {
        super(namespaceURI, elementLocalName);
    }

    /** {@inheritDoc} */
    protected void processChildElement(XMLObject parentSAMLObject, XMLObject childSAMLObject)
            throws UnmarshallingException {
        AffiliationDescriptor descriptor = (AffiliationDescriptor) parentSAMLObject;

        if (childSAMLObject instanceof Extensions) {
            descriptor.setExtensions((Extensions) childSAMLObject);
        } else if (childSAMLObject instanceof AffiliateMember) {
            descriptor.getMembers().add((AffiliateMember) childSAMLObject);
        } else if (childSAMLObject instanceof KeyDescriptor) {
            descriptor.getKeyDescriptors().add((KeyDescriptor) childSAMLObject);
        } else {
            super.processChildElement(parentSAMLObject, childSAMLObject);
        }
    }

    /** {@inheritDoc} */
    protected void processAttribute(XMLObject samlObject, Attr attribute) throws UnmarshallingException {
        AffiliationDescriptor descriptor = (AffiliationDescriptor) samlObject;

        if (attribute.getLocalName().equals(AffiliationDescriptor.OWNER_ID_ATTRIB_NAME)) {
            descriptor.setOwnerID(attribute.getValue());
        } else if (attribute.getLocalName().equals(AffiliationDescriptor.ID_ATTRIB_NAME)) {
            descriptor.setID(attribute.getValue());
        } else if (attribute.getLocalName().equals(TimeBoundSAMLObject.VALID_UNTIL_ATTRIB_NAME)) {
            descriptor.setValidUntil(new DateTime(attribute.getValue(), ISOChronology.getInstanceUTC()));
        } else if (attribute.getLocalName().equals(CacheableSAMLObject.CACHE_DURATION_ATTRIB_NAME)) {
            descriptor.setCacheDuration(DatatypeHelper.durationToLong(attribute.getValue()));
        } else {
            descriptor.getUnknownAttributes().put(XMLHelper.getNodeQName(attribute), attribute.getValue());
        }
    }
}