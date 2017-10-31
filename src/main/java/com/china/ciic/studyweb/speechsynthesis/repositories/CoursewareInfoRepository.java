package com.china.ciic.studyweb.speechsynthesis.repositories;

import com.china.ciic.studyweb.speechsynthesis.entity.CoursewareInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursewareInfoRepository extends JpaRepository<CoursewareInfo, Long> {

}
