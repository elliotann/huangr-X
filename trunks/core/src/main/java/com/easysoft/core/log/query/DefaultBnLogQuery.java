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

package com.easysoft.core.log.query;

import com.easysoft.core.log.annotation.BnLogItem;
import com.easysoft.core.log.support.Appender;
import com.easysoft.framework.db.PageOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : andy.huang
 * @since :
 */
@Component("bnLogQuery")
public class DefaultBnLogQuery implements IBnLogQuery {
    @Autowired
    private Appender appender;

    public PageOption queryForPage(PageOption pageOption) {
        List<BnLogItem> bnLogItems = appender.queryForPage(pageOption);
        if(!bnLogItems.isEmpty()){
            pageOption.setData(bnLogItems);
        }
        return pageOption;
    }

    public void setAppender(Appender appender) {
        this.appender = appender;
    }
}
