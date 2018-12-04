package com.jwt.model;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class SortByDateClassDay implements Comparator<ClassDay> 
{ 
    // Used for sorting in ascending order of Date
    public int compare(ClassDay a, ClassDay b) 
    { 
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	try {
	    	Date date1 = sdf.parse(a.getDateClass());
	        Date date2 = sdf.parse(b.getDateClass());
	        return date1.compareTo(date2);
    	}catch(Exception e) {
    	}
		return 0;
    } 
} 