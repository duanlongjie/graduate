package cn.edu.haue.graduate.service;

import cn.edu.haue.graduate.entity.Major;
import cn.edu.haue.graduate.entity.ResultInfo;

import java.util.List;

/**
 * created by dlj on 2018/9/3 17:01
 */
public interface MajorService {
    /**
     * 获取所有专业
     * @return
     */
    ResultInfo<List<Major>> getAllMajors();

    /**
     * 根据主键获取专业
     * @return
     */
    ResultInfo<Major> getByMajorkey(String name,String years);

    /**
     * 根据专业名称获取专业
     * @return
     */
    ResultInfo<Major> getByMajorName(String majorName);

    /**
     * 获取 学年
     * @return
     */
    ResultInfo<List<String>> getMajorYears();

    /**
     * 获取所有专业名称
     * @return
     */
    ResultInfo<List<String>> getMajorNames();
}
