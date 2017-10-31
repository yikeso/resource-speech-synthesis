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
@Table(name = "pec_article")
public class Article implements Serializable  {

	private static final long serialVersionUID = -4572526542367248563L;

	@Id
	@GeneratedValue
	@Column(name = "sys_id", nullable = false, unique = true)
	private Long id;

	@Column(name = "pec_columnid")
	private Long columnId;// 栏目ID

	@Column(name = "pec_teaser")
	private String teaser;// 引题
	@Column(name = "pec_powernum1")
	private Integer powernum1 = 0;// 权重1

	@Column(name = "pec_powernum2")
	private Integer powernum2 = 0;// 权重2

	@Column(name = "pec_powernum3")
	private Integer powernum3 = 0;// 权重3

	@Column(name = "pec_powernum4")
	private Integer powernum4 = 0;// 权重4

	@Column(name = "pec_title", length = 200)
	private String title;// 标题

	@Column(name = "pec_indextitle", length = 200)
	private String indexTitle;// 首页标题

	@Column(name = "pec_columntitle", length = 200)
	private String columnTitle;// 栏目页标题

	@Column(name = "pec_vicetitle", length = 200)
	private String vicetitle;// 副标题

	@Column(name = "pec_author", length = 100)
	private String author;// 作者

	@Column(name = "pec_source", length = 100)
	private String source;// 来源

	@Column(name = "pec_keyword", length = 100)
	private String keyword;// 关键字

	@Column(name = "pec_imageone", length = 300)
	private String imageOne;// 缩略图1(一比一-100:100)

	@Column(name = "pec_imagetwo", length = 300)
	private String imageTwo;// 缩略图2(四比三-160:120)
	
	@Column(name = "pec_imagethree", length = 300)
	private String imageThree;// 缩略图3(三比四)

	@Temporal(TemporalType.DATE)
	@Column(name = "pec_datedir")
	private Date datedir = new Date();// 终文章日期
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pec_judgment")
	private Date judgment;
	
	@Column(name = "pec_publshstate")
	private int publishState; // 发布状态

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pec_createdate")
	private Date createDate = new Date();// 录入时间

	@Column(name = "pec_userid")
	private Long createUserId;// 编辑ID
	
	@Column(name = "pec_gather_userid")
	private Long gatherCreateUserId;// 采集ID

	@Column(name = "pec_auditfrequency")
	private Integer auditFrequency;// 审核次数

	@Column(name = "pec_videourl", length = 100)
	private String videoUrl; // 视频 地址
//
//	@OneToOne(fetch = FetchType.LAZY)
//	@PrimaryKeyJoinColumn(name = "id", columnDefinition = "")
//	private ArticleDetail detail;

	@Column(name = "pec_firstauditusername", length = 30)
	private String firstAuditUsername; // 初审用户名

	@Column(name = "pec_firstaudituserid")
	private Long firstAuditUserId; // 初审id

	@Column(name = "pec_finalaudituserid")
	private Long finalAuditUserId; // 终审id

	@Column(name = "pec_finalauditusername", length = 30)
	private String finalAuditUsername; // 终审用户名
	
	@Column(name = "pec_delusername", length = 50)
	private String delUserName; // 终审用户名

