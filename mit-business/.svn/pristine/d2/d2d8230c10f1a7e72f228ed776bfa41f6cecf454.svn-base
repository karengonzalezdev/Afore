package mx.profuturo.nci.business.file.generator.bean;

import java.util.List;

import org.beanio.annotation.Group;
import org.beanio.annotation.Record;


@Group(minOccurs=0, maxOccurs=-1)
public class BanamexDomiFixLenghtFileRecordBean{
	
	@Record(name="header",minOccurs=1,maxOccurs=1,type=BanamexDomiHeaderRecordBean.class,order=1)
	private BanamexDomiHeaderRecordBean header;
	
	@Record(name="records",minOccurs=1,maxOccurs=-1,type=BanamexDomiRecordRecordBean.class,collection=List.class,order=2)
	private List<BanamexDomiRecordRecordBean> records;
	
	@Record(name="footer",minOccurs=1,maxOccurs=1,type=BanamexDomiFooterRecordBean.class,order=3)
	private BanamexDomiFooterRecordBean footer;
	
	public BanamexDomiHeaderRecordBean getHeader() {
		return header;
	}

	public void setHeader(BanamexDomiHeaderRecordBean header) {
		this.header = header;
	}

	public List<BanamexDomiRecordRecordBean> getRecords() {
		return records;
	}

	public void setRecords(List<BanamexDomiRecordRecordBean> records) {
		this.records = records;
	}

	public BanamexDomiFooterRecordBean getFooter() {
		return footer;
	}

	public void setFooter(BanamexDomiFooterRecordBean footer) {
		this.footer = footer;
	}
	
}
