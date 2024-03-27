package idcBusiness;

import idc.persistence.SecureIDCPersistence;

@Service("idcService")
public class IDCServiceImpl implements IDCService{

	private static final Logger LOGGER = LoggerFactory.getLogger(IDCServiceImpl.class);
	
	@Autowired
	SecureIDCPersistence idcPersistence;
	
	
}
