package mx.profuturo.nci.business.file.generator.bean;

import java.util.List;

import org.beanio.annotation.Group;
import org.beanio.annotation.Record;


@Group(minOccurs=0, maxOccurs=-1)
public class BanamexDomiFixLenghtFileRecordBeanPar{
	
	@Record(name="header",minOccurs=1,maxOccurs=1,type=BanamexDomiHeaderRecordBeanPar.class,order=1)
	private BanamexDomiHeaderRecordBeanPar header;
	
	@Record(name="records",minOccurs=1,maxOccurs=-1,type=BanamexDomiRecordRecordBeanPar.class,collection=List.class,order=2)
	private List<BanamexDomiRecordRecordBeanPar> records;
	
	@Record(name="footer",minOccurs=1,maxOccurs=1,type=BanamexDomiFooterRecordBean.class,order=3)
	private BanamexDomiFooterRecordBean footer;
	
	public BanamexDomiHeaderRecordBeanPar getHeader() {
		return header;
	}

	public void setHeader(BanamexDomiHeaderRecordBeanPar header) {
		this.header = header;
	}

	public List<BanamexDomiRecordRecordBeanPar> getRecords() {
		return records;
	}

	public void setRecords(List<BanamexDomiRecordRecordBeanPar> records) {
		this.records = records;
	}

	public BanamexDomiFooterRecordBean getFooter() {
		return footer;
	}

	public void setFooter(BanamexDomiFooterRecordBean footer) {
		this.footer = footer;
	}
	
}
