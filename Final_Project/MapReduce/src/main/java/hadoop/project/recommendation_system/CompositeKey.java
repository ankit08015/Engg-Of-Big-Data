/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hadoop.project.recommendation_system;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 *
 * @author ankit
 */
public class CompositeKey implements WritableComparable<CompositeKey>{

    private String srcDest;
    private String carrierInfo;

    public CompositeKey() {
        super();
    }

    public String getSrcDest() {
        return srcDest;
    }

    public void setSrcDest(String srcDest) {
        this.srcDest = srcDest;
    }

    public String getCarrierInfo() {
        return carrierInfo;
    }

    public void setCarrierInfo(String carrierInfo) {
        this.carrierInfo = carrierInfo;
    }

    public CompositeKey(String srcDest, String carrierInfo) {
        this.srcDest = srcDest;
        this.carrierInfo = carrierInfo;
    }

    @Override
    public void write(DataOutput d) throws IOException {
        d.writeUTF(srcDest);
        d.writeUTF(carrierInfo);
    }

    @Override
    public void readFields(DataInput di) throws IOException {
        srcDest = di.readUTF();
        carrierInfo = di.readUTF();
    }

    @Override
    public int compareTo(CompositeKey o) {
        int result = this.srcDest.compareTo(o.getSrcDest());
        if(result==0){
            String c1 = this.carrierInfo;
            Double rms1 = Double.parseDouble(c1.split("\t")[1]);

            String c2 = o.getCarrierInfo();
            Double rms2 = Double.parseDouble(c2.split("\t")[1]);
            return rms1.compareTo(rms2);
        }
        
        return result;
    }

    @Override
    public String toString() {
        return srcDest + " : " + carrierInfo;
    }
}
