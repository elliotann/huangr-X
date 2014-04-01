package com.easysoft.shop.member.manager.impl;

import com.easysoft.core.common.dao.spring.BaseSupport;
import com.easysoft.core.manager.ISettingService;
import com.easysoft.framework.utils.DateUtil;
import com.easysoft.framework.utils.StringUtil;
import com.easysoft.member.manager.IMemberLvManager;
import com.easysoft.member.manager.IMemberManager;
import com.easysoft.member.model.Member;
import com.easysoft.member.model.MemberLv;
import com.easysoft.shop.member.manager.IMemberPointManger;
import com.easysoft.shop.member.manager.IPointHistoryManager;
import com.easysoft.shop.model.FreezePoint;
import com.easysoft.shop.model.PointHistory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 会员积分管理
 * @author andy
 *
 */
public class MemberPointManger extends BaseSupport implements IMemberPointManger {
	private IPointHistoryManager pointHistoryManager;
	private IMemberManager memberManager;
	private IMemberLvManager memberLvManager;
	private ISettingService settingService ;
	public boolean checkIsOpen(String itemname) {
		String value = settingService.getSetting("point", itemname);
		
		return "1".equals(value);
	}

	 
	public int getItemPoint(String itemname) {
		String value = settingService.getSetting("point", itemname);
		if(StringUtil.isEmpty(value)){
			return 0;
		}
		return Integer.valueOf(value);
	}

	@Transactional(propagation = Propagation.REQUIRED)  
	public void useMarketPoint(int memberid,int point,String reson,Integer relatedId){
		PointHistory pointHistory = new  PointHistory();
		pointHistory.setMember_id(memberid);
		pointHistory.setOperator("member");
		pointHistory.setPoint(point);
		pointHistory.setReason(reson);
		pointHistory.setType(0);
		pointHistory.setPoint_type(1);
		pointHistory.setTime(System.currentTimeMillis());
		pointHistory.setRelated_id(relatedId);
		
		pointHistoryManager.addPointHistory(pointHistory);
		this.baseDaoSupport.execute("update member set mp=mp-? where member_id=?", point,memberid); 
	}
	
