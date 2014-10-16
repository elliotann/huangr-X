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

package com.easysoft.build.manager;

import com.easysoft.build.model.BuildDetailData;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @author : andy.huang
 * @since :
 */
public class BuildDetailiDataService {

    public void validBuildNo(String[] nos){
        String rs = "";
        for(String no:nos){
            if(this.getBuildDetailDataNoByNo(no)==null){
                rs =rs+ "["+no+"]";
            }
        }
        if(StringUtils.isNotEmpty(rs)){
            throw new RuntimeException("单号："+rs+"不存在");
        }
    }
    public BuildDetailData getBuildDetailDataNoByNo(String no){
        return null;//dao.getBuildDetailDataNoByNo(no);
    }

}
