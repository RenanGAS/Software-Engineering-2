package src.tools;
import java.lang.Math;
import java.text.DecimalFormat;

public class Operations {
	private static final DecimalFormat df = new DecimalFormat("0.00");
	 
    public double average(LinkedList list){
        LinkedList.Node currNode = list.head;
        double sumValues = 0;

        while (currNode != null) {
            sumValues += currNode.data;
            currNode = currNode.next;
        }

        return Double.parseDouble(df.format(sumValues / list.numNodes));
    }

    public double std(LinkedList list){
        LinkedList.Node currNode = list.head;
        double summation = 0;

        double averageList = this.average(list);

        while (currNode != null) {
            double diff = currNode.data - averageList;
            summation += Math.pow(diff, 2);
            currNode = currNode.next;
        }

        return Double.parseDouble(df.format(Math.sqrt(summation / (list.numNodes - 1))));
    }
}