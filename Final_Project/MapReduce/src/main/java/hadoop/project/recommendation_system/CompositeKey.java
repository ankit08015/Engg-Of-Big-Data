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

    private String stockSymbol;
    private String date;

    public CompositeKey() {
        super();
    }
    

    public CompositeKey(String stockSymbol, String date) {
        this.stockSymbol = stockSymbol;
        this.date = date;
    }    
    

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    @Override
    public void write(DataOutput d) throws IOException {
        d.writeUTF(date);
        d.writeUTF(stockSymbol);
    }

    @Override
    public void readFields(DataInput di) throws IOException {
        date = di.readUTF();
        stockSymbol= di.readUTF();
    }

    @Override
    public int compareTo(CompositeKey o) {
        int result = this.stockSymbol.compareTo(o.getStockSymbol());
        if(result==0){
            return this.date.compareTo(o.getDate());
        }
        
        return result;
    }

    @Override
    public String toString() {
        return stockSymbol + ", " + date;
    }
    
    
    
}
