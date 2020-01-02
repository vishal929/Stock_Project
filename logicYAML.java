import org.yaml.snakeyaml.Yaml;
import java.io.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




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
		String filingsDirectory="./companyFilings/"+filing.getTicker();
		File filings = new File(filingsDirectory);

		//creating name of new filing (should include ticker and year)
		String newFiling =filingsDirectory+"/"+filing.getTicker()+filing.getFilingYear()+".yml";

		//creating the new file
		File newFile = new File(newFiling);
		try{
			newFile.createNewFile();
			//writing the yaml info into the file

			Writer writer = new FileWriter(newFile);	
			yaml.dump(filing,writer);
			writer.close();
			logger.info("successfully dumped data into YAML serialization!");
			return true;

		}catch(IOException e){
			//then for some reason we could not create the new file
			logger.fatal("Not able to create the new file to dump YAML info to! Maybe there is not enough space! Aborting Process!");
			return false;
		}

	}
	
	//method to load the yamlFile to construct the CompanyFile object basically
	public static CompanyFile load(File yamlFile){

		Yaml yaml = new Yaml(getConstructor(CompanyFile.class));
		//loading the CompanyFile object from a yamlFile to "read" the yaml data

		InputStream in = CompanyFile.class.getClassLoader().getResourceAsStream(yamlFile.getAbsolutePath());
		CompanyFile filing = yaml.load(in);

		return filing;
	}









}
