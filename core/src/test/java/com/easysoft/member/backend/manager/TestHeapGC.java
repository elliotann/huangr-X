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
public class TestHeapGC {
    public static void main(String[] args) {
        /*byte[] a = new byte[1024*1024/2];
        byte[] b = new byte[1024*1024*8];
        b=null;
        b = new byte[1024*1024*8];
        System.gc();*/
        /*for(int i=0;i<Integer.MAX_VALUE;i++){
            String.valueOf(i).intern();
        }*/
        /*List list = new ArrayList();
        for(int i=1;i<=10;i++){
            byte[] a = new byte[1024*1024];
            list.add(a);
            System.out.println(i+"m is allocated!");
        }
        System.out.println("max memory is "+Runtime.getRuntime().maxMemory()/1024/1024+"m");*/


        List list = new ArrayList();
        for(int i=1;i<=10;i++){
            byte[] a = new byte[1024*1024];
            list.add(a);
            if(list.size()==3){
                list.clear();
            }

        }
    }
}
