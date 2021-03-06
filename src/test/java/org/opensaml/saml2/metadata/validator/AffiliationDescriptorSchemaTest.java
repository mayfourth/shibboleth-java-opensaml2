/*
 * Licensed to the University Corporation for Advanced Internet Development, 
 * Inc. (UCAID) under one or more contributor license agreements.  See the 
 * NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The UCAID licenses this file to You under the Apache 
 * License, Version 2.0 (the "License"); you may not use this file except in 
 * compliance with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensaml.saml2.metadata.validator;

import javax.xml.namespace.QName;

import org.opensaml.common.BaseSAMLObjectValidatorTestCase;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.metadata.AffiliateMember;
import org.opensaml.saml2.metadata.AffiliationDescriptor;
import org.opensaml.xml.validation.ValidationException;

/**
 * Test case for {@link org.opensaml.saml2.metadata.AffiliationDescriptor}.
 */
public class AffiliationDescriptorSchemaTest extends BaseSAMLObjectValidatorTestCase {

    /** Constructor */
    public AffiliationDescriptorSchemaTest() {
        targetQName = new QName(SAMLConstants.SAML20MD_NS, AffiliationDescriptor.DEFAULT_ELEMENT_LOCAL_NAME,
                SAMLConstants.SAML20MD_PREFIX);
        validator = new AffiliationDescriptorSchemaValidator();
    }

    /** {@inheritDoc} */
    protected void populateRequiredData() {
        super.populateRequiredData();
        AffiliationDescriptor affiliationDescriptor = (AffiliationDescriptor) target;
        AffiliateMember affiliateMember = (AffiliateMember) buildXMLObject(new QName(SAMLConstants.SAML20MD_NS,
                AffiliateMember.DEFAULT_ELEMENT_LOCAL_NAME, SAMLConstants.SAML20MD_PREFIX));
        affiliationDescriptor.setOwnerID("owner id");
        affiliationDescriptor.getMembers().add(affiliateMember);
    }

    /**
     * Tests for Owner ID failure.
     * 
     * @throws ValidationException
     */
    public void testOwnerFailure() throws ValidationException {
        AffiliationDescriptor affiliationDescriptor = (AffiliationDescriptor) target;

        affiliationDescriptor.setOwnerID(null);
        assertValidationFail("Owner ID was null, should raise a Validation Exception.");

        affiliationDescriptor.setOwnerID("");
        assertValidationFail("Owner ID was empty string, should raise a Validation Exception.");

        affiliationDescriptor.setOwnerID("   ");
        assertValidationFail("Owner ID was white space, should raise a Validation Exception.");
    }

    /**
     * Tests for Member failure.
     * 
     * @throws ValidationException
     */
    public void testMemberFailure() throws ValidationException {
        AffiliationDescriptor affiliationDescriptor = (AffiliationDescriptor) target;

        affiliationDescriptor.getMembers().clear();
        assertValidationFail("Members list was empty, should raise a Validation Exception.");
    }
}