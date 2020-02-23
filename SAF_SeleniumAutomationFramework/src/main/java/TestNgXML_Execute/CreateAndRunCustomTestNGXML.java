package TestNgXML_Execute;

import Config.Enum.BaseConfig;
import Config.YAMLData.YAMLConfigDataRead;
import Config.YAMLData.YAMLTestData;
import org.testng.TestNG;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreateAndRunCustomTestNGXML {

    private static Element addTestAndClass(Document doc, Element parentElement, String TCID, String scriptName) {

        //Test
        Element test1 = doc.createElement("test");
        parentElement.appendChild(test1);

        Attr attr = doc.createAttribute("name");
        attr.setValue(TCID);
        test1.setAttributeNode(attr);

        //Parameter 1
        Element parameter = doc.createElement("parameter");
        test1.appendChild(parameter);

        attr = doc.createAttribute("name");
        attr.setValue("TC_ID");
        parameter.setAttributeNode(attr);

        attr = doc.createAttribute("value");
        attr.setValue(TCID);
        parameter.setAttributeNode(attr);

        //Classes
        Element classesObj = doc.createElement("classes");
        test1.appendChild(classesObj);
        //Class
        Element classObj = doc.createElement("class");
        classesObj.appendChild(classObj);

        attr = doc.createAttribute("name");
        attr.setValue("ExecuteTests."+scriptName);
        classObj.setAttributeNode(attr);

        return parentElement;
    }

    public static void generateCustomExeTestNGXMLFIle(ArrayList<String> exeData) {

        Document doc = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


        //Suite
        Element rootElement = doc.createElement("suite");
        doc.appendChild(rootElement);

        Attr attr = null;
        if (System.getProperty(BaseConfig.isParallel.toString()).equalsIgnoreCase("yes")) {
            attr = doc.createAttribute("parallel");
            attr.setValue("classes");
            rootElement.setAttributeNode(attr);

            String threadCount = System.getProperty(BaseConfig.threadCount.toString());
            attr = doc.createAttribute("thread-count");
            attr.setValue(threadCount);
            rootElement.setAttributeNode(attr);
        }
        attr = doc.createAttribute("name");
        attr.setValue("Suite");
        rootElement.setAttributeNode(attr);

        for (String row : exeData) {
            rootElement = addTestAndClass(doc, rootElement, row.split("###")[0].split("=")[1].toString().trim(),
                    row.split("###")[1].split("=")[1].toString().trim());
        }


        try {
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            String testNGXMLPath = "src/test/testNGXML/TestNG_" + System.currentTimeMillis() + ".xml";
            System.setProperty(BaseConfig.exeTestNGFileName.toString(), testNGXMLPath);

            StreamResult result = new StreamResult(new File(testNGXMLPath));
            transformer.transform(source, result);

           /* // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);*/
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void runTestNGXML(){
        TestNG runner=new TestNG();
        List<String> suitefiles=new ArrayList<String>();
        System.out.println("Path : "+System.getProperty(BaseConfig.exeTestNGFileName.toString()));
        suitefiles.add(System.getProperty(BaseConfig.exeTestNGFileName.toString()));
        runner.setTestSuites(suitefiles);
        runner.run();
    }



    public static void main(String[] args) {

        YAMLConfigDataRead obj = new YAMLConfigDataRead();
        ArrayList<String> exeData = obj.YAMLConfigDataLoad();
        generateCustomExeTestNGXMLFIle(exeData);
        System.out.println(">>>>>>>>>>>> TestNG.XML created successfully <<<<<<<<<<<<<<<<");

        //Load TestData
        new YAMLTestData().configTestCaseTestDataYaml();

        //Run CustomTextNG.XML
        runTestNGXML();
    }
}
