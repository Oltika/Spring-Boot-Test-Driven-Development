package com.testing.system.web.controller;

import com.testing.system.web.error.BadRequestException;
import io.micrometer.common.util.StringUtils;

import java.util.UUID;
/*
This class is used to translate a string to a UUID.
It is used to convert the id parameter from the URL to a UUID.
It is also used to convert the id parameter from the request body to a UUID.
*/
public class ControllerUtils {
  public static UUID translateStringToUUID(String id){
    if(StringUtils.isBlank(id)){
      throw new BadRequestException("id cannot be null or empty");
    }
    try{
      return UUID.fromString(id);
    }catch(IllegalArgumentException iae){
      throw new BadRequestException("cannot convert string to uuid");
    }
  }
}
