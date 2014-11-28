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

import org.junit.Test;

/**
 * @author : andy.huang
 * @since :
 */
public class TestWord {
    public void test1(){
        {
            byte[] a = new byte[1024*1024];
            a=null;
        }
        long b=2;
        System.gc();
        System.out.println("gc start!");
    }
    public void test2(){
        long a=1;
        long b=2;
    }
    @Test
    public void testGc(){
        test1();
    }
}
