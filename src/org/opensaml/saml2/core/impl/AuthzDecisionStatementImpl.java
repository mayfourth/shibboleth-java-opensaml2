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

/**
 * 
 */

package org.opensaml.saml2.core.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.opensaml.common.SAMLObject;
import org.opensaml.common.impl.AbstractSAMLObject;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.core.Action;
import org.opensaml.saml2.core.AuthzDecisionStatement;
import org.opensaml.saml2.core.DecisionType;
import org.opensaml.saml2.core.Evidence;
import org.opensaml.xml.util.XMLObjectChildrenList;

/**
 * A concrete implementation of {@link org.opensaml.saml2.core.AuthzDecisionStatement}.
 */
public class AuthzDecisionStatementImpl extends AbstractSAMLObject implements AuthzDecisionStatement {

    /** URI of the resource to which authorization is sought */
    private String resource;

    /** Decision of the authorization request */
    private DecisionType decision;

    /** Actions authorized to be performed */
    private XMLObjectChildrenList<Action> actions;

    /** SAML assertion the authority relied on when making the authorization decision */
    private Evidence evidence;

    /** Constructor */
    public AuthzDecisionStatementImpl() {
        super(SAMLConstants.SAML20_NS, AuthzDecisionStatement.LOCAL_NAME);
        setElementNamespacePrefix(SAMLConstants.SAML20_PREFIX);

        actions = new XMLObjectChildrenList<Action>(this);
    }

    /*
     * @see org.opensaml.saml2.core.AuthzDecisionStatement#getResource()
     */
    public String getResource() {
        return resource;
    }

    /*
     * @see org.opensaml.saml2.core.AuthzDecisionStatement#setResource(java.lang.String)
     */
    public void setResource(String newResourceURI) {
        this.resource = prepareForAssignment(this.resource, newResourceURI);
    }

    /*
     * @see org.opensaml.saml2.core.AuthzDecisionStatement#getDecision()
     */
    public DecisionType getDecision() {
        return decision;
    }

    /*
     * @see org.opensaml.saml2.core.AuthzDecisionStatement#setDecision(org.opensaml.saml2.core.DecisionType)
     */
    public void setDecision(DecisionType newDecision) {
        this.decision = prepareForAssignment(this.decision, newDecision);
    }

    /*
     * @see org.opensaml.saml2.core.AuthzDecisionStatement#getActions()
     */
    public List<Action> getActions() {
        return actions;
    }

    /*
     * @see org.opensaml.saml2.core.AuthzDecisionStatement#getEvidence()
     */
    public Evidence getEvidence() {
        return evidence;
    }

    /*
     * @see org.opensaml.saml2.core.AuthzDecisionStatement#setEvidence(org.opensaml.saml2.core.Evidence)
     */
    public void setEvidence(Evidence newEvidence) {
        this.evidence = prepareForAssignment(this.evidence, newEvidence);
    }

    /*
     * @see org.opensaml.common.SAMLObject#getOrderedChildren()
     */
    public List<SAMLObject> getOrderedChildren() {
        ArrayList<SAMLObject> children = new ArrayList<SAMLObject>();

        children.addAll(actions);
        children.add(evidence);
        return Collections.unmodifiableList(children);
    }
}