package com.china.ciic.studyweb.speechsynthesis.repositories;

import com.china.ciic.studyweb.speechsynthesis.entity.Courseware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursewareRepository extends JpaRepository<Courseware, Long> {

    /**
     * 找出两篇还未语音合成的电子书
     * @return
     */
    @Query(value="SELECT book.* FROM pec_courseware book " +
            " LEFT JOIN pec_bookaudio audio " +
            " ON book.sys_id = audio.bookId " +
            " LEFT JOIN pec_coursewareinfo info " +
            " ON book.sys_id = info.sys_id " +
            " WHERE audio.bookId IS NULL " +
            " AND info.pec_resourceurl LIKE '/book/%' " +
            " AND book.pec_publishstate NOT IN (3,4) " +
            " ORDER BY book.sys_id desc " +
            " LIMIT 0,2 ", nativeQuery = true)
    List<Courseware> findOutNotTtsBooks();

    /**
     * 找出两篇指定栏目还未语音合成的电子书
     * @return
     */
    @Query(value="SELECT book.* FROM pec_courseware book " +
            " LEFT JOIN pec_bookaudio audio " +
            " ON book.sys_id = audio.bookId " +
            " LEFT JOIN pec_coursewareinfo info " +
            " ON book.sys_id = info.sys_id " +
            " WHERE audio.bookId IS NULL " +
            " AND book.pec_columnid = ? " +
            " AND info.pec_resourceurl LIKE '/book/%' " +
            " AND book.pec_publishstate NOT IN (3,4) " +
            " ORDER BY book.sys_id desc " +
            " LIMIT 0,2 ", nativeQuery = true)
    List<Courseware> findOutNotTtsBookByColumId(Long columnId);

}
