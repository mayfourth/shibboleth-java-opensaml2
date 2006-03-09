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

import org.joda.time.DateTime;
import org.opensaml.common.SAMLObjectValidatorBaseTestCase;
import org.opensaml.saml1.core.RequestAbstractType;

/**
 * Test case for {@link org.opensaml.saml1.core.validator.RequestSchemaValidator}.
 */
public abstract class RequestAbstractTypeSchemaTest extends SAMLObjectValidatorBaseTestCase {

    /** Constructor */
    public RequestAbstractTypeSchemaTest() {
        super();
    }

    /*
     * @see org.opensaml.common.SAMLObjectValidatorBaseTestCase#populateRequiredData()
     */
    protected void populateRequiredData() {
        super.populateRequiredData();
        
        RequestAbstractType request = (RequestAbstractType) target;
        request.setID("Ident");
        request.setMinorVersion(1);
        request.setIssueInstant(new DateTime());
       
    }
    
    public void testMissingID() {
        RequestAbstractType request = (RequestAbstractType) target;
        request.setID(null);
        assertValidationFail("No RequestID, should raise a Validation Exception");
    }

    public void testMissingIssueInstant() {
        RequestAbstractType request = (RequestAbstractType) target;
        request.setIssueInstant(null);
        assertValidationFail("Both IssueInstant attribute present, should raise a Validation Exception");
    }
}