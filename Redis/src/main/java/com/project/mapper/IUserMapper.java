package com.project.mapper;

import com.project.bean.UserBean;

public interface IUserMapper {

    UserBean findByID(int id);
}
