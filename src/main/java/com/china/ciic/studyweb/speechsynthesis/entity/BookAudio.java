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
@Table(name = "pec_bookaudio")
public class BookAudio implements Serializable {

	private static final long serialVersionUID = -7094728269108552611L;

	@Id
	@GeneratedValue
	@Column(name = "sys_id", nullable = false, unique = true)
	private Long id;

	@Column(name = "audio_name", length = 255)
	private String audioName; // 名称

	@Column(name = "audio_time_length")
	private Long audioTimeLength; // 长度

	@Column(name = "audio_url", length = 255)
	private String audioUrl; // url地址

	@Column(name = "audio_zjjj")
	private String audioZjjj; // 专家简介

	@Column(name = "audio_path", length = 20)
	private String path; // 文件夹名称

	@Column(name = "audio_message")
	private String audioMessage; // 信息

	@Column(name = "audio_state")
	private Integer audioState; // 状态

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "audio_createdate")
	private Date createTime = new Date(); // 创建时间

	@Column(name = "audio_xml_url", length = 200)
	private String audioXmlUrl; // xml地址

	@Column(name = "audio_pic_url", length = 200)
	private String audioPicUrl; // 图片地址

	@Column(name = "audio_xml_state")
	private Integer audioXmlState; // xml状态

	@Column(name = "video_id")
	private Long videoId; // 视频id
	
	@Column(name = "localhost_ip")
	private String localhostip; // 转换主机ip
	
	@Column(name = "use_state")
	private Boolean useState = false; // 使用状态 默认情况下没有使用
	
	@Column(name="author",length=20)
	private String author; //作者
	
	@Column(name="resource",length=200)
	private String resource; // 来源
	
	@Column(name="languages",length=20)
	private String languages; // 语种
	
	@Column(name = "difficulty",length=20)
	private Integer difficulty; // 难度级别 1-低、2-中、3-高

	
	@Column(name = "bookid",length=20)
	private Long bookId;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getAudioName() {
		return audioName;
	}


	public void setAudioName(String audioName) {
		this.audioName = audioName;
	}


	public Long getAudioTimeLength() {
		return audioTimeLength;
	}


	public void setAudioTimeLength(Long audioTimeLength) {
		this.audioTimeLength = audioTimeLength;
	}


	public String getAudioUrl() {
		return audioUrl;
	}


	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}


	public String getAudioZjjj() {
		return audioZjjj;
	}


	public void setAudioZjjj(String audioZjjj) {
		this.audioZjjj = audioZjjj;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getAudioMessage() {
		return audioMessage;
	}


	public void setAudioMessage(String audioMessage) {
		this.audioMessage = audioMessage;
	}


	public Integer getAudioState() {
		return audioState;
	}


	public void setAudioState(Integer audioState) {
		this.audioState = audioState;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getAudioXmlUrl() {
		return audioXmlUrl;
	}


	public void setAudioXmlUrl(String audioXmlUrl) {
		this.audioXmlUrl = audioXmlUrl;
	}


	public String getAudioPicUrl() {
		return audioPicUrl;
	}


	public void setAudioPicUrl(String audioPicUrl) {
		this.audioPicUrl = audioPicUrl;
	}


	public Integer getAudioXmlState() {
		return audioXmlState;
	}


	public void setAudioXmlState(Integer audioXmlState) {
		this.audioXmlState = audioXmlState;
	}


	public Long getVideoId() {
		return videoId;
	}


	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}


	public String getLocalhostip() {
		return localhostip;
	}


	public void setLocalhostip(String localhostip) {
		this.localhostip = localhostip;
	}


	public Boolean getUseState() {
		return useState;
	}


	public void setUseState(Boolean useState) {
		this.useState = useState;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getResource() {
		return resource;
	}


	public void setResource(String resource) {
		this.resource = resource;
	}


	public String getLanguages() {
		return languages;
	}


	public void setLanguages(String languages) {
		this.languages = languages;
	}


	public Integer getDifficulty() {
		return difficulty;
	}


	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}


	public Long getBookId() {
		return bookId;
	}


	public void setBookId(Long bookId) {
		this.bookId = bookId;
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
		BookAudio other = (BookAudio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "BookAudio [id=" + id + ", audioName=" + audioName
				+ ", audioTimeLength=" + audioTimeLength + ", audioUrl="
				+ audioUrl + ", audioZjjj=" + audioZjjj + ", path=" + path
				+ ", audioMessage=" + audioMessage + ", audioState="
				+ audioState + ", createTime=" + createTime + ", audioXmlUrl="
				+ audioXmlUrl + ", audioPicUrl=" + audioPicUrl
				+ ", audioXmlState=" + audioXmlState + ", videoId=" + videoId
				+ ", localhostip=" + localhostip + ", useState=" + useState
				+ ", author=" + author + ", resource=" + resource
				+ ", languages=" + languages + ", difficulty=" + difficulty
				+ ", bookId=" + bookId + "]";
	}
	
}
