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
public class JVMStackTest {
    private int count;
    public void resursion(long a,long b,long c){
        long d=0;
        long e=0;
        long f=0;
        long h=0;
        count++;
        //resursion(a,b,c);
    }
    @Test
    public void test(){
        try{
            resursion(10,11,12);
        }catch (Throwable e){
            System.out.println("the deep of stack is "+count);
            e.printStackTrace();
        }

    }
}
