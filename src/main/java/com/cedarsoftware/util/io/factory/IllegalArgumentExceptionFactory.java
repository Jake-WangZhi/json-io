package com.cedarsoftware.util.io.factory;

import com.cedarsoftware.util.io.JsonObject;

/**
 * Factory class to create IllegalArgumentException instances.  Needed for JDK17+ as the only way to set the
 * 'detailMessage' field on an IllegalArgumentException is via its constructor.
 * <p>
 * @author John DeRegnaucourt (jdereg@gmail.com)
 *         <br>
 *         Copyright (c) Cedar Software LLC
 *         <br><br>
 *         Licensed under the Apache License, Version 2.0 (the "License");
 *         you may not use this file except in compliance with the License.
 *         You may obtain a copy of the License at
 *         <br><br>
 *         http://www.apache.org/licenses/LICENSE-2.0
 *         <br><br>
 *         Unless required by applicable law or agreed to in writing, software
 *         distributed under the License is distributed on an "AS IS" BASIS,
 *         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *         See the License for the specific language governing permissions and
 *         limitations under the License.
 */
public class IllegalArgumentExceptionFactory extends ThrowableFactory
{
    protected Throwable createException(String msg, Throwable cause)
    {
        return new IllegalArgumentException(msg, cause);
    }
}