package com.qxn.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.qxn.pj.sys.entity.Menus;

@Mapper
public interface MenusDao {
	//新增menu，用对象接收
	//sql语句较长，不写在注释中，写在xml文件中
	int insertObject(Menus entity);
	//删除menus表记录
	@Delete("delete from menus where id=#{id}")
	int deleteObject(Integer id);
	//基于id统计子菜单数
	@Select("select count(*) from menus where parentId=#{id}")
	int getChildCount(Integer id);
	//查询所有菜单和上级菜单信息，左外连接
	//一条数据是一个map，相同属性名值会覆盖name
	@Select("select c.*,p.name parentName "
			+ "from menus c left join menus p "
			+ "on c.parentId=p.id")
	List<Map<String,Object>> findObjects();
	int updateObject(Menus entity);
}