	//经度
	@Column(name = "pec_precision")
	private String precision;
	//纬度
	@Column(name = "pec_dimension")
	private String dimension;
	//地点
	@Column(name = "pec_place")
	private String place;
	//事件发生时间
	@Column(name = "pec_eventtime")
	private String eventTimes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getColumnId() {
		return columnId;
	}
	public void setColumnId(Long columnId) {
		this.columnId = columnId;
	}
	public String getTeaser() {
		return teaser;
	}
	public void setTeaser(String teaser) {
		this.teaser = teaser;
	}
	public Integer getPowernum1() {
		return powernum1;
	}
	public void setPowernum1(Integer powernum1) {
		this.powernum1 = powernum1;
	}
	public Integer getPowernum2() {
		return powernum2;
	}
	public void setPowernum2(Integer powernum2) {
		this.powernum2 = powernum2;
	}
	public Integer getPowernum3() {
		return powernum3;
	}
	public void setPowernum3(Integer powernum3) {
		this.powernum3 = powernum3;
	}
	public Integer getPowernum4() {
		return powernum4;
	}
	public void setPowernum4(Integer powernum4) {
		this.powernum4 = powernum4;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIndexTitle() {
		return indexTitle;
	}
	public void setIndexTitle(String indexTitle) {
		this.indexTitle = indexTitle;
	}
	public String getColumnTitle() {
		return columnTitle;
	}
	public void setColumnTitle(String columnTitle) {
		this.columnTitle = columnTitle;
	}
	public String getVicetitle() {
		return vicetitle;
	}
	public void setVicetitle(String vicetitle) {
		this.vicetitle = vicetitle;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getImageOne() {
		return imageOne;
	}
	public void setImageOne(String imageOne) {
		this.imageOne = imageOne;
	}
	public String getImageTwo() {
		return imageTwo;
	}
	public void setImageTwo(String imageTwo) {
		this.imageTwo = imageTwo;
	}
	public String getImageThree() {
		return imageThree;
	}
	public void setImageThree(String imageThree) {
		this.imageThree = imageThree;
	}
	public Date getDatedir() {
		return datedir;
	}
	public void setDatedir(Date datedir) {
		this.datedir = datedir;
	}
	public Date getJudgment() {
		return judgment;
	}
	public void setJudgment(Date judgment) {
		this.judgment = judgment;
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
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public Long getGatherCreateUserId() {
		return gatherCreateUserId;
	}
	public void setGatherCreateUserId(Long gatherCreateUserId) {
		this.gatherCreateUserId = gatherCreateUserId;
	}
	public Integer getAuditFrequency() {
		return auditFrequency;
	}
	public void setAuditFrequency(Integer auditFrequency) {
		this.auditFrequency = auditFrequency;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
//	public ArticleDetail getDetail() {
//		return detail;
//	}
//	public void setDetail(ArticleDetail detail) {
//		this.detail = detail;
//	}
	public String getFirstAuditUsername() {
		return firstAuditUsername;
	}
	public void setFirstAuditUsername(String firstAuditUsername) {
		this.firstAuditUsername = firstAuditUsername;
	}
	public Long getFirstAuditUserId() {
		return firstAuditUserId;
	}
	public void setFirstAuditUserId(Long firstAuditUserId) {
		this.firstAuditUserId = firstAuditUserId;
	}
	public Long getFinalAuditUserId() {
		return finalAuditUserId;
	}
	public void setFinalAuditUserId(Long finalAuditUserId) {
		this.finalAuditUserId = finalAuditUserId;
	}
	public String getFinalAuditUsername() {
		return finalAuditUsername;
	}
	public void setFinalAuditUsername(String finalAuditUsername) {
		this.finalAuditUsername = finalAuditUsername;
	}
	public String getDelUserName() {
		return delUserName;
	}
	public void setDelUserName(String delUserName) {
		this.delUserName = delUserName;
	}
	public String getPrecision() {
		return precision;
	}
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getEventTimes() {
		return eventTimes;
	}
	public void setEventTimes(String eventTimes) {
		this.eventTimes = eventTimes;
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
		Article other = (Article) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", columnId=" + columnId + ", teaser="
				+ teaser + ", powernum1=" + powernum1 + ", powernum2="
				+ powernum2 + ", powernum3=" + powernum3 + ", powernum4="
				+ powernum4 + ", title=" + title + ", indexTitle=" + indexTitle
				+ ", columnTitle=" + columnTitle + ", vicetitle=" + vicetitle
				+ ", author=" + author + ", source=" + source + ", keyword="
				+ keyword + ", imageOne=" + imageOne + ", imageTwo=" + imageTwo
				+ ", imageThree=" + imageThree + ", datedir=" + datedir
				+ ", judgment=" + judgment + ", publishState=" + publishState
				+ ", createDate=" + createDate + ", createUserId="
				+ createUserId + ", gatherCreateUserId=" + gatherCreateUserId
				+ ", auditFrequency=" + auditFrequency + ", videoUrl="
				+ videoUrl + ", detail="  + ", firstAuditUsername="
				+ firstAuditUsername + ", firstAuditUserId=" + firstAuditUserId
				+ ", finalAuditUserId=" + finalAuditUserId
				+ ", finalAuditUsername=" + finalAuditUsername
				+ ", delUserName=" + delUserName + ", precision=" + precision
				+ ", dimension=" + dimension + ", place=" + place
				+ ", eventTimes=" + eventTimes + "]";
	}
	
}