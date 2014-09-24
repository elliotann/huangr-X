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

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * @author : andy.huang
 * @since :
 */
public class MyObject {
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("MyObject finalize is called!");
    }

    @Override
    public String toString() {
        return "I am MyObject";
    }

    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> softQueue = new ReferenceQueue<MyObject>();
        SoftReference<MyObject> softReference = new SoftReference<MyObject>(myObject,softQueue);
        new CheckRefQueue().start();
    }
}
