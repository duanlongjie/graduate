package cn.edu.haue.graduate.entity;
import cn.edu.haue.graduate.constant.ResultCode;

import java.util.List;

/**
 * 服务返回结果包装类
 */
public class ResultInfo<T> {
    private Integer resultCode;
    private String resultMessage;
    private T resultObj;
    private List<String> AuditResultMessage;  //毕业审核结果信息

    public ResultInfo() {
        //默认是失败的
        this.resultCode = ResultCode.RESULT_CODE_FAIL;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public T getResultObj() {
        return resultObj;
    }

    public void setResultObj(T resultObj) {
        this.resultObj = resultObj;
    }

    public List<String> getAuditResultMessage() {
        return AuditResultMessage;
    }

    public void setAuditResultMessage(List<String> auditResultMessage) {
        AuditResultMessage = auditResultMessage;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "resultCode=" + resultCode +
                ", resultMessage='" + resultMessage + '\'' +
                ", resultObj=" + resultObj +
                ", AuditResultMessage=" + AuditResultMessage +
                '}';
    }
}
