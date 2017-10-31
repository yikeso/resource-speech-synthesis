package com.china.ciic.studyweb.speechsynthesis.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "pec_articledetails")
public class ArticleDetail implements Serializable {

	private static final long serialVersionUID = 8266554327576597911L;

	@Id
	@Column(name = "sys_id")
	private Long id;
	
	@Column(name = "pec_extend1", length = 300)
	private String extend1;// 附加属性1

	@Column(name = "pec_extend2", length = 300)
	private String extend2;// 附加属性2

	@Column(name = "pec_extend3", length = 300)
	private String extend3;// 附加属性3

	@Column(name = "pec_extend4", length = 300)
	private String extend4;// 附加属性4

	@Column(name = "pec_extend5", length = 300)
	private String extend5;// 附加属性5

	@Column(name = "pec_extend6", length = 300)
	private String extend6;// 附加属性6

	@Column(name = "pec_extend7", length = 300)
	private String extend7;// 附加属性7

	@Column(name = "pec_extend8", length = 300)
	private String extend8;// 附加属性8

	@Column(name = "pec_extend9", length = 300)
	private String extend9;// 附加属性9

	@Column(name = "pec_extend10", length = 300)
	private String extend10;// 附加属性10

	@Lob
	@Column(name = "pec_summary")
	private String summary;// 摘要

	@Lob
	@Column(name = "pec_content")
	private String content;// 文章内容

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}

	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	public String getExtend3() {
		return extend3;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}

	public String getExtend4() {
		return extend4;
	}

	public void setExtend4(String extend4) {
		this.extend4 = extend4;
	}

	public String getExtend5() {
		return extend5;
	}

	public void setExtend5(String extend5) {
		this.extend5 = extend5;
	}

	public String getExtend6() {
		return extend6;
	}

	public void setExtend6(String extend6) {
		this.extend6 = extend6;
	}

	public String getExtend7() {
		return extend7;
	}

	public void setExtend7(String extend7) {
		this.extend7 = extend7;
	}

	public String getExtend8() {
		return extend8;
	}

	public void setExtend8(String extend8) {
		this.extend8 = extend8;
	}

	public String getExtend9() {
		return extend9;
	}

	public void setExtend9(String extend9) {
		this.extend9 = extend9;
	}

	public String getExtend10() {
		return extend10;
	}

	public void setExtend10(String extend10) {
		this.extend10 = extend10;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		ArticleDetail other = (ArticleDetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ArticleDetail [id=" + id + ", extend1=" + extend1
				+ ", extend2=" + extend2 + ", extend3=" + extend3
				+ ", extend4=" + extend4 + ", extend5=" + extend5
				+ ", extend6=" + extend6 + ", extend7=" + extend7
				+ ", extend8=" + extend8 + ", extend9=" + extend9
				+ ", extend10=" + extend10 + ", summary=" + summary
				+ ", content=" + content + "]";
	}

}
