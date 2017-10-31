package com.china.ciic.studyweb.speechsynthesis.repositories;

import com.china.ciic.studyweb.speechsynthesis.entity.ArticleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDetailRepository extends JpaRepository<ArticleDetail, Long> {

}
