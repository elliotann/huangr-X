package com.easysoft.member.dispatcher.processor.backend;

import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.easysoft.core.dispatcher.core.Response;
import com.easysoft.core.dispatcher.processor.Processor;
import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.framework.utils.EncryptionUtil1;
import com.easysoft.framework.utils.HttpUtil;
import com.easysoft.member.backend.manager.impl.UserServiceFactory;
import com.easysoft.member.manager.IMemberManager;

/**
 * User: andy
 * Date: 13-8-15
 * Time: 上午9:11
 *
 * @since:
 */
@Service("autoLoginProcessor")
@Lazy(true)
public class AutoLoginProcessor
        implements Processor
{
    private IMemberManager memberManager;

    public Response process(int mode, HttpServletResponse httpResponse, HttpServletRequest httpRequest)
    {
        try
        {
            String url = httpRequest.getRequestURI();
            if (url != null) {
                url = url.toLowerCase();
                if ((url.endsWith("/")) || (url.endsWith(".html")) || (url.endsWith(".do")))
                {
                    if (memberManager.getCurrentMember() == null) {
                        String cookieValue = HttpUtil.getCookieValue(ThreadContextHolder.getHttpRequest(), "EShopUser");

                        if ((cookieValue != null) && (!cookieValue.equals(""))) {
                            cookieValue = URLDecoder.decode(cookieValue, "UTF-8");

                            cookieValue = EncryptionUtil1.authcode(cookieValue, "DECODE", "", 0);

                            if ((cookieValue != null) && (!cookieValue.equals("")))
                            {
                                Map map = (Map) JSONObject.toBean(JSONObject.fromObject(cookieValue), Map.class);

                                if (map != null) {
                                    String username = map.get("username").toString();

                                    String password = map.get("password").toString();

                                    int result = this.memberManager.loginWithCookie(username, password);

                                    if (result != 1)
                                        HttpUtil.addCookie(ThreadContextHolder.getHttpResponse(), "EShopUser", "", 0);
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex)
        {
        }
        return null;
    }

    public void setMemberManager(IMemberManager memberManager) {
        this.memberManager = memberManager;
    }

    public static void main(String[] args)
    {
        String str1 = "db21SPFxwCWgshcLqsIxFzS9YeEusB/qzRdC1OKk2R47uLdLCuai1BPUMh5xNJhSkwuu1v09po2qNuLPsWjLg/+p4aaeZZ70LyCEGwcwMZGuHCY9zmKDv1sXBZKQ6OXjFV04MQ";
        System.out.println(EncryptionUtil1.authcode(str1, "DECODE", "", 0));
    }
}