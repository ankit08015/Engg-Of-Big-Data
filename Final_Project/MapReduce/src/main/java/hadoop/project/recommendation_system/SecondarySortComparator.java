/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hadoop.project.recommendation_system;

/**
 *
 * @author ankit
 */

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SecondarySortComparator extends WritableComparator {
    
       private final static SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd");

	protected SecondarySortComparator() {
		super(CompositeKey.class, true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
            
         

		CompositeKey ck1 = (CompositeKey) a;
		CompositeKey ck2 = (CompositeKey) b;

		int result = ck1.getSrcDest().compareTo(ck2.getSrcDest());

		if (result == 0) {
			String c1 = ck1.getCarrierInfo();
			Double rms1 = Double.parseDouble(c1.split("\t")[1]);

			String c2 = ck2.getCarrierInfo();
			Double rms2 = Double.parseDouble(c2.split("\t")[1]);
			result =  rms1.compareTo(rms2);
			
		}

		return result;
	}

	
	
}
