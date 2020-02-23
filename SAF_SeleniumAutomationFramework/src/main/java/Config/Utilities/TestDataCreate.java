package Config.Utilities;

public class TestDataCreate {

    public String getValidTestData(String testData){
        if(!(testData.startsWith("@") || testData.startsWith("<")))
            return testData;
        return null;
    }

}
