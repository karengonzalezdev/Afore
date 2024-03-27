package idcWS;

public class IDCBeanResponse {

	private String totalCli;
	private Long descCli;
	private Long identCli;
	private Long noIdentCli;
	private Long vigCli;
	private Long noVigCli;
	
	public IDCBeanResponse() {}
	
	public IDCBeanResponse(String totalCli, Long descCli, Long identCli, 
			Long noIdentCli, Long vigCli, Long noVigCli) {
		this.totalCli = totalCli;
		this.descCli = descCli;
		this.identCli = identCli;
		this.noIdentCli = noIdentCli;
		this.vigCli = vigCli;
		this.noVigCli = noVigCli;
	}

	public String getTotalCli() {
		return totalCli;
	}
	public void setTotalCli(String totalCli) {
		this.totalCli = totalCli;
	}
	public Long getDescCli() {
		return descCli;
	}
	public void setDescCli(Long descCli) {
		this.descCli = descCli;
	}
	public Long getIdentCli() {
		return identCli;
	}
	public void setIdentCli(Long identCli) {
		this.identCli = identCli;
	}
	public Long getNoIdentCli() {
		return noIdentCli;
	}
	public void setNoIdentCli(Long noIdentCli) {
		this.noIdentCli = noIdentCli;
	}
	public Long getVigCli() {
		return vigCli;
	}
	public void setVigCli(Long vigCli) {
		this.vigCli = vigCli;
	}
	public Long getNoVigCli() {
		return noVigCli;
	}
	public void setNoVigCli(Long noVigCli) {
		this.noVigCli = noVigCli;
	}
	
}
