package com.zipcodewilmington.phone;

import com.zipcodewilmington.exceptions.InvalidPhoneNumberFormatException;
import com.zipcodewilmington.tools.RandomNumberFactory;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by leon on 5/1/17.
 */
public final class PhoneNumberFactory {
    private static final Logger logger = Logger.getGlobal();

    private PhoneNumberFactory() {
        /** This constructor is private
         *  This class is uninstantiable */
    }

    /**
     * @param phoneNumberCount - number of PhoneNumber objects to instantiate
     * @return array of randomly generated PhoneNumber objects
     */ //TODO - Implement logic
    public static PhoneNumber[] createRandomPhoneNumberArray(int phoneNumberCount) throws InvalidPhoneNumberFormatException{
        PhoneNumber [] temp = new PhoneNumber[phoneNumberCount];
        for (PhoneNumber each : temp) {
            each = createRandomPhoneNumber();
        }

        return temp;
    }

    /**
     * @return an instance of PhoneNumber with randomly generated phone number value
     */ //TODO - Implement logic
    public static PhoneNumber createRandomPhoneNumber() throws  InvalidPhoneNumberFormatException{
        String areaCode = "";
        String centralOfficeCode = "";
        String phoneLineCode = "";
        for (int i = 0; i < 3; i++)
            areaCode += RandomNumberFactory.createInteger(0,9);
        for (int i = 0; i < 3; i++)
            centralOfficeCode += RandomNumberFactory.createInteger(0,9);
        for (int i = 0; i < 4; i++)
            phoneLineCode += RandomNumberFactory.createInteger(0,9);

        //System.out.println("bleh " + Integer.parseInt(areaCode) + Integer.parseInt(centralOfficeCode) + Integer.parseInt(phoneLineCode));
        return createPhoneNumberSafely(Integer.parseInt(areaCode), Integer.parseInt(centralOfficeCode), Integer.parseInt(phoneLineCode));
    }


    /**
     * @param areaCode          - 3 digit code
     * @param centralOfficeCode - 3 digit code
     * @param phoneLineCode     - 4 digit code
     * @return a new phone number object
     */ //TODO - if input is valid, return respective PhoneNumber object, else return null
    public static PhoneNumber createPhoneNumberSafely(int areaCode, int centralOfficeCode, int phoneLineCode) throws  InvalidPhoneNumberFormatException{
        boolean isGarbagePhone = false;

        String areaCodez = String.valueOf(areaCode);
        String centralCodes = String.valueOf(centralOfficeCode);
        String phoneLinez = String.valueOf(phoneLineCode);

        String msg = areaCodez + centralCodes + phoneLinez;

        if((areaCodez.length() == 1 && centralCodes.length() == 1 && phoneLinez.length() == 1))
            isGarbagePhone = true;

        while(areaCodez.length() < 3){
            String fluff = "0";
            areaCodez = fluff + areaCodez;
        }

        while(centralCodes.length() < 3){
            String fluff = "0";
            centralCodes = fluff + centralCodes;
        }

        while(phoneLinez.length() < 4){
            String fluff = "0";
            phoneLinez = fluff + phoneLinez;
        }

        try{
            if(isGarbagePhone)
                throw new InvalidPhoneNumberFormatException();
            //System.out.println("(" + areaCodez + ")-" + centralCodes + "-" + phoneLinez);
            return new PhoneNumber("(" + areaCodez + ")-" + centralCodes + "-" + phoneLinez);
        }catch(InvalidPhoneNumberFormatException e){
            msg += ": is not a valid phone number";
            logger.log(Level.WARNING, msg);
            return null;
        }
    }


    /**
     * @param phoneNumberString - some String corresponding to a phone number whose format is `(###)-###-####`
     * @return a new phone number object
     * @throws InvalidPhoneNumberFormatException - thrown if phoneNumberString does not match acceptable format
     */ // TODO - Add throws statement to method signature
    public static PhoneNumber createPhoneNumber(String phoneNumberString) throws InvalidPhoneNumberFormatException{
        String msg = "Attempting to create a new PhoneNumber object with a value of: " + phoneNumberString;
        logger.log(Level.INFO, msg);
        PhoneNumber bleh = new PhoneNumber(phoneNumberString);
        return bleh;
    }
}
