/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2005-2017 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://oss.oracle.com/licenses/CDDL+GPL-1.1
 * or LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package javax.xml.ws;

import java.security.Principal;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.wsaddressing.W3CEndpointReference;
import org.w3c.dom.Element;


/**
 *  A {@code WebServiceContext} makes it possible for
 *  a web service endpoint implementation class to access
 *  message context and security information relative to
 *  a request being served.
 *
 *  Typically a {@code WebServiceContext} is injected
 *  into an endpoint implementation class using the
 *  {@code Resource} annotation.
 *
 *  @since 1.6, JAX-WS 2.0
 *
 *  @see javax.annotation.Resource
 **/
public interface WebServiceContext {

    /**
     * Returns the {@code MessageContext} for the request being served
     * at the time this method is called. Only properties with
     * APPLICATION scope will be visible to the application.
     *
     * @return MessageContext The message context.
     *
     * @throws IllegalStateException This exception is thrown
     *         if the method is called while no request is
     *         being serviced.
     *
     * @see javax.xml.ws.handler.MessageContext
     * @see javax.xml.ws.handler.MessageContext.Scope
     * @see java.lang.IllegalStateException
     **/
    public MessageContext getMessageContext();

    /**
     * Returns the Principal that identifies the sender
     * of the request currently being serviced. If the
     * sender has not been authenticated, the method
     * returns {@code null}.
     *
     * @return Principal The principal object.
     *
     * @throws IllegalStateException This exception is thrown
     *         if the method is called while no request is
     *         being serviced.
     *
     * @see java.security.Principal
     * @see java.lang.IllegalStateException
     **/
    public Principal getUserPrincipal();

    /**
     * Returns a boolean indicating whether the
     * authenticated user is included in the specified
     * logical role. If the user has not been
     * authenticated, the method returns {@code false}.
     *
     * @param role  A {@code String} specifying the name of the role
     *
     * @return a {@code boolean} indicating whether
     * the sender of the request belongs to a given role
     *
     * @throws IllegalStateException This exception is thrown
     *         if the method is called while no request is
     *         being serviced.
     **/
    public boolean isUserInRole(String role);

    /**
     * Returns the {@code EndpointReference} for this
     * endpoint.
     * <p>
     * If the {@link Binding} for this {@code bindingProvider} is
     * either SOAP1.1/HTTP or SOAP1.2/HTTP, then a
     * {@code W3CEndpointReference} MUST be returned.
     *
     * @param referenceParameters Reference parameters to be associated with the
     * returned {@code EndpointReference} instance.
     * @return EndpointReference of the endpoint associated with this
     * {@code WebServiceContext}.
     * If the returned {@code EndpointReference} is of type
     * {@code W3CEndpointReference} then it MUST contain the
     * the specified {@code referenceParameters}.
     *
     * @throws IllegalStateException This exception is thrown
     *         if the method is called while no request is
     *         being serviced.
     *
     * @see W3CEndpointReference
     *
     * @since 1.6, JAX-WS 2.1
     */
    public EndpointReference getEndpointReference(Element... referenceParameters);

    /**
     * Returns the {@code EndpointReference} associated with
     * this endpoint.
     *
     * @param <T> The type of {@code EndpointReference}.
     * @param clazz The type of {@code EndpointReference} that
     * MUST be returned.
     * @param referenceParameters Reference parameters to be associated with the
     * returned {@code EndpointReference} instance.
     * @return EndpointReference of type {@code clazz} of the endpoint
     * associated with this {@code WebServiceContext} instance.
     * If the returned {@code EndpointReference} is of type
     * {@code W3CEndpointReference} then it MUST contain the
     * the specified {@code referenceParameters}.
     *
     * @throws IllegalStateException This exception is thrown
     *         if the method is called while no request is
     *         being serviced.
     * @throws WebServiceException If the {@code clazz} type of
     * {@code EndpointReference} is not supported.
     *
     * @since 1.6, JAX-WS 2.1
     **/
    public <T extends EndpointReference> T getEndpointReference(Class<T> clazz,
            Element... referenceParameters);
}
