package ccut.qinrushi.controller;

import ccut.qinrushi.dao.StudentDao;
import ccut.qinrushi.entity.Student;
import ccut.qinrushi.service.StudentService;
import ccut.qinrushi.util.ShowData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    StudentDao studentDao;
    @Autowired
    ShowData showData;

    //添加一个学生
    @PostMapping(path = "/addStudent")
    public String addStudent(@RequestParam("name") String name, @RequestParam("age") String age) {
        if ("".equals(name) || name == null || "".equals(age) || age == null) {
            return showData.showData(false, "请补全信息");
        }
        return studentService.addStudent(name,age);
    }

    @PutMapping(path = "/updateStudent")
    public String updateStudentById(@RequestParam(value="studentId",required = false) String studentId,
                                    @RequestParam("name") String name,
                                    @RequestParam("age") String age){
        if ("".equals(studentId) || studentId == null ||"".equals(name) || name == null || "".equals(age) || age == null) {
            return showData.showData(false, "请补全信息");
        }
        return studentService.updateStudent(studentId,name,age);
    }



    //根据id删除一条数据
    @PostMapping(path = "/deleteStudent")
    public String deleteStudentById(@RequestParam("studentId") String studentId) {
        if ("".equals(studentId) || studentId == null) {
            return showData.showData(false, "没有这个id");
        }
        return studentService.deleteStudentById(studentId);
    }

    //查询所有
    @GetMapping(path = "/findAll")
    public List<Student> findAll() {
        System.out.println("9999999999999999999");
            List<Student> list = studentService.findAll();
            return list;
    }

    //根据id查询一条数据(2.0后不能使用findOne了)
    @GetMapping(path = "/findStudentById")
    public String findStudentById(@RequestParam("studentId") String studentId) {
        if ("".equals(studentId) || studentId == null) {
            return showData.showData(false, "查询不到这个id");
        }
         return studentService.findStudentById(studentId);
    }

    //根据学生姓名查询多条数据
    @GetMapping(value = "/findStudentByName")
    public String findStudentByName(@RequestParam("name") String name) {
        if ("".equals(name) || name == null) {
            return showData.showData(false, "姓名不能为空");
        }
        return studentService.findStudentByName(name);
    }
}

