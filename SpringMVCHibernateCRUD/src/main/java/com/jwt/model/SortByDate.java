package com.jwt.model;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class SortByDate implements Comparator<ExitTicket> 
{ 
    // Used for sorting in ascending order of Date
    public int compare(ExitTicket a, ExitTicket b) 
    { 
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	try {
	    	Date date1 = sdf.parse(a.getDateET());
	        Date date2 = sdf.parse(b.getDateET());
	        return date1.compareTo(date2);
    	}catch(Exception e) {
    	}
		return 0;
    } 
} 