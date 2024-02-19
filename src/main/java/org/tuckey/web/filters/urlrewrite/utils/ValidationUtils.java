/**
 * Copyright (c) 2017-2024, Bloomreach
 * All rights reserved.
 * ====================================================================
 * Licensed under the BSD License. Text as follows.
 * <p>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * <p>
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Redistributions in binary form must reproduce the above
 *     copyright notice, this list of conditions and the following
 *     disclaimer in the documentation and/or other materials provided
 *     with the distribution.
 *   - Neither the name tuckey.org nor the names of its contributors
 *     may be used to endorse or promote products derived from this
 *     software without specific prior written permission.
 * <p>
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 */
package org.tuckey.web.filters.urlrewrite.utils;

import java.util.regex.Pattern;

/**
 * Input validation utilities
 */
public final class ValidationUtils {

    private static final Pattern CR_OR_LF = Pattern.compile("\\r|\\n");

    private ValidationUtils() {
    }

    /**
     * Check name and value for CWE-113 (HTTP Response Splitting)
     *
     * @param key   key
     * @param value value
     *              see https://cwe.mitre.org/data/definitions/113.html
     */
    public static void validateNewLines(String key, String value) {
        if (containsNewLine(key) || containsNewLine(value)) {
            throw new IllegalArgumentException("Invalid characters found (CR/LF) in header or cookie key: "
                    + key + " value: "
                    + value);
        }
    }

    /**
     * Check name and value for CWE-113 (HTTP Response Splitting)
     *
     * @param value value
     *              see https://cwe.mitre.org/data/definitions/113.html
     */
    public static void validateNewLines(String value) {
        if (containsNewLine(value)) {
            throw new IllegalArgumentException("Invalid characters found (CR/LF) in header or cookie " + value);
        }
    }

    private static boolean containsNewLine(String value) {
        return value != null && CR_OR_LF.matcher(value).find();
    }
}
