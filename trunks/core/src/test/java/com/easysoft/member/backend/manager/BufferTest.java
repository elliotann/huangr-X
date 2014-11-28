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

import java.io.*;

/**
 * @author : andy.huang
 * @since :
 */
public class BufferTest {
    public static void main(String[] args) {
        noBuffer();
        buffer();
    }

    public static void noBuffer(){
        int CICLE=100000;
        try {
            Writer writer = new FileWriter(new File("1.txt"));
            long begin = System.currentTimeMillis();
            for(int i=0;i<CICLE;i++){
                writer.write(i);
            }
            writer.close();
            System.out.println("use time is = "+(System.currentTimeMillis()-begin));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void buffer(){
        int CICLE=100000;
        try {
            Writer writer = new BufferedWriter(new FileWriter(new File("1.txt")));
            long begin = System.currentTimeMillis();
            for(int i=0;i<CICLE;i++){
                writer.write(i);
            }
            writer.close();
            System.out.println("use time is = "+(System.currentTimeMillis()-begin));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
