/*
 *
 *  * Copyright 1999-2011 jeap Group Holding Ltd.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.easysoft.member.backend.manager;

import com.taobao.api.internal.util.WebUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
public class GCDetailsTest {
    private static final int _1MB = 1024*1024;
    public static void main(String[] args) {
        String url="https://oauth.taobao.com/token";
        Map<String,String> props=new HashMap<String,String>();
        props.put("grant_type","authorization_code");

        /*测试时，需把test参数换成自己应用对应的值*/
        props.put("code","21664029");
        props.put("client_id","21664029");
        props.put("client_secret","21664029");
        props.put("redirect_uri","http://ezshop.com");
        props.put("view","web");
        String s="";
        try{
            s= WebUtils.doPost(url, props, 30000, 30000);
            System.out.println(s);
        }catch(IOException e){
            e.printStackTrace();}
    }


}

