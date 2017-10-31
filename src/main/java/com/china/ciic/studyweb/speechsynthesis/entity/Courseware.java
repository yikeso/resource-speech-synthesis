package com.china.ciic.studyweb.speechsynthesis.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pec_courseware")
public class Courseware implements Serializable  {

	private static final long serialVersionUID = 1215387423947944340L;

	@Id
	@GeneratedValue
	@Column(name = "sys_id", nullable = false, unique = true)
	private Long id;

	@Column(name = "pec_uuid",length = 32)
	private String uuid = UUID.randomUUID().toString().replaceAll("-", ""); //uuid
	
	@Column(name = "pec_type", nullable = true)
	private String type;// 课件类型c

	@Column(name = "pec_title", nullable = true, length = 200)
	private String title;// 课件标题

	@Column(name = "pec_shorttitle", nullable = true, length = 200)
	private String shortTitle;// 短标题
	
	@Column(name = "pec_vicetitle", length = 200)
	private String vicetitle;// 副标题

	@Column(name = "pec_author", length = 30)
	private String author;// 课件作者

	@Column(name = "pec_authorcompany", length = 100)
	private String authorCompany;// 作者单位

	@Column(name = "pec_source", length = 150)
	private String source;// 课件来源

	@Column(name = "pec_readlevel")
	private int readLevel;// 阅读级别 0-科级、1-处级、2-厅级、3-省级

	@Column(name = "pec_selectstate")
	private int selectstate;// 选学状态 0-必修、1-选修、2-参考

	@Column(name = "pec_examineyear")
	private Integer examineYear;// 考核年度

	@Column(name = "pec_keyword", length = 300)
	private String keyword;// 关键词

	@Column(name = "pec_difficulty")
	private Integer difficulty;// 课件整体的难度级别 1-低、2-中、3-高

	@Column(name = "pec_provider", length = 100)
	private String provider;// 课件提供方

	@Column(name = "pec_topic", length = 300)
	private String topic;// 专题

	@Column(name = "pec_imageone", length = 300)
	private String imageOne;// 缩略图1(一比一-100:100)

	@Column(name = "pec_imagetwo", length = 300)
	private String imageTwo;// 缩略图2(四比三-160:120)

