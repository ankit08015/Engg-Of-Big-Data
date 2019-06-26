/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadoop.assignment4.part_4;

/**
 *
 * @author ankit
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SecondarySortComparator extends WritableComparator {
    
       private final static SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd");

	protected SecondarySortComparator() {
		super(CompositeKey.class, true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
            
         

		CompositeKey ck1 = (CompositeKey) a;
		CompositeKey ck2 = (CompositeKey) b;

		int result = ck1.getStockSymbol().compareTo(ck2.getStockSymbol());

		if (result == 0) {
                    try {
                        Date d1= frmt.parse(ck1.getDate());
                        Date d2= frmt.parse(ck2.getDate());
                        
                        return -d1.compareTo(d2);
                    } catch (ParseException ex) {
                        Logger.getLogger(SecondarySortComparator.class.getName()).log(Level.SEVERE, null, ex);
                    }
			
		}

		return result;
	}

	
	
}
