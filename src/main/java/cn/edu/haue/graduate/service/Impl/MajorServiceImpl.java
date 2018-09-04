package cn.edu.haue.graduate.service.Impl;

import cn.edu.haue.graduate.constant.ResultCode;
import cn.edu.haue.graduate.dao.MajorDao;
import cn.edu.haue.graduate.entity.Major;
import cn.edu.haue.graduate.entity.MajorKey;
import cn.edu.haue.graduate.entity.ResultInfo;
import cn.edu.haue.graduate.service.MajorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * created by dlj on 2018/9/3 17:03
 */
@Service
@Transactional
public class MajorServiceImpl implements MajorService {
    @Resource
    private MajorDao majorDao;
    @Override
    public ResultInfo<List<Major>> getAllMajors() {
        ResultInfo<List<Major>> resultInfo =new ResultInfo<>();
        List<Major> majors = majorDao.findAll();
        resultInfo.setResultObj(majors);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<Major> getByMajorkey(String name,String years) {
        ResultInfo<Major> resultInfo =new ResultInfo<>();

        if(name==null || years==null){
            System.out.println("专业名称或年限错误!");
            resultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);
            return resultInfo;
        }
        MajorKey m=new MajorKey();
        m.setMajorName(name); m.setMajorYears(years);
        Optional<Major> op = majorDao.findById(m);
        Major major = op.get();
        resultInfo.setResultObj(major);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<Major> getByMajorName(String majorName) {
        ResultInfo<Major> resultInfo =new ResultInfo<>();
        if(majorName==null){
            resultInfo.setResultCode(ResultCode.RESULT_CODE_FAIL);
            System.out.println("专业名称为空!");
            return resultInfo;
        }
        Major major = majorDao.getMajorByMajorName(majorName);
        resultInfo.setResultObj(major);
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        return resultInfo;
    }

    @Override
    public ResultInfo<List<String>> getMajorYears() {
        ResultInfo<List<String>> resultInfo =new ResultInfo<>();
        List<String> years =new ArrayList<>();
        List<Major> majors = majorDao.findAll();
        for(Major m:majors){
            String majorYears = m.getMajorYears();
            if(!years.contains(majorYears)){
                years.add(majorYears);
            }
        }
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        resultInfo.setResultObj(years);
        return resultInfo;
    }

    @Override
    public ResultInfo<List<String>> getMajorNames() {
        ResultInfo<List<String>> resultInfo =new ResultInfo<>();
        List<String> names =new ArrayList<>();
        List<Major> majors = majorDao.findAll();
        for(Major m:majors){
            String majorName = m.getMajorName();
            if(!names.contains(majorName)){
                names.add(majorName);
            }
        }
        resultInfo.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        resultInfo.setResultObj(names);
        return resultInfo;
    }
}
