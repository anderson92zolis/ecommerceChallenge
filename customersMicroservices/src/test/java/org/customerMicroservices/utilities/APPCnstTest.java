package org.customerMicroservices.utilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class APPCnstTest {

    @Test
    public void testFinalVariables() {
        //region VARIABLES
        APPCnst appCnst;

        //endregion VARIABLES


        //region TEST INITIALIZATION


        //endregion TEST INITIALIZATION


        //region TESTS
        Assertions.assertEquals("API version", APPCnst.API_VERSION);
        Assertions.assertEquals("Endpoint version", APPCnst.ENDPOINT_VERSION);
        Assertions.assertEquals("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
                APPCnst.EMAIL_REGEX);
        //endregion TESTS

    }

}