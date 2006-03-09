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

package org.opensaml.saml1.core.validator;

import javax.xml.namespace.QName;

import org.joda.time.DateTime;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml1.core.AuthenticationStatement;

/**
 * Test case for {@link org.opensaml.saml1.core.validator.AuthenticationStatementValidator}.
 */
public class AuthenticationStatementSchemaTest extends SubjectStatementSchemaTest {

    /** Constructor */
    public AuthenticationStatementSchemaTest() {
        super();
        targetQName = new QName(SAMLConstants.SAML1_NS, AuthenticationStatement.LOCAL_NAME, SAMLConstants.SAML1_PREFIX);
        validator = new AuthenticationStatementValidator();

    }

    /*
     * @see org.opensaml.common.SAMLObjectValidatorBaseTestCase#populateRequiredData()
     */
    protected void populateRequiredData() {
        super.populateRequiredData();

        AuthenticationStatement authenticationStatement = (AuthenticationStatement) target;
        authenticationStatement.setAuthenticationInstant(new DateTime());
        authenticationStatement.setAuthenticationMethod("Hi there!");
    }
    
    public void testMissingAuthenticationInstant(){
        AuthenticationStatement authenticationStatement = (AuthenticationStatement) target;

        authenticationStatement.setAuthenticationInstant(null);
        assertValidationFail("No AuthenticationInstant attribute - should fail");
    }

    public void testMissingAuthenticationMethod(){
        AuthenticationStatement authenticationStatement = (AuthenticationStatement) target;

        authenticationStatement.setAuthenticationMethod("");
        assertValidationFail("No AuthenticationMethod attribute - should fail");
    }
}