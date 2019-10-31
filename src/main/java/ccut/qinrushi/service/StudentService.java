package ccut.qinrushi.service;

import ccut.qinrushi.dao.StudentDao;
import ccut.qinrushi.entity.Student;
import ccut.qinrushi.util.ShowData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    ShowData showData;

    //插入一个学生
    public String  addStudent(String name,String age){
        List<Student> byNameAndAge = studentDao.findByNameAndAge(name, age);
        if(byNameAndAge.size()==1){
            return showData.showData(false,"学生已存在");
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String timeString = timestamp.toString();
        timeString = timeString.split(" ")[0].split("-")[0] +
                timeString.split(" ")[0].split("-")[1] +
                timeString.split(" ")[0].split("-")[2] +
                timeString.split(" ")[1].split(":")[0] +
                timeString.split(" ")[1].split(":")[1] +
                timeString.split(" ")[1].split(":")[2].split("\\.")[0];
        Student student = new Student(timeString, name, age);
        Student save = studentDao.save(student);
        return showData.showData(true,"添加成功");
    }

//    public String updateStudentById(String studentId,String name,String age){
//        studentDao.save();
//        return showData.showData(true,"更新成功");
//    }
    public String updateStudent(String studentId,String name,String age){
        Student byStudentId = studentDao.findByStudentId(studentId);
        if(byStudentId != null){
            byStudentId.setName(name);
            byStudentId.setAge(age);
            studentDao.save(byStudentId);
            return showData.showData(true,"修改成功");
        }
        return showData.showData(false,"修改失败，学生不存在");
    }


    //根据id删除一条数据
    @Transactional
    public String deleteStudentById(String studentId){
        Student byStudentId = studentDao.findByStudentId(studentId);
        if (byStudentId != null){
            studentDao.deleteByStudentId(studentId);
            return showData.showData(true,"删除成功");
        }
        return showData.showData(false,"删除失败");
    }

    //查询所有
    public List<Student> findAll(){
        return studentDao.findAll();
    }
    //根U据id查询一条数据(2.0后不能使用findOne了)
    public String findStudentById(String studentId){
        Student byStudentId = studentDao.findByStudentId(studentId);
        if (byStudentId != null){
            return showData.showData(true,byStudentId.toString());
        }
        return showData.showData(false,"查询失败");
    }
    //根据学生姓名查询多条数据
    public String findStudentByName(String name){
        List<Student> byName = studentDao.findByName(name);
        if (byName.size() >= 1){
            return showData.showData(true,byName.toString());
        }
        return showData.showData(false,"查询失败");
    }
}
