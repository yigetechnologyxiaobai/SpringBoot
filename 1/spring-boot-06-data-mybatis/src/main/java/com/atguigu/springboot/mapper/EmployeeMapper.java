package com.atguigu.springboot.mapper;

import com.atguigu.springboot.bean.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

//@Mapper或者@MapperScan将接口扫描配置到容器中
@Component
public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);

}
