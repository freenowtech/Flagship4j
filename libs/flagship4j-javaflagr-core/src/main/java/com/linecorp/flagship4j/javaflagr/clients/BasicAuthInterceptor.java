/*
 * Copyright 2024 LY Corporation
 *
 * LY Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.linecorp.flagship4j.javaflagr.clients;

import java.io.IOException;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor
{

    private String credentials;


    public BasicAuthInterceptor(final String user, final String password)
    {
        this.credentials = Credentials.basic(user, password);
    }


    @Override
    public Response intercept(final Chain chain) throws IOException
    {
        final Request request = chain.request();
        final Request authenticatedRequest = request.newBuilder()
            .header("Authorization", credentials)
            .build();
        return chain.proceed(authenticatedRequest);
    }
}