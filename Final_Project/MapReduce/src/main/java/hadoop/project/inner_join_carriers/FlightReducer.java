package hadoop.project.inner_join_carriers;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class FlightReducer extends Reducer<Text, Text, Text, Text> {

    private static final Text EMPTY_TEXT = new Text(" ");
    private Text tmp = new Text();
    private ArrayList<Text> listA = new ArrayList<>();
    private ArrayList<Text> listB = new ArrayList<>();
    private String joinType = null;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //joinType = context.getConfiguration().get("join.type");
        joinType="inner";
    }

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
       listA.clear();
       listB.clear();
        Iterator<Text> itr = values.iterator();
       while(itr.hasNext()){
           tmp= itr.next();

           if(tmp.charAt(0)=='A'){
               listA.add(new Text(tmp.toString().substring(1)));
           }else if(tmp.charAt(0)=='B'){
               listB.add(new Text(tmp.toString().substring(1)));
           }
       }

       executeJoinLogic(context);
    }

    private void executeJoinLogic(Context context) throws IOException, InterruptedException {
        if(joinType.equals("inner")){
            if(!listA.isEmpty() && !listB.isEmpty()){
                for(Text textA:listA){
                    for(Text textB:listB){
                        context.write(textA,textB);
                    }
                }
            }
        }
    }
}
