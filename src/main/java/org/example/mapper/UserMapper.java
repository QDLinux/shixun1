package org.example.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.example.entity.User;

@Mapper
public interface UserMapper {
    public User getUserById(Integer id);
    public Integer addUser(User user);
    @Delete("delete from user where id = #{id}")
    public Integer delUserById(Integer id);
    @Update("update user set userName=#{userName},password=#{password} where id=#{id}")
    public Integer updateUser(User user);
}
