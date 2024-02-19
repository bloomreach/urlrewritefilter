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

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidationUtilsTest {

    @Test
    public void ok() {
        ValidationUtils.validateNewLines("test", "me");
        assertTrue(true);
        ValidationUtils.validateNewLines("test");
        assertTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateNewLinesKeyValue1() {
        ValidationUtils.validateNewLines("test", "foo\nbar");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateNewLinesKeyValue11() {
        ValidationUtils.validateNewLines("te\nst", "foobar");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateNewLinesKeyValue2() {
        ValidationUtils.validateNewLines("test", "foo\rbar");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateNewLinesKeyValue3() {
        ValidationUtils.validateNewLines("test", "foo\n\rbar");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateNewLinesValue1() {
        ValidationUtils.validateNewLines("foo\nbar");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateNewLinesValue2() {
        ValidationUtils.validateNewLines("foo\rbar");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateNewLinesValue3() {
        ValidationUtils.validateNewLines("foo\r\n");
    }
}
