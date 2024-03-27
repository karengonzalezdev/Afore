package mx.profuturo.nci.business.file.generator.bean;

import java.util.List;

import org.beanio.annotation.Group;
import org.beanio.annotation.Record;


@Group(minOccurs=0, maxOccurs=-1)
public class BancomerDomiFixLenghtFileRecordBeanPar{
	
	@Record(name="header",minOccurs=1,maxOccurs=1,type=BancomerDomiHeaderRecordBeanPar.class,order=1)
	private BancomerDomiHeaderRecordBeanPar header;
	
	@Record(name="records",minOccurs=1,maxOccurs=-1,type=BancomerDomiRecordRecordBeanPar.class,collection=List.class,order=2)
	private List<BancomerDomiRecordRecordBeanPar> records;
	
	@Record(name="footer",minOccurs=1,maxOccurs=1,type=BancomerDomiFooterRecordBean.class,order=3)
	private BancomerDomiFooterRecordBean footer;

	
	
	public BancomerDomiHeaderRecordBeanPar getHeader() {
		return header;
	}

	public void setHeader(BancomerDomiHeaderRecordBeanPar header) {
		this.header = header;
	}

	public List<BancomerDomiRecordRecordBeanPar> getRecords() {
		return records;
	}

	public void setRecords(List<BancomerDomiRecordRecordBeanPar> records) {
		this.records = records;
	}

	public BancomerDomiFooterRecordBean getFooter() {
		return footer;
	}

	public void setFooter(BancomerDomiFooterRecordBean footer) {
		this.footer = footer;
	}
	
}
