package com.china.ciic.studyweb.speechsynthesis.repositories;

import java.util.List;

import com.china.ciic.studyweb.speechsynthesis.entity.BookAudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookAudioRepository extends JpaRepository<BookAudio, Long> {
	/**
	 * 根据电子书id查询文章音频信息
	 * @param bookId 电子书id
	 * @return BookAudio对象的集合
	 */
	public List<BookAudio> findByBookId(Long bookId);

	/**
	 * 根据电子书id删除电子音频信息
	 * @param bookId 电子书id
	 */
	@Modifying
	public void deleteByBookId(Long bookId);
}
