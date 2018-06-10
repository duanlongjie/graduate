package cn.edu.haue.graduate.service.Impl;

import cn.edu.haue.graduate.entity.Student;
import cn.edu.haue.graduate.service.GraduateOrNotService;

/**
 * Author: lnp
 * Date: 2018/6/10
 **/
public class GraduateOrNotServiceImpl implements GraduateOrNotService {
    /**
     * 毕业条件审核
     * @param student
     * @return
     */
    @Override
    public boolean GraduateOrNot(Student student) {
        //student应该是经过查询加工的具备所有的信息，在这里进行审核
        //写审核逻辑....
        return false;
    }
}
