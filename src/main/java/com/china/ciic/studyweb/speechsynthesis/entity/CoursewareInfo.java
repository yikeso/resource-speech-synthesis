package com.china.ciic.studyweb.speechsynthesis.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pec_coursewareinfo")
public class CoursewareInfo implements Serializable {

	private static final long serialVersionUID = -8262984788376444460L;

	@Id
	@GeneratedValue
	@Column(name = "sys_id", nullable = false, unique = true)
	private Long id;

	@Column(name = "pec_subtype", nullable = false)
	private String subType;// 子类型

	@Column(name = "pec_extname", length = 6)
	private String extName;// 文件格式

	@Column(name = "pec_definition", length = 8)
	private String definition;// 清晰度

	@Column(name = "pec_money")
	private Double money = 0D;// 价格

	@Column(name = "pec_length", nullable = false)
	private Long length;// 课件长度 视频时间、文章字数

	@Column(name = "pec_quality")
	private Integer quality;// 质量 1-低、2-中、3-高

	@Column(name = "pec_difficulty")
	private Integer difficulty;// 难度级别 1-低、2-中、3-高

	@Column(name = "pec_resourceurl", length = 300, nullable = false)
	private String url;// 课件资源连接

	@Column(name = "pec_language", length = 10)
	private String language;// 课件语言
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "times", nullable = false)
	private Date times = new Date(); // updateTime

	@Column(name = "pec_coursewareid")
	private Long coursewareId;// 课件

	@Column(name = "pec_createuserid")
	private Long createUserId;// 创建用户id

	@Column(name = "pec_createusername", length = 20)
	private String createUsername; // 创建用户名称

	@Column(name = "pec_publishstate")
	private int publishState; // 发布状态 0-已发布、 1-待发布 、2-审核中

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pec_createdate", nullable = false)
	private Date createDate = new Date(); // 上传时间

	@Column(name = "pec_auditname", length = 20)
	private String auditname; // 审核人

	@Column(name = "pec_auditid")
	private Long auditid; // 审核人 id

	@Column(name = "pec_m3u8_url", length = 100)
	private String meu8Url; // 视频m3u8地址
	@Column(name = "pec_m3u8_status")
	private int meu8Staus; // 视频m3u8状态
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getExtName() {
		return extName;
	}
	public void setExtName(String extName) {
		this.extName = extName;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Long getLength() {
		return length;
	}
	public void setLength(Long length) {
		this.length = length;
	}
	public Integer getQuality() {
		return quality;
	}
	public void setQuality(Integer quality) {
		this.quality = quality;
	}
	public Integer getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Date getTimes() {
		return times;
	}
	public void setTimes(Date times) {
		this.times = times;
	}
	public Long getCoursewareId() {
		return coursewareId;
	}
	public void setCoursewareId(Long coursewareId) {
		this.coursewareId = coursewareId;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateUsername() {
		return createUsername;
	}
	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}
	public int getPublishState() {
		return publishState;
	}
	public void setPublishState(int publishState) {
		this.publishState = publishState;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getAuditname() {
		return auditname;
	}
	public void setAuditname(String auditname) {
		this.auditname = auditname;
	}
	public Long getAuditid() {
		return auditid;
	}
	public void setAuditid(Long auditid) {
		this.auditid = auditid;
	}
	public String getMeu8Url() {
		return meu8Url;
	}
	public void setMeu8Url(String meu8Url) {
		this.meu8Url = meu8Url;
	}
	public int getMeu8Staus() {
		return meu8Staus;
	}
	public void setMeu8Staus(int meu8Staus) {
		this.meu8Staus = meu8Staus;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoursewareInfo other = (CoursewareInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CoursewareInfo [id=" + id + ", subType=" + subType
				+ ", extName=" + extName + ", definition=" + definition
				+ ", money=" + money + ", length=" + length + ", quality="
				+ quality + ", difficulty=" + difficulty + ", url=" + url
				+ ", language=" + language + ", times=" + times
				+ ", coursewareId=" + coursewareId + ", createUserId="
				+ createUserId + ", createUsername=" + createUsername
				+ ", publishState=" + publishState + ", createDate="
				+ createDate + ", auditname=" + auditname + ", auditid="
				+ auditid + ", meu8Url=" + meu8Url + ", meu8Staus=" + meu8Staus
				+ "]";
	}
	
}