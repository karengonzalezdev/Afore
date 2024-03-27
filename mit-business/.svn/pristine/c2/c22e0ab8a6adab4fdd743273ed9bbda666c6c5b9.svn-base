package mx.profuturo.nci.business.file.generator.bean;

import java.util.List;

import org.beanio.annotation.Group;
import org.beanio.annotation.Record;


@Group(minOccurs=0, maxOccurs=-1)
public class BancomerDomiFixLenghtFileRecordBean{
	
	@Record(name="header",minOccurs=1,maxOccurs=1,type=BancomerDomiHeaderRecordBean.class,order=1)
	private BancomerDomiHeaderRecordBean header;
	
	@Record(name="records",minOccurs=1,maxOccurs=-1,type=BancomerDomiRecordRecordBean.class,collection=List.class,order=2)
	private List<BancomerDomiRecordRecordBean> records;
	
	@Record(name="footer",minOccurs=1,maxOccurs=1,type=BancomerDomiFooterRecordBean.class,order=3)
	private BancomerDomiFooterRecordBean footer;

	
	
	public BancomerDomiHeaderRecordBean getHeader() {
		return header;
	}

	public void setHeader(BancomerDomiHeaderRecordBean header) {
		this.header = header;
	}

	public List<BancomerDomiRecordRecordBean> getRecords() {
		return records;
	}

	public void setRecords(List<BancomerDomiRecordRecordBean> records) {
		this.records = records;
	}

	public BancomerDomiFooterRecordBean getFooter() {
		return footer;
	}

	public void setFooter(BancomerDomiFooterRecordBean footer) {
		this.footer = footer;
	}
	
}
