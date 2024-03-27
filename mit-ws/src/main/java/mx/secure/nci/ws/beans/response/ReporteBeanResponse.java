package mx.secure.nci.ws.beans.response;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(propOrder = { "codRet", "msgRet", "reportePdfInfo" })
public class ReporteBeanResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codRet;
    private String msgRet;
    private String reportePdfInfo;

    public ReporteBeanResponse() {

    }

    public ReporteBeanResponse(String codRet, String msgRet, String reportePdfInfo) {
        this.codRet = codRet;
        this.msgRet = msgRet;
        this.reportePdfInfo = reportePdfInfo;
    }

    public String getCodRet() {
        return codRet;
    }

    public void setCodRet(String codRet) {
        this.codRet = codRet;
    }

    public String getMsgRet() {
        return msgRet;
    }

    public void setMsgRet(String msgRet) {
        this.msgRet = msgRet;
    }

    public String getReportePdfInfo() {
        return reportePdfInfo;
    }

    public void setReportePdfInfo(String reportePdfInfo) {
        this.reportePdfInfo = reportePdfInfo;
    }
}
