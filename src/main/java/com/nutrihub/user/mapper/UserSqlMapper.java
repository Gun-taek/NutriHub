package com.nutrihub.user.mapper;

import com.nutrihub.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
// Dto Import


@Mapper
public interface UserSqlMapper {
    // 회원가입 기능
    public void joinProcess(UserDto userDto);

    public List<UserDto> getIdList();

    public UserDto findUserByIdPw(UserDto userDto);
}
