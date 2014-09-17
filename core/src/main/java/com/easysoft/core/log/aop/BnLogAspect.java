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

package com.easysoft.core.log.aop;


import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.easysoft.core.log.BnLog;
import com.easysoft.core.log.BnLogConfiguration;
import com.easysoft.core.log.BnLogException;
import com.easysoft.core.log.annotation.BnLogItem;
import com.easysoft.core.log.annotation.BusinessLog;
import com.easysoft.core.log.annotation.State;
import com.easysoft.framework.exception.ErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : andy.huang
 * @since :
 */
@Component
@Aspect
public class BnLogAspect {
    private static final Log logger = LogFactory.getLog(BnLogAspect.class);
    public enum BnLogError{
        @ErrorCode(comment = "无效的业务类型:${businessType}")
        BUSINESS_TYPE_ERROR,
        @ErrorCode(comment = "无效的事件类型:${businessType}")
        EVENT_TYPE_ERROR,
        @ErrorCode(comment = "当state设置为INVALID时,info内容不能为空")
        INFO_IS_NEED,
        @ErrorCode(comment = "当state设置为VALID时,success和failture内容不能为空")
        SUCCESS_OR_FAIL_IS_NEED
    }
    @Autowired
    private BnLog bnLog;
    @Pointcut("@annotation(com.easysoft.core.log.annotation.BusinessLog)")
    private void bnLogAnnotation(){}
    @AfterReturning("bnLogAnnotation() && @annotation(businessLog)")
    public void afterReturning(BusinessLog businessLog){
        try{
            this.log(businessLog,true);
        }catch (BnLogException ex){
            logger.error("业务日志切面抛出异常",ex);
        }

    }
    @AfterThrowing(pointcut = "bnLogAnnotation() && @annotation(businessLog)", throwing = "e")
    public void afterThrowing(BusinessLog businessLog, Exception e) throws Exception{
        try{
            this.log(businessLog,false);
        }catch (BnLogException ex){
            logger.error("业务日志切面抛出异常",ex);
        }
    }
    private void log(BusinessLog businessLog ,boolean state){
        BnLogItem bnLogItem = new BnLogItem();
        if(!BnLogConfiguration.validateBusinessType(businessLog.businessType())){
            throw new BnLogException(BnLogError.BUSINESS_TYPE_ERROR,businessLog.businessType());
        }
        if(!BnLogConfiguration.validateEventType(businessLog.eventType())){
            throw new BnLogException(BnLogError.EVENT_TYPE_ERROR,businessLog.eventType());
        }

        if(State.INVALID == businessLog.state()){
            if(StringUtils.isEmpty(businessLog.info())){
                throw new BnLogException(BnLogError.INFO_IS_NEED);
            }
            bnLogItem.setMessage(businessLog.info());
        }else{
            if(StringUtils.isEmpty(businessLog.success())&&StringUtils.isEmpty(businessLog.failture())){
                throw new BnLogException(BnLogError.SUCCESS_OR_FAIL_IS_NEED);
            }
            if(state){
                bnLogItem.setMessage(businessLog.success());
            }else{
                bnLogItem.setMessage(businessLog.failture());
            }
        }
        bnLogItem.setBusinessType(businessLog.businessType());
        bnLogItem.setEventType(businessLog.eventType());
        bnLog.log(bnLogItem);

    }

    public void setBnLog(BnLog bnLog) {
        this.bnLog = bnLog;
    }
}
