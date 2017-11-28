package testscripts;

import org.testng.annotations.Test;
import testbase.TestBase;

import java.io.IOException;

/**
 * Created by Sharif on 11/27/2017.
 */
public class TC003_ConditionalStatement_01 extends TestBase{
    public static int indexSI = 1;
    @Test
    public void testValidation01()throws IOException{
        int a=5;
        if (a==5){
            updateResult(indexSI,"testValidation01","Pass","Test_01");
        }else{
            updateResult(indexSI,"testValidation01","Failed","Test_01");
        }
    }
    @Test
    public void testValidation02()throws IOException{
        int a=10;
        if (a==5){
            updateResult(indexSI,"testValidation01","Pass","Test_01");
        }else{
            updateResult(indexSI,"testValidation01","Failed","Test_01");
        }
    }

}
