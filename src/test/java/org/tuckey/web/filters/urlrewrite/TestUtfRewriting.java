package org.tuckey.web.filters.urlrewrite;

import org.junit.Before;
import org.junit.Test;
import org.tuckey.web.filters.urlrewrite.utils.RewriteUtils;
import org.tuckey.web.testhelper.MockFilterChain;
import org.tuckey.web.testhelper.MockRequest;
import org.tuckey.web.testhelper.MockResponse;

import jakarta.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

public class TestUtfRewriting {
    MockResponse response;
    MockRequest request;

    private final String to = "/красота-от-природы/cловарь-ингредиентов/";
    private final String from = "/красота-от-природы";

    MockFilterChain chain;

    @Before
    public void setUp() {
        response = new MockResponse();
        request = new MockRequest();
        chain = new MockFilterChain();
    }

    @Test
    public void testSimple() throws Exception {

        final NormalRule rule = new NormalRule();
        rule.setFrom("/products");
        rule.setTo(to);
        rule.initialise(null);

        final MockRequest request = new MockRequest("/products");
        final String requestURI = request.getRequestURI();
        final RewrittenUrl rewrittenUrl = rule.matches(requestURI, request, response);
        assertEquals(to, rewrittenUrl.getTarget());
    }

    public void testCyrillicEncoding() throws Exception {

    }
    @Test
    public void testSimpleCyrillic() throws Exception {

        final NormalRule rule = new NormalRule();
        rule.setFrom(from);

        rule.setToType("permanent-redirect");
        rule.setTo(to);
        rule.initialise(null);

        final MockRequest request = new MockRequest(from);
        final String requestURI = request.getRequestURI();
        final RewrittenUrl rewrittenUrl = rule.matches(requestURI, request, response);
        assertEquals(to, rewrittenUrl.getTarget());
    }


    @Test
    public void testRedirect1() throws Exception {

        final NormalRewrittenUrl rewrittenUrl = new NormalRewrittenUrl(to);
        rewrittenUrl.setTemporaryRedirect(true);
        rewrittenUrl.doRewrite(request, response, chain);

        assertEquals(RewriteUtils.uriEncodeParts(to), response.getHeader("Location"));
        assertEquals(HttpServletResponse.SC_MOVED_TEMPORARILY, response.getStatus());
    }

    @Test
    public void testRedirect2() throws Exception {

        final String target = "^/Pomegranate+natural+beauty";
        final NormalRewrittenUrl rewrittenUrl = new NormalRewrittenUrl(target);
        rewrittenUrl.setTemporaryRedirect(true);
        rewrittenUrl.doRewrite(request, response, chain);

        assertEquals(RewriteUtils.uriEncodeParts(target), response.getHeader("Location"));
        assertEquals(HttpServletResponse.SC_MOVED_TEMPORARILY, response.getStatus());
    }
}
