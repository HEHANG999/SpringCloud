package com.project.servcie;

import com.project.bean.UserBean;

public interface IUserService {

    UserBean findById(int id);
}
