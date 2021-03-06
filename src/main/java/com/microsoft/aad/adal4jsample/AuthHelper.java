/*******************************************************************************
 // Copyright (c) Microsoft Corporation.
 // All rights reserved.
 //
 // This code is licensed under the MIT License.
 //
 // Permission is hereby granted, free of charge, to any person obtaining a copy
 // of this software and associated documentation files(the "Software"), to deal
 // in the Software without restriction, including without limitation the rights
 // to use, copy, modify, merge, publish, distribute, sublicense, and / or sell
 // copies of the Software, and to permit persons to whom the Software is
 // furnished to do so, subject to the following conditions :
 //
 // The above copyright notice and this permission notice shall be included in
 // all copies or substantial portions of the Software.
 //
 // THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 // IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 // FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.IN NO EVENT SHALL THE
 // AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 // LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 // OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 // THE SOFTWARE.
 ******************************************************************************/
package com.microsoft.aad.adal4jsample;

import com.microsoft.aad.adal4j.AuthenticationResult;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public final class AuthHelper {

    public static final String PRINCIPAL_SESSION_NAME = "principal";

    private AuthHelper() {
    }

    public static boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession().getAttribute(PRINCIPAL_SESSION_NAME) != null;
    }

    public static AuthenticationResult getAuthSessionObject(HttpServletRequest request) {
        return (AuthenticationResult) request.getSession().getAttribute(PRINCIPAL_SESSION_NAME);
    }

    public static boolean containsAuthenticationData(HttpServletRequest httpRequest) {
        final boolean isPostMethod = httpRequest.getMethod().equalsIgnoreCase("POST");

        final Map<String, String[]> map = httpRequest.getParameterMap();

        final boolean hasAnyRequiredParameter = map.containsKey(AuthParameterNames.ERROR)
                || map.containsKey(AuthParameterNames.ID_TOKEN)
                || map.containsKey(AuthParameterNames.CODE);

        return (isPostMethod && hasAnyRequiredParameter);
    }

    public static boolean isAuthenticationSuccessful(AuthenticationResponse authResponse) {
        return (authResponse instanceof AuthenticationSuccessResponse);
    }
}
