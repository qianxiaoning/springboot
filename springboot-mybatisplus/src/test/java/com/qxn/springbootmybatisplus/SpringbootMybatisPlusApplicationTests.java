package com.qxn.springbootmybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qxn.springbootmybatisplus.mapper.UserMapper;
import com.qxn.springbootmybatisplus.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisPlusApplicationTests {
    @Autowired
    private UserMapper userMapper;
//    条件构造器
    private QueryWrapper<User> queryWrapper;
    private UpdateWrapper<User> updateWrapper;

    @Test
    public void testFindUser() {
        //条件为空
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
    @Test
    public void testInsertUser() {
        User user = new User();
        user.setName("小明");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }
    @Test
    public void testDeleteById() {
        int i = userMapper.deleteById(5);
        System.out.println(i);
    }
    @Test
    public void testDeleteUser() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(7);
        int i = userMapper.deleteBatchIds(list);
        System.out.println(i);
    }
    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setName("sq").setId(3);
        int i = userMapper.updateById(user);
        System.out.println(i);
    }
    @Test
    public void testSelectUserById() {
        User user = userMapper.selectById(2);
        System.out.println(user);
    }
    @Test
    public void testSelectUserByName() {
        User user = new User();
        user.setId(1);
        queryWrapper = new QueryWrapper(user);
        User userInfo = userMapper.selectOne(queryWrapper);
        System.out.println(userInfo);
    }
    @Test
    public void testSelectUser2() {
        queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",1).eq("name","a");
        User userInfo = userMapper.selectOne(queryWrapper);
        System.out.println(userInfo);
    }
    @Test
    public void testSelectUserList() {
        User user = new User();
        user.setName("d");
        queryWrapper = new QueryWrapper(user);
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }
    @Test
    public void testSelectUserByConditionCompare() {
        queryWrapper = new QueryWrapper();
        //大于
        queryWrapper.gt("id",2);
        //小于
//        queryWrapper.lt("id",4);
        //等于eq，大于等于ge，小于等于le
        queryWrapper.le("id",4);
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println(count);
    }
    @Test
    public void testSelectUserByConditionOr() {
        queryWrapper = new QueryWrapper();
        queryWrapper.gt("id",4).or().lt("id",2);
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }
    @Test
    public void testSelectUserByConditionBetween() {
        queryWrapper = new QueryWrapper();
        queryWrapper.between("id", 2, 4);
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }
    @Test
    public void testSelectUserByNameNull() {
        queryWrapper = new QueryWrapper();
        queryWrapper.isNull("name");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }
    @Test
    public void testUpdateUserIsNameNull() {
        User user = new User().setName("f");
        updateWrapper = new UpdateWrapper<>();
        updateWrapper.isNull("name");
        int i = userMapper.update(user, updateWrapper);
        System.out.println(i);
    }
}
