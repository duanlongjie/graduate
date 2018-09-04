package cn.edu.haue.graduate.dao;

import cn.edu.haue.graduate.entity.Major;
import cn.edu.haue.graduate.entity.MajorKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: lnp
 * Date: 2018/6/13
 **/
public interface MajorDao extends JpaRepository<Major, MajorKey> {
    Major getMajorByMajorName(String name);
    Major findMajorByMajorNameAndMajorYears(String majorName, String majorYears);
}
