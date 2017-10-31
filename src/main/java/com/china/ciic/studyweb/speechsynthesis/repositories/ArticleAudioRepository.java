package com.china.ciic.studyweb.speechsynthesis.repositories;

import java.util.List;

import com.china.ciic.studyweb.speechsynthesis.entity.ArticleAudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ArticleAudioRepository extends JpaRepository<ArticleAudio, Long> {
	/**
	 * 根据文章id查询文章音频信息
	 * @param articleId 文章id
	 * @return ArticleAudio对象的集合
	 */
	public List<ArticleAudio> findByArticleId(Long articleId);

	/**
	 * 根据文章id删除文章音频信息
	 * @param articleId 文章id
	 */
	@Modifying
	public void deleteByArticleId(Long articleId);
	
}
