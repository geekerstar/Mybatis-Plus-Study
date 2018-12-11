package com.geekerstar.mp.test;

import com.baomidou.mybatisplus.plugins.Page;
import com.geekerstar.mp.beans.Employee;
import com.geekerstar.mp.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 * @author geekerstar
 * @date 2018/12/11
 * description
 */
public class TestMp {

    private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

    private EmployeeMapper employeeMapper = ioc.getBean("employeeMapper",EmployeeMapper.class);


    /**
     * 通用 删除操作
     */
    @Test
    public void testCommonDelete() {
        //1 .根据id进行删除
        Integer result = employeeMapper.deleteById(13);
        System.out.println("result: " + result );
        //2. 根据 条件进行删除
//		Map<String,Object> columnMap = new HashMap<>();
//		columnMap.put("last_name", "MP");
//		columnMap.put("email", "mp@atguigu.com");
//		Integer result = employeeMapper.deleteByMap(columnMap);
//		System.out.println("result: " + result );

        //3. 批量删除
//		List<Integer> idList = new ArrayList<>();
//		idList.add(3);
//		idList.add(4);
//		idList.add(5);
//		Integer result = employeeMapper.deleteBatchIds(idList);
//		System.out.println("result: " + result );
    }


    /**
     * 测试通用 查询操作
     */
    @Test
    public void  testCommonSelect() {
        //1. 通过id查询
//		Employee employee = employeeMapper.selectById(7);
//		System.out.println(employee);

        //2. 通过多个列进行查询    id  +  lastName
//		Employee  employee = new Employee();
//		//employee.setId(7);
//		employee.setLastName("xx");
//		employee.setGender(0);
//
//		Employee result = employeeMapper.selectOne(employee);
//		System.out.println("result: " +result );


        //3. 通过多个id进行查询    <foreach>
//		List<Integer> idList = new ArrayList<>();
//		idList.add(4);
//		idList.add(5);
//		idList.add(6);
//		idList.add(7);
//		List<Employee> emps = employeeMapper.selectBatchIds(idList);
//		System.out.println(emps);

        //4. 通过Map封装条件查询
//		Map<String,Object> columnMap = new HashMap<>();
//		columnMap.put("last_name", "Tom");
//		columnMap.put("gender", 1);
//
//		List<Employee> emps = employeeMapper.selectByMap(columnMap);
//		System.out.println(emps);

        //5. 分页查询
        List<Employee> emps = employeeMapper.selectPage(new Page<>(1, 1), null);
        System.out.println(emps);
    }




    /**
     * 测试通用 更新操作
     */
    @Test
    public void testCommonUpdate(){
        //初始化修改对象
        Employee employee = new Employee();
        employee.setId(5);
        employee.setLastName("MybatisPlus");
        employee.setEmail("2222@qq.com");
        employee.setGender(0);
        employee.setAge(33);

//        Integer result = employeeMapper.updateById(employee);
        Integer result = employeeMapper.updateAllColumnById(employee);
        System.out.println("result:"+result);

    }




    /**
     * 测试通用插入操作
     * @throws SQLException
     */
    @Test
    public void testCommonInsert(){
        //初始化Employee对象
        Employee employee = new Employee();
        employee.setLastName("Geekerstar");
        employee.setEmail("247507792@qq.com");
        employee.setGender(1);
        employee.setAge(22);

        employee.setSalary(2000.0);
        //插入到数据库
        //Insert方法在插入时，会根据实体类的每个属性进行非空判断，只有非空的属性对应的字段才会出现到SQL语句中
        //InsertAllColum方法在插入时，不管属性是否非空，属性所对应的字段都会出现到SQL语句中
        Integer result = employeeMapper.insert(employee);
        System.out.println("result:"+result);

        //获取当前数据在数据库中的主键值
        Integer key = employee.getId();
        System.out.println("key:"+key);


    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource ds = ioc.getBean("dataSource",DataSource.class);
        System.out.println(ds);

        Connection connection = ds.getConnection();
        System.out.println(connection);

    }
}
