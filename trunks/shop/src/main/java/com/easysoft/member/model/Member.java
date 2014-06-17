package com.easysoft.member.model;


import javax.persistence.*;

@Entity
@Table(name="t_member")
public class Member implements java.io.Serializable {

	private Integer member_id;
	
	private Integer lv_id;

	private String uname;

	private String email;

	private String password;

	private Long regtime;

	private String pw_answer;

	private String pw_question;

	private String name;

	private Integer sex;

	private Long birthday;
	
	private Double advance;

	private Integer province_id;
	
	private Integer city_id;
	
	private Integer region_id;
	
	private String province;
	
	private String city;
	
	private String region;

	private String address;

	private String zip;

	private String mobile;

	private String tel;

 
	
	private Integer point;
	
	private String qq;
	
	private String msn;
	
	private String remark;
	
	private long lastlogin;
	
	private Integer logincount;
	
	private int mp;  //消费积分 
	
	//会员等级名称，非数据库字段
	private String lvname;
	
	private int parentid; //父代理商id
	private Integer agentid; //代理商id
	
	private int is_cheked; //是否已验证
	private String registerip; // 注册IP
    private int info_full;
    private String face;
    private String nickname;
    private Integer midentity;
	private String find_code;
    private int recommend_point_state;
    private Integer last_send_email;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="member_id")
	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer memberId) {
		member_id = memberId;
	}
    @Column(name ="lv_id")
	public Integer getLv_id() {
		//lv_id = lv_id==null?0:lv_id;
		return lv_id;
	}

	public void setLv_id(Integer lvId) {
		lv_id = lvId;
	}
    @Column(name ="uname")
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
    @Column(name ="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    @Column(name ="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    @Column(name ="regtime")
	public Long getRegtime() {
		return regtime;
	}

	public void setRegtime(Long regtime) {
		this.regtime = regtime;
	}
    @Column(name ="pw_answer")
	public String getPw_answer() {
		return pw_answer;
	}

	public void setPw_answer(String pwAnswer) {
		pw_answer = pwAnswer;
	}
    @Column(name ="pw_question")
	public String getPw_question() {
		return pw_question;
	}

	public void setPw_question(String pwQuestion) {
		pw_question = pwQuestion;
	}
    @Column(name ="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    @Column(name ="sex")
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
    @Column(name ="birthday")
	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}
    @Column(name ="advance")
	public Double getAdvance() {
		return advance;
	}

	public void setAdvance(Double advance) {
		this.advance = advance;
	}
    @Column(name ="province_id")
	public Integer getProvince_id() {
		return province_id;
	}

	public void setProvince_id(Integer provinceId) {
		province_id = provinceId;
	}
    @Column(name ="city_id")
	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer cityId) {
		city_id = cityId;
	}
    @Column(name ="region_id")
	public Integer getRegion_id() {
		return region_id;
	}

	public void setRegion_id(Integer regionId) {
		region_id = regionId;
	}
    @Column(name ="province")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
    @Column(name ="city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
    @Column(name ="region")
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
    @Column(name ="address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    @Column(name ="zip")
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
    @Column(name ="mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
    @Column(name ="tel")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

    @Column(name ="point")
	public Integer getPoint() {
		if(point==null) point=0;
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}
    @Column(name ="qq")
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
    @Column(name ="msn")
	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}
    @Column(name ="remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Transient
	public String getLvname() {
		return lvname;
	}

	public void setLvname(String lvname) {
		this.lvname = lvname;
	}
    @Column(name ="lastlogin")
	public long getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(long lastlogin) {
		this.lastlogin = lastlogin;
	}
    @Column(name ="logincount")
	public Integer getLogincount() {
		return logincount;
	}

	public void setLogincount(Integer logincount) {
		this.logincount = logincount;
	}
    @Column(name ="parentid")
	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
    @Column(name ="agentid")
	public Integer getAgentid() {
		return agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}
    @Column(name ="is_cheked")
	public int getIs_cheked() {
		return is_cheked;
	}

	public void setIs_cheked(int isCheked) {
		is_cheked = isCheked;
	}
    @Column(name ="registerip")
	public String getRegisterip() {
		return registerip;
	}

	public void setRegisterip(String registerip) {
		this.registerip = registerip;
	}
    @Column(name ="mp")
	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}
    @Column(name ="info_full")
    public int getInfo_full() {
        return info_full;
    }

    public void setInfo_full(int info_full) {
        this.info_full = info_full;
    }
    @Column(name ="face")
    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }
    @Column(name ="nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    @Column(name ="midentity")
    public Integer getMidentity() {
        return midentity;
    }

    public void setMidentity(Integer midentity) {
        this.midentity = midentity;
    }
    @Column(name ="find_code")
    public String getFind_code() {
        return find_code;
    }

    public void setFind_code(String find_code) {
        this.find_code = find_code;
    }
    @Column(name ="recommend_point_state")
    public int getRecommend_point_state() {
        return recommend_point_state;
    }

    public void setRecommend_point_state(int recommend_point_state) {
        this.recommend_point_state = recommend_point_state;
    }
    @Column(name ="last_send_email")
    public Integer getLast_send_email() {
        return last_send_email;
    }

    public void setLast_send_email(Integer last_send_email) {
        this.last_send_email = last_send_email;
    }
}