package com.easysoft.shop.member.manager.impl;

import com.easysoft.core.common.dao.spring.BaseSupport;
import com.easysoft.framework.db.PageOption;
import com.easysoft.framework.utils.StringUtil;
import com.easysoft.member.manager.IMemberLvManager;
import com.easysoft.member.model.MemberLv;
import com.easysoft.shop.member.dao.IMemberLvDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
 

/**
 * 会员级别管理
 * @author andy
 */
@Service("memberLvManager")
public class MemberLvManager extends BaseSupport<MemberLv> implements IMemberLvManager{
    @Autowired
    private IMemberLvDao memberLvDao;
	
	public void add(MemberLv lv) {
		this.baseDaoSupport.insert("member_lv", lv);
	}

	
	public void edit(MemberLv lv) {
		this.baseDaoSupport.update("member_lv", lv, "lv_id=" + lv.getLv_id());
	}

    public List getCatDiscountByLv(int lv_id) {
        String sql = "select d.*,c.name from " + getTableName("member_lv_discount") + " d inner join " + getTableName("goods_cat") + " c on d.cat_id=c.cat_id where d.lv_id=" + lv_id;
        return this.daoSupport.queryForList(sql, new Object[0]);
    }

    public Integer getDefaultLv() {
		String sql  ="select * from member_lv where default_lv=1";
		List<MemberLv> lvList  = this.baseDaoSupport.queryForList(sql, MemberLv.class);
		if(lvList==null || lvList.isEmpty()){
			return null;
		}
		
		return lvList.get(0).getLv_id();
	}

	
	public PageOption list(String order, int page, int pageSize) {
        DetachedCriteria dc = DetachedCriteria.forClass(MemberLv.class);
        if (order == null)
            dc.addOrder(Order.asc("lv_id"));
        else
            dc.addOrder(Order.asc(order));
        PageOption webpage =memberLvDao.queryForPage(dc,page,pageSize);
		return webpage;
	}

	
	public MemberLv get(Integer lvId) {
		if(lvId!=null&&lvId!=0){
			String sql = "select * from member_lv where lv_id=?";
			MemberLv lv = this.baseDaoSupport.queryForObject(sql, MemberLv.class,
				lvId);
			return lv;
		}else{
			return null;
		}
	}

	
	public void delete(String id) {
		if (id == null || id.equals(""))
			return;
		String sql = "delete from member_lv where lv_id in (" + id + ")";
		this.baseDaoSupport.execute(sql);
	}

	
	public List<MemberLv> list() {
		String hql = "from MemberLv l order by l.lv_id";
        List lvlist = memberLvDao.queryForList(hql,null);
		return lvlist;
	}

	
	public List<MemberLv> list(String ids) {
		
		if( StringUtil.isEmpty(ids)) return new ArrayList();
		
		String sql = "select * from member_lv where  lv_id in("+ids+") order by lv_id";
		List lvlist = this.baseDaoSupport.queryForList(sql, MemberLv.class);
		return lvlist;
		 
	}


	public MemberLv getByPoint(int point) {
		String sql = "select * from member_lv where  point<=? order by point desc";
		List<MemberLv> list =this.baseDaoSupport.queryForList(sql, MemberLv.class,point);
		if(list==null || list.isEmpty())
		return null;
		else
			return list.get(0);
	}


    public MemberLv getNextLv(int point) {
        String sql = "select * from member_lv where  point>? order by point ASC";
        List list = this.baseDaoSupport.queryForList(sql, MemberLv.class, new Object[]{Integer.valueOf(point)});
        if ((list == null) || (list.isEmpty())) {
            return null;
        }
        return (MemberLv) list.get(0);
    }


}
