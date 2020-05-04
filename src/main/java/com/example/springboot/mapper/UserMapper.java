package com.example.springboot.mapper;


import com.example.springboot.eneity.User;

import java.util.List;
 /**  
   * @className: UserMapper
   * @Description: TODO
   * @author wangwei
   * @date 2020/5/4 12:41
   */
public interface UserMapper {

    List<User> getUsers();
}