	@Column(name = "pec_imagethree", length = 300)
	private String imageThree;// 缩略图3(三比四)
//
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "coursewareId")
//	@LazyCollection(LazyCollectionOption.EXTRA)
//	private List<CoursewareInfo> infoList;

	@Column(name = "pec_publishstate")
	private int publishState; // 发布状态 0-已发布、 1-待发布 、2-审核中、3-已删除

	@Column(name = "pec_createuserid")
	private Long createUserId;// 创建用户id

	@Column(name = "pec_createusername", length = 20)
	private String createUsername;// 创建用户名

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pec_createdate", nullable = false)
	private Date createDate = new Date(); // 上传时间

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pec_publishdate")
	private Date publishDate;// 发布时间

	@Column(name = "pec_columnid")
	private Long columnId; // 栏目id

	@Column(name = "pec_columnname", length = 100)
	private String columnName; // 栏目名称

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "pec_info")
	private String info;// 课件的简介

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "pec_authorinfo")
	private String authorInfo;// 作者的简介

	@Column(name = "pec_isinfoaudit")
	private Boolean infoAudit = false;// 子集中是否有需要审核的

	@Column(name = "pec_auditname", length = 20)
	private String auditname; // 审核人

	@Column(name = "pec_auditid")
	private Long auditid; // 审核人 id

	@Column(name = "pec_paperid", length = 100)
	private String paperId; // 试卷id

	@Column(name = "pec_papername", length = 200)
	private String paperName; // 试卷名称

	@Column(name = "pec_producer", length = 20)
	private String producer; // 出品人

	@Column(name = "pec_coursewareids")
	private String coursewareIds; // 相关资源

	@Column(name = "pec_videoids")
	private String videoIds; // 相关视频资源id

	@Column(name = "pec_audioids")
	private String audioIds; // 相关音频资源id

	@Column(name = "pec_bookids")
	private String bookIds; // 相关电子书资源id

	@Column(name = "pec_mindmapids")
	private String mindmapIds; // 相关知识地图资源id
	
	@Column(name="pec_articlebookids")
	private String articleBookIds; // 相关文章id（其实与相关电子书相同）

	@Column(name = "pec_issubject")
	private Integer isSubject = 0; // 是否为专题片 （0：否 不是专题片 1：是专题片）
	
	@Column(name = "pec_figureids")//相关人物id（其实与相关电子书相同）
	private String figureIds;
	@Column(name = "pec_heart_word")
	private String heartWord; // 核心词
	
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "times", nullable = false)
	private Date times = new Date(); // updateTime

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
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

	public String getAuthorCompany() {
		return authorCompany;
	}

	public void setAuthorCompany(String authorCompany) {
		this.authorCompany = authorCompany;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getReadLevel() {
		return readLevel;
	}

	public void setReadLevel(int readLevel) {
		this.readLevel = readLevel;
	}

	public int getSelectstate() {
		return selectstate;
	}

	public void setSelectstate(int selectstate) {
		this.selectstate = selectstate;
	}

	public Integer getExamineYear() {
		return examineYear;
	}

	public void setExamineYear(Integer examineYear) {
		this.examineYear = examineYear;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
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

	public int getPublishState() {
		return publishState;
	}

	public void setPublishState(int publishState) {
		this.publishState = publishState;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Long getColumnId() {
		return columnId;
	}

	public void setColumnId(Long columnId) {
		this.columnId = columnId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getAuthorInfo() {
		return authorInfo;
	}

	public void setAuthorInfo(String authorInfo) {
		this.authorInfo = authorInfo;
	}

	public Boolean getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(Boolean infoAudit) {
		this.infoAudit = infoAudit;
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

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getCoursewareIds() {
		return coursewareIds;
	}

	public void setCoursewareIds(String coursewareIds) {
		this.coursewareIds = coursewareIds;
	}

	public String getVideoIds() {
		return videoIds;
	}

	public void setVideoIds(String videoIds) {
		this.videoIds = videoIds;
	}

	public String getAudioIds() {
		return audioIds;
	}

	public void setAudioIds(String audioIds) {
		this.audioIds = audioIds;
	}

	public String getBookIds() {
		return bookIds;
	}

	public void setBookIds(String bookIds) {
		this.bookIds = bookIds;
	}

	public String getMindmapIds() {
		return mindmapIds;
	}

	public void setMindmapIds(String mindmapIds) {
		this.mindmapIds = mindmapIds;
	}

	public String getArticleBookIds() {
		return articleBookIds;
	}

	public void setArticleBookIds(String articleBookIds) {
		this.articleBookIds = articleBookIds;
	}

	public Integer getIsSubject() {
		return isSubject;
	}

	public void setIsSubject(Integer isSubject) {
		this.isSubject = isSubject;
	}

	public String getFigureIds() {
		return figureIds;
	}

	public void setFigureIds(String figureIds) {
		this.figureIds = figureIds;
	}

	public String getHeartWord() {
		return heartWord;
	}

	public void setHeartWord(String heartWord) {
		this.heartWord = heartWord;
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

	public Date getTimes() {
		return times;
	}

	public void setTimes(Date times) {
		this.times = times;
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
		Courseware other = (Courseware) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Courseware [id=" + id + ", uuid=" + uuid + ", type=" + type
				+ ", title=" + title + ", shortTitle=" + shortTitle
				+ ", vicetitle=" + vicetitle + ", author=" + author
				+ ", authorCompany=" + authorCompany + ", source=" + source
				+ ", readLevel=" + readLevel + ", selectstate=" + selectstate
				+ ", examineYear=" + examineYear + ", keyword=" + keyword
				+ ", difficulty=" + difficulty + ", provider=" + provider
				+ ", topic=" + topic + ", imageOne=" + imageOne + ", imageTwo="
				+ imageTwo + ", imageThree=" + imageThree + ", infoList="
				+ ", publishState=" + publishState
				+ ", createUserId=" + createUserId + ", createUsername="
				+ createUsername + ", createDate=" + createDate
				+ ", publishDate=" + publishDate + ", columnId=" + columnId
				+ ", columnName=" + columnName + ", info=" + info
				+ ", authorInfo=" + authorInfo + ", infoAudit=" + infoAudit
				+ ", auditname=" + auditname + ", auditid=" + auditid
				+ ", paperId=" + paperId + ", paperName=" + paperName
				+ ", producer=" + producer + ", coursewareIds=" + coursewareIds
				+ ", videoIds=" + videoIds + ", audioIds=" + audioIds
				+ ", bookIds=" + bookIds + ", mindmapIds=" + mindmapIds
				+ ", articleBookIds=" + articleBookIds + ", isSubject="
				+ isSubject + ", figureIds=" + figureIds + ", heartWord="
				+ heartWord + ", precision=" + precision + ", dimension="
				+ dimension + ", place=" + place + ", eventTimes=" + eventTimes
				+ ", times=" + times + "]";
	}


}
