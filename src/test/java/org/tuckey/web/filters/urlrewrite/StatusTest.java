/**
 * Copyright (c) 2005-2023, Paul Tuckey
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
package org.tuckey.web.filters.urlrewrite;

import junit.framework.TestCase;
import org.tuckey.web.testhelper.MockRequest;
import org.tuckey.web.testhelper.MockServletContext;
import org.tuckey.web.filters.urlrewrite.utils.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Paul Tuckey
 * @version $Revision: 1 $ $Date: 2006-08-01 21:40:28 +1200 (Tue, 01 Aug 2006) $
 */
public class StatusTest extends TestCase {

    public void setUp() {
        Log.setLevel("DEBUG");
    }


    public void testSimple() throws IOException {

        MockRequest hsRequest = new MockRequest();
        InputStream is = ConfTest.class.getResourceAsStream(ConfTest.BASE_XML_PATH + "conf-test1.xml");
        Conf conf = new Conf(new MockServletContext(), is, "conf-test1.xml", "conf-test1.xml");
        assertTrue(conf.getErrors().toString(), conf.isOk());
        UrlRewriter urlRewriter = new UrlRewriter(conf);
        UrlRewriteFilter urlRewriteFilter = new UrlRewriteFilter();

        Status status = new Status(urlRewriter.getConf(), urlRewriteFilter);
        status.displayStatusInContainer(hsRequest);
        assertNotNull(status.getBuffer());
        assertFalse(status.getBuffer().length() == 0);

        // save it so we can view it
        File buildDir = new File("build");
        if ( ! buildDir.exists() ) buildDir.mkdir();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(buildDir, "status.html")));
        try {
            bos.write(status.getBuffer().toString().getBytes());
        } finally {
            bos.close();
        }

    }

}
