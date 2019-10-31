package ccut.qinrushi.dao;


import ccut.qinrushi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//泛型中第一个参数是实体类，第二个是id类型
public interface StudentDao extends JpaRepository<Student,String> {

     List<Student> findByName(String name);
     Student findByStudentId(String studentId);
     List<Student> findByNameAndAge(String name,String age);

     void deleteByStudentId(String studentId);
}
