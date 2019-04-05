package com.caihua.mapper;

import com.caihua.bean.People;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PeopleMapper {
    List<People> findPeople(String date);
}
