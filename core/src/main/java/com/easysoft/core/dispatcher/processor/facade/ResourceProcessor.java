package com.easysoft.core.dispatcher.processor.facade;

import com.easysoft.core.resource.impl.ResourceBuilder;
import com.easysoft.core.dispatcher.processor.Processor;
import com.easysoft.core.dispatcher.core.InputStreamResponse;
import com.easysoft.core.dispatcher.core.Response;
import com.easysoft.core.dispatcher.core.StringResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * User: andy
 *
 * @since:
 */
public class ResourceProcessor implements Processor
{
    public Response process(int mode, HttpServletResponse httpResponse, HttpServletRequest httpRequest)
    {
        String id = httpRequest.getParameter("id");
        String type = httpRequest.getParameter("type");

        String content = null;

        if ("javascript".equals(type)) {
            content = ResourceBuilder.getScript(id);
        }

        if ("css".equals(type)) {
            content = ResourceBuilder.getCss(id);
        }

        if (content != null) {
            InputStream in = null;
            try {
                in = new ByteArrayInputStream(content.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Response response = new InputStreamResponse(in);
            if ("javscript".equals(type)) {
                response.setContentType("application/x-javascript");
            }
            if ("css".equals(type)) {
                response.setContentType("text/css");
            }
            return response;
        }
        Response response = new StringResponse();
        return response;
    }
}
