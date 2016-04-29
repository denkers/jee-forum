//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.ejb.util;

public class ValidationUtils 
{
    public static boolean isInRange(String input, int min, int max)
    {
        return input.length() >= min && input.length() < max;
    }
    
    public static boolean isNotNull(String... inputs)
    {
        for(String input : inputs)
            if(input == null) return false;
        
        return true;
    }
    
    public static boolean isAlphanumeric(String... inputs)
    {
        for(String input : inputs)
            if(!input.matches("^[\\w]*$"))
                return false;
        
        return true;
    }
    
    public static boolean isNumeric(String... inputs)
    {
        try
        {
            for(String input : inputs)
                Integer.parseInt(input);
            
            return true;
        }
        
        catch(NumberFormatException e)
        {
            return false;
        }
    }
}
