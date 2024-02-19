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

import java.net.URI;

public final class RewriteUtils {

    private static final Log log = Log.getLog(RewriteUtils.class);

    private RewriteUtils() {
    }

    /**
     * Bloomreach modification on behalf of non-ASCII characters, see issue HIPPLUG-1419.
     *
     * No longer actually encoding 'parts' but we need to encode each character separately.
     * Not very efficient but good enough (100_000 rules ~4 sec),
     */
    public static String uriEncodeParts(final String value) {
        if (StringUtils.isBlank(value)) {
            return value;
        }
        final int length = value.length();
        final StringBuilder builder = new StringBuilder(length << 1);
        for (int i = 0; i < length; i++) {
            final char c = value.charAt(i);
            if (isASCII(c)) {
                builder.append(c);
            } else {
                builder.append(encodeCharacter(c));
            }
        }
        return builder.toString();
    }


    private static String encodeCharacter(final char c) {
        try {
            return URI.create(String.valueOf(c)).toASCIIString();
        } catch (Exception e) {
            log.error("Error parsing character '" + c + "'", e);
        }
        return String.valueOf(c);
    }


    /**
     * Bloomreach addition on behalf of non-ASCII characters, original issue HIPPLUG-1419
     */
    public static String encodeRedirect(final String target) {
        boolean allAscii = isAllASCII(target);
        if (!allAscii) {
            try {
                return URI.create(target).toASCIIString();
            } catch (Exception e) {
                log.error("Invalid target uri: " + target, e);
            }
        }
        return target;
    }

    private static boolean isASCII(char c) {
        return c <= '\u007f';
    }

    private static boolean isAllASCII(CharSequence sequence) {
        for (int i = sequence.length() - 1; i >= 0; i--) {
            if (!isASCII(sequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
