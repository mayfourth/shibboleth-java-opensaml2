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

package org.opensaml.saml2.core.impl;

import javax.xml.namespace.QName;

import org.opensaml.common.SAMLObjectBaseTestCase;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.SubjectConfirmation;
import org.opensaml.saml2.core.SubjectConfirmationData;

/**
 * Test case for creating, marshalling, and unmarshalling {@link org.opensaml.saml2.core.impl.SubjectConfirmationImpl}.
 */
public class SubjectConfirmationTest extends SAMLObjectBaseTestCase {

    /** Expected Method value */
    private String expectedMethod;

    /** Constructor */
    public SubjectConfirmationTest() {
        singleElementFile = "/data/org/opensaml/saml2/core/impl/SubjectConfirmation.xml";
        childElementsFile = "/data/org/opensaml/saml2/core/impl/SubjectConfirmationChildElements.xml";
    }

    /** {@inheritDoc} */
    protected void setUp() throws Exception {
        super.setUp();
        expectedMethod = "conf method";
    }

    /** {@inheritDoc} */
    public void testSingleElementUnmarshall() {
        SubjectConfirmation subjectConfirmation = (SubjectConfirmation) unmarshallElement(singleElementFile);

        String method = subjectConfirmation.getMethod();
        assertEquals("Method not as expected", expectedMethod, method);
    }

    /** {@inheritDoc} */
    public void testSingleElementOptionalAttributesUnmarshall() {
        // do nothing
    }

    /** {@inheritDoc} */
    public void testSingleElementMarshall() {
        QName qname = new QName(SAMLConstants.SAML20_NS, SubjectConfirmation.DEFAULT_ELEMENT_LOCAL_NAME, SAMLConstants.SAML20_PREFIX);
        SubjectConfirmation subjectConfirmation = (SubjectConfirmation) buildXMLObject(qname);

        subjectConfirmation.setMethod(expectedMethod);
        assertEquals(expectedDOM, subjectConfirmation);
    }

    /** {@inheritDoc} */
    public void testSingleElementOptionalAttributesMarshall() {
        // do nothing
    }

    /** {@inheritDoc} */
    public void testChildElementsUnmarshall() {
        SubjectConfirmation subjectConfirmation = (SubjectConfirmation) unmarshallElement(childElementsFile);

        assertNotNull("Identifier elemement not present", subjectConfirmation.getNameID());
        assertNotNull("SubjectConfirmationData element not present", subjectConfirmation.getSubjectConfirmationData());
    }

    /** {@inheritDoc} */
    public void testChildElementsMarshall() {
        QName qname = new QName(SAMLConstants.SAML20_NS, SubjectConfirmation.DEFAULT_ELEMENT_LOCAL_NAME, SAMLConstants.SAML20_PREFIX);
        SubjectConfirmation subjectConfirmation = (SubjectConfirmation) buildXMLObject(qname);

        QName nameIDQName = new QName(SAMLConstants.SAML20_NS, NameID.DEFAULT_ELEMENT_LOCAL_NAME, SAMLConstants.SAML20_PREFIX);
        subjectConfirmation.setNameID((NameID) buildXMLObject(nameIDQName));
        
        QName subjectConfirmationDataQName = new QName(SAMLConstants.SAML20_NS, SubjectConfirmationData.DEFAULT_ELEMENT_LOCAL_NAME, SAMLConstants.SAML20_PREFIX);
        subjectConfirmation.setSubjectConfirmationData((SubjectConfirmationData) buildXMLObject(subjectConfirmationDataQName));

        assertEquals(expectedChildElementsDOM, subjectConfirmation);
    }
}