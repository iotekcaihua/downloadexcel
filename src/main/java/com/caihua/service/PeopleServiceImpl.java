package com.caihua.service;

import com.caihua.bean.People;
import com.caihua.mapper.PeopleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    public List<People> findPeople(String date) {
        return peopleMapper.findPeople(date);
    }
}
