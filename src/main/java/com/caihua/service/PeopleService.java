package com.caihua.service;

import com.caihua.bean.People;
import com.caihua.mapper.PeopleMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface PeopleService {

    List<People> findPeople(String date);

}
