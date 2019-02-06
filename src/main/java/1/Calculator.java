import java.util.logging.Level;
import java.util.logging.Logger;
 
 
class Calculator extends Thread{
 
    int n;
    int a = 1;
    double b = 2.4;
    double results[]; 
    
    public Calculator(int n){
       this.n = n;
    }
    
    private void threadCalc(){
        results = new double[n];
        double h = (b - a) / n;
        for (int i = 0; i < n-1; i++)
        {               
            double x = a + i * h;
            results[i] = (x + h /2)  * h ;
        }
    }
   
   public double getResult(){
        int result = 0;
        for (int i = 0; i < n-1; i++)
        {
            result += results[i];
        }
        return result;
   }
   
   @Override
   public void run() {
        threadCalc();
        Calculator[] threads = new Calculator[n];   
                
        for (int i = 0; i < n; i++)
        {               
            threads[i] = new Calculator(i);                    
            threads[i].start();
             
        }
        for (int i = 0; i < n; i++)
        {
            try {                
                threads[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex); 
            }
            
        }
        
   }
}
public class Main {
public static void main(String[] args) {
    Calculator i1 = new Calculator(10);
  }
}