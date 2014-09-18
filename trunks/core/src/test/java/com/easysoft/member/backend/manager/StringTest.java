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

import java.util.ArrayList;
import java.util.List;

/**
 * @author : andy.huang
 * @since :
 */
public class StringTest {
    public static void main(String[] args) {
        /*String str1 = "abc";
        String str2 = new String("abc");
        *//*Integer i1= Integer.MAX_VALUE;
        Integer i2 = Integer.MAX_VALUE;*//*
        Integer i1 = 127;
        Integer i2 =127;
        System.out.println(i1==i2);*/
        List<String> handler = new ArrayList<String>();
        for(int i=0;i<1000;i++){
           HugeStr h = new HugeStr();
            //ImprovedHugeStr h = new ImprovedHugeStr();
            handler.add(h.subString(1,5));
        }
    }

    static class HugeStr {
        private String str = new String(new char[100000]);
        public String subString(int begin,int end){
            return str.substring(begin,end);
        }
    }
    static class ImprovedHugeStr{
        private String str = new String(new char[100000]);
        public String subString(int begin,int end){
            return new String(str.substring(begin,end));
        }
    }
}
