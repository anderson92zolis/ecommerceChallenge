package org.customerMicroservices.helpers;

import lombok.experimental.UtilityClass;
import org.customerMicroservices.dto.CustomerDTO;
import org.customerMicroservices.utilities.APPCnst;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class APPMthd {
    //region METHODS: Publics

    /**
     * Methode to verify if the CustomerDTO has been created correctly
     * @param customerDTO Customer structure
     * @param checkUUID if it is true, the UUID verification should be performed
     * @return true / false
     */
    public boolean isCorrectCustomerDTO(CustomerDTO customerDTO, boolean checkUUID) {
        //region VARIABLES
        boolean result = false;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // CHECK UUID
            if (customerDTO != null &&
                    (!checkUUID || isValidUUID(customerDTO.getId().toString())) &&
                    !customerDTO.getPass().isEmpty() && !customerDTO.getPass().isBlank() &&
                    !customerDTO.getName().isEmpty() && !customerDTO.getName().isBlank() &&
                    isValidDNI(customerDTO.getDni())) {
                result = true;
            }
        } catch (Exception ex) {
            //todo
        }

        //endregion ACTIONS


        // OUT
        return result;

    }

    /**
     * Method to check correct DNI format and correct letter.
     * @param dni Format "00000000A" or "00000000a"
     * @return true / false
     */
    public boolean isValidDNI(String dni){
        //region VARIABLES
        boolean result = false;
        char expectedLetter;
        int numericDNI;
        String numericPart;
        String letterPart;
        String validLetters = "TRWAGMYFPDXBNJZSQVHLCKE";

        //endregion VARIABLES


        //region ACTIONS
        // Mains Checks
        if (dni != null && !dni.isEmpty() && dni.matches("\\d{8}[A-Za-z]")){
            try{
                // Check numeric part
                numericPart = dni.substring(0, 8);
                numericDNI = Integer.parseInt(numericPart);

                // Check letter
                letterPart = dni.substring(8).toUpperCase();
                expectedLetter = validLetters.charAt(numericDNI % 23);
                expectedLetter = Character.toUpperCase(expectedLetter);
                result = (expectedLetter == letterPart.charAt(0));

            }catch(NumberFormatException e){
                //todo log error info
            }
        }

        //endregion ACTIONS


        // OUT
        return result;

    }


    /**
     * Method to check if an email is in a valid format.
     *
     * @param email "Email address.
     * @return true and false.
     */
    public boolean isValidEmail(String email) {
        boolean result = false;
        if (email != null) {
            Pattern pattern = Pattern.compile(APPCnst.EMAIL_REGEX);
            Matcher matcher = pattern.matcher(email);
            result = matcher.matches();
        }
        return result;
    }

    /**
     * Method to verify if the UUID has been created correctly.
     * @param uuidIn UUID in string format
     * @return true / false
     */
    public boolean isValidUUID(String uuidIn){
        Pattern pattern = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
        return (uuidIn != null && uuidIn.length() == 36 && pattern.matcher(uuidIn).matches());
    }

    //endregion METHODS: Publics
}
