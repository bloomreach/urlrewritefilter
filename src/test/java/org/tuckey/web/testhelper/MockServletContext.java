/**
 * Copyright (c) 2005-2007, Paul Tuckey
 * All rights reserved.
 * ====================================================================
 * Licensed under the BSD License. Text as follows.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Redistributions in binary form must reproduce the above
 *     copyright notice, this list of conditions and the following
 *     disclaimer in the documentation and/or other materials provided
 *     with the distribution.
 *   - Neither the name tuckey.org nor the names of its contributors
 *     may be used to endorse or promote products derived from this
 *     software without specific prior written permission.
 *
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
package org.tuckey.web.testhelper;

import org.tuckey.web.filters.urlrewrite.UrlRewriteFilterTest;
import org.tuckey.web.filters.urlrewrite.utils.Log;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import javax.servlet.descriptor.JspConfigDescriptor;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * @author Paul Tuckey
 * @version $Revision: 1 $ $Date: 2006-08-01 21:40:28 +1200 (Tue, 01 Aug 2006) $
 */
public class MockServletContext implements ServletContext {

    private static Log log = Log.getLog(MockServletContext.class);
    private final Hashtable attributes = new Hashtable();

    @Override
    public String getContextPath() {
        return "/site";
    }

    public ServletContext getContext(String s) {
        return new MockServletContext();
    }

    public Object getAttribute(String name) {
    	return this.attributes.get(name);
    }
    
    public int getMajorVersion() {
        return 0;
    }

    public int getMinorVersion() {
        return 0;
    }

    @Override
    public int getEffectiveMajorVersion() {
        return 0;
    }

    @Override
    public int getEffectiveMinorVersion() {
        return 0;
    }

    public String getMimeType(String s) {
        return null;
    }

    public Set getResourcePaths(String s) {
        return null;
    }

    public URL getResource(String s) throws MalformedURLException {
        return null;
    }

    public InputStream getResourceAsStream(String s) {
        return null;
    }

    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }

    public RequestDispatcher getNamedDispatcher(String s) {
        return null;
    }

    public Servlet getServlet(String s) throws ServletException {
        return null;
    }

    public Enumeration getServlets() {
        return null;
    }

    public Enumeration getServletNames() {
        return null;
    }

    public void log(String s) {

    }

    public void log(Exception e, String s) {

    }

    public void log(String s, Throwable throwable) {

    }

    public String getRealPath(String s) {
        URL url = UrlRewriteFilterTest.class.getResource("conf-test1.xml");
        if ( url == null ) {
            log.error("could not get base path for comparison");
            return null;
        }   else {
            String basePath = url.getFile();
            log.debug("TEST ONLY using base path of " + basePath);
            if (basePath.endsWith("conf-test1.xml")) basePath = basePath.substring(0, basePath.length() - "conf-test1.xml".length());
            if (basePath.endsWith("/")) basePath = basePath.substring(0, basePath.length() - 1);
            return basePath + (s == null ? "" : s);
        }
    }

    public String getServerInfo() {
        return null;
    }

    public String getInitParameter(String s) {
        return null;
    }

    public Enumeration getInitParameterNames() {
        return null;
    }

    @Override
    public boolean setInitParameter(final String name, final String value) {
        return false;
    }

    public Enumeration getAttributeNames() {
        return null;
    }

    public void setAttribute(String name, Object value) {
    	if (value != null) {
    		this.attributes.put(name, value);
    	}
    	else {
    		 this.attributes.remove(name);
    	}
    }

    public void removeAttribute(String s) {

    }

    public String getServletContextName() {
        return null;
    }

    @Override
    public ServletRegistration.Dynamic addServlet(final String servletName, final String className) {
        return null;
    }

    @Override
    public ServletRegistration.Dynamic addServlet(final String servletName, final Servlet servlet) {
        return null;
    }

    @Override
    public ServletRegistration.Dynamic addServlet(final String servletName, final Class<? extends Servlet> servletClass) {
        return null;
    }

    @Override
    public <T extends Servlet> T createServlet(final Class<T> clazz) throws ServletException {
        return null;
    }

    @Override
    public ServletRegistration getServletRegistration(final String servletName) {
        return null;
    }

    @Override
    public Map<String, ? extends ServletRegistration> getServletRegistrations() {
        return null;
    }

    @Override
    public FilterRegistration.Dynamic addFilter(final String filterName, final String className) {
        return null;
    }

    @Override
    public FilterRegistration.Dynamic addFilter(final String filterName, final Filter filter) {
        return null;
    }

    @Override
    public FilterRegistration.Dynamic addFilter(final String filterName, final Class<? extends Filter> filterClass) {
        return null;
    }

    @Override
    public <T extends Filter> T createFilter(final Class<T> clazz) throws ServletException {
        return null;
    }

    @Override
    public FilterRegistration getFilterRegistration(final String filterName) {
        return null;
    }

    @Override
    public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
        return null;
    }

    @Override
    public SessionCookieConfig getSessionCookieConfig() {
        return null;
    }

    @Override
    public void setSessionTrackingModes(final Set<SessionTrackingMode> sessionTrackingModes) {

    }

    @Override
    public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
        return null;
    }

    @Override
    public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
        return null;
    }

    @Override
    public void addListener(final String className) {

    }

    @Override
    public <T extends EventListener> void addListener(final T t) {

    }

    @Override
    public void addListener(final Class<? extends EventListener> listenerClass) {

    }

    @Override
    public <T extends EventListener> T createListener(final Class<T> clazz) throws ServletException {
        return null;
    }

    @Override
    public JspConfigDescriptor getJspConfigDescriptor() {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }

    @Override
    public void declareRoles(final String... roleNames) {

    }
}
