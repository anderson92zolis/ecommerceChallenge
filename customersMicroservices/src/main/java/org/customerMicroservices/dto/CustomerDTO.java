package org.customerMicroservices.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
    //region ATTRIBUTES
    private UUID id;
    private String name;
    private String dni;
    private AddressDTO address;
    private List<Integer> ordersList;

    //endregion ATTRIBUTES


    //region METHODS: Public
    /**
     * Method to check correct DNI format and correct letter.
     * @param dni Format "00000000A" or "00000000a"
     * @return true / false
     */
    public static boolean validateDNI(String dni){
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
                result = false;
            }
        }

        //endregion ACTIONS


        // OUT
        return result;

    }

    //endregion METHODS: Public

}