	@Transactional(propagation = Propagation.REQUIRED)  
	public void add(int memberid, int point, String itemname,Integer relatedId) {
		PointHistory pointHistory = new  PointHistory();
		pointHistory.setMember_id(memberid);
		pointHistory.setOperator("member");
		pointHistory.setPoint(point);
		pointHistory.setReason(itemname);
		pointHistory.setType(1);
		pointHistory.setPoint_type(0);
		pointHistory.setTime(System.currentTimeMillis());
		pointHistory.setRelated_id(relatedId);
		
		pointHistoryManager.addPointHistory(pointHistory);
		 
		Member member  = this.memberManager.get(memberid);
		Integer memberpoint  = member.getPoint();

		
		this.baseDaoSupport.execute("update member set point=point+? where member_id=?", point,memberid); 
		
		//改变会员等级
		if(memberpoint!=null ){
			MemberLv lv =  this.memberLvManager.getByPoint(memberpoint);
			if(lv!=null ){
				if(member.getLv_id()==null ||lv.getLv_id().intValue()!= member.getLv_id().intValue()){
					this.memberManager.updateLv(memberid, lv.getLv_id());
				}
			}
		}
	}
    @Transactional(propagation=Propagation.REQUIRED)
/*     */   public void add(int memberid, int point, String itemname, Integer relatedId, int mp)
/*     */   {
/* 222 */     PointHistory pointHistory = new PointHistory();
/* 223 */     pointHistory.setMember_id(memberid);
/* 224 */     pointHistory.setOperator("member");
/* 225 */     pointHistory.setPoint(point);
/* 226 */     pointHistory.setReason(itemname);
/* 227 */     pointHistory.setType(1);
/* 228 */     pointHistory.setTime(Long.valueOf(System.currentTimeMillis()));
/* 229 */     pointHistory.setRelated_id(relatedId);
/* 230 */     pointHistory.setMp(Integer.valueOf(mp));
/*     */
/* 232 */     this.pointHistoryManager.addPointHistory(pointHistory);
/*     */
/* 234 */     Member member = this.memberManager.get(Integer.valueOf(memberid));
/* 235 */     if (member == null) {
/* 236 */       this.logger.debug("获取用户失败 memberid is " + memberid);
/* 237 */       System.out.println("获取用户失败memberid is" + memberid);
/* 238 */       this.baseDaoSupport.execute("delete from freeze_point where memberid=?", new Object[] { Integer.valueOf(memberid) });
/*     */     } else {
/* 240 */       Integer memberpoint = member.getPoint();
/*     */
/* 243 */       this.baseDaoSupport.execute("update member set point=point+?,mp=mp+? where member_id=?", new Object[] { Integer.valueOf(point), Integer.valueOf(mp), Integer.valueOf(memberid) });
/*     */
/* 246 */       if (memberpoint != null) {
/* 247 */         MemberLv lv = this.memberLvManager.getByPoint(memberpoint.intValue() + point);
/* 248 */         if ((lv != null) && (
/* 249 */           (member.getLv_id() == null) || (lv.getLv_id().intValue() > member.getLv_id().intValue())))
/*     */         {
/* 254 */           this.memberManager.updateLv(memberid, lv.getLv_id().intValue());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
    public int getFreezeMpByMemberId(int memberid) {
        return this.baseDaoSupport.queryForInt("SELECT SUM(mp) FROM freeze_point WHERE memberid=?", new Object[]{Integer.valueOf(memberid)});
    }
	public IPointHistoryManager getPointHistoryManager() {
		return pointHistoryManager;
	}


	public void setPointHistoryManager(IPointHistoryManager pointHistoryManager) {
		this.pointHistoryManager = pointHistoryManager;
	}


	public ISettingService getSettingService() {
		return settingService;
	}


	public void setSettingService(ISettingService settingService) {
		this.settingService = settingService;
	}


	public IMemberManager getMemberManager() {
		return memberManager;
	}


	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}


	public IMemberLvManager getMemberLvManager() {
		return memberLvManager;
	}


	public void setMemberLvManager(IMemberLvManager memberLvManager) {
		this.memberLvManager = memberLvManager;
	}

    public int getFreezePointByMemberId(int memberid) {
        return this.baseDaoSupport.queryForInt("SELECT SUM(point) FROM freeze_point WHERE memberid=?", new Object[]{Integer.valueOf(memberid)});
    }
    public void addFreezePoint(FreezePoint freezePoint, String memberName)
/*     */   {
/* 150 */     if (freezePoint == null) throw new IllegalArgumentException("param freezePoint is NULL");
/* 151 */     if (freezePoint.getMemberid() == 0) throw new IllegalArgumentException("param freezePoint.memberid is zero");
/*     */
/* 155 */     String reson = "";
/* 156 */     if ("register_link".equals(freezePoint.getType()))
/*     */     {
/* 158 */       reson = "register_link";
/*     */     }
/*     */
/* 161 */     if ("buygoods".equals(freezePoint.getType()))
/*     */     {
/* 163 */       reson = "buygoods";
/*     */     }
/*     */
/* 166 */     if ("onlinepay".equals(freezePoint.getType()))
/*     */     {
/* 168 */       reson = "onlinepay";
/*     */     }
/*     */
/* 183 */     freezePoint.setDateline(DateUtil.getDateline());
/* 184 */     this.baseDaoSupport.insert("freeze_point", freezePoint);
/*     */   }
    public List<FreezePoint> listByBeforeDay(int beforeDayNum)
/*     */   {
/* 136 */     int f = beforeDayNum * 24 * 60 * 60;
/*     */
/* 138 */     int now = DateUtil.getDateline();
/* 139 */     String sql = "select fp.*,o.status as order_status from " + getTableName("freeze_point") + "  fp inner join " + getTableName("order") + " o on fp.orderid= o.order_id  where  " + now + "-dateline>=" + f;
/*     */
/* 141 */     return this.daoSupport.queryForList(sql, FreezePoint.class, new Object[0]);
/*     */   }
    public void thaw(FreezePoint fp, boolean ismanual)
/*     */   {
/*  44 */     String reson = "";
/*  45 */     if ("register_link".equals(fp.getType())) {
/*  46 */       reson = "推荐会员购买商品完成，积分解冻";
/*     */     }
/*     */
/*  49 */     if ("buygoods".equals(fp.getType())) {
/*  50 */       if (ismanual)
/*  51 */         reson = "购买商品,用户提前解冻积分";
/*     */       else {
/*  53 */         reson = "购买商品满15天积分解冻";
/*     */       }
/*     */
/*     */     }
/*     */
/*  58 */     if ("onlinepay".equals(fp.getType())) {
/*  59 */       if (ismanual)
/*  60 */         reson = "在线支付购买商品,用户提前解冻积分";
/*     */       else {
/*  62 */         reson = "在线支付购买商品满15天积分解冻";
/*     */       }
/*     */     }
    }
        public void deleteByOrderId(Integer orderId)
/*     */   {
/* 267 */     String sql = "delete from freeze_point where orderid=" + orderId;
/* 268 */     this.baseDaoSupport.execute(sql, new Object[0]);
/*     */   }
}
