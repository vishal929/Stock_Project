import org.yaml.snakeyaml.Yaml;
import java.io.*;


//purpose of YAML file is for organization, readability, and flexibility
//any other language can use the yaml file and I can just send YAML files etc. 

public class logicYAML{

	//method to write companyFile object to YAML file
	public static boolean makeYAML(CompanyFile filing) {
		Logger logger = LogManager.getLogger("logger");
		logger.info("serializing data into YAML file...");
		String fileName= ""+filing.getTicker()+""+filing.getFilingYear()+".yml";

		Yaml yaml = new Yaml();

		//testing what yaml will dump:

		System.out.println(yaml.dump(filing));


		//creating the yaml file in directory to read from
		String filingsDirectory="/companyFilings/"+ticker;
		File filings = new File(filingsDirectory);

		//creating name of new filing (should include ticker and year)
		String newFiling =filingsDirectory+"/"+filing.ticker+filing.year+".yml";

		//creating the new file
		File newFile = new File(newFiling);
		if(newFile.createNewFile()){
			//writing the yaml info into the file

			Writer writer = new FileWriter(newFile);	
			yaml.dump(filing,writer,default_flow_style=False);
			writer.close();
			logger.info("successfully dumped data into YAML serialization!");
			return true;

		} else {
			//then for some reason we could not create the new file
			logger.fatal("Not able to create the new file to dump YAML info to! Maybe there is not enough space! Aborting Process!");
			return false;
		}

	}









}
