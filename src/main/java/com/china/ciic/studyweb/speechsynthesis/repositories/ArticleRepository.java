package com.china.ciic.studyweb.speechsynthesis.repositories;

import com.china.ciic.studyweb.speechsynthesis.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    /**
     * 找出两篇还未语音合成的文章
     * @return
     */
    @Query(value="SELECT article.* FROM pec_article article " +
            "LEFT JOIN pec_articleaudio audio " +
            " ON article.sys_id = audio.articleid " +
            " WHERE audio.articleid IS NULL " +
            " AND article.pec_publshstate NOT IN (3,4) " +
            " LIMIT 0,2 ", nativeQuery = true)
    List<Article> findOutNotTtsArticle();

    /**
     * 找出两篇指定栏目还未语音合成的文章
     * @return
     */
    @Query(value="SELECT article.* FROM pec_article article " +
            "LEFT JOIN pec_articleaudio audio " +
            " ON article.sys_id = audio.articleid " +
            " WHERE audio.articleid IS NULL " +
            " AND article.pec_columnId = ? " +
            " AND article.pec_publshstate NOT IN (3,4) " +
            " LIMIT 0,2 ", nativeQuery = true)
    List<Article> findOutNotTtsArticleByColumId(Long columnId);
	
}
