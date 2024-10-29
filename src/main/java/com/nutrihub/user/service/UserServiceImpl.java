package com.nutrihub.user.service;


import com.nutrihub.user.dto.UserDto;
import com.nutrihub.user.mapper.UserSqlMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl{
    private final UserSqlMapper userSqlMapper;
    public UserServiceImpl(UserSqlMapper userSqlMapper){
        this.userSqlMapper = userSqlMapper;
    }

    // 유저 회원가입
    public void JoinProcess(UserDto userDto) {
        userSqlMapper.joinProcess(userDto);
    }

    public List<UserDto> getIdList () {
        return userSqlMapper.getIdList();
    }
    // 유저 로그인
    public UserDto LoginProcess (UserDto userDto){
        return userSqlMapper.findUserByIdPw(userDto);
    }
}
