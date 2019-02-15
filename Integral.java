public class Integral extends Thread{


	//public static final double MINIMUM = 0.01;
	public static final double NUMBER_OF_THREADS = 10; //число потоков
	public static final double A = 2; //предел интегрированния
	public static final double B = 5; //предел интегрированния

    private double sum=0; // Результат суммы в каждом отрезке
    private static double step = (B - A)/NUMBER_OF_THREADS/NUMBER_OF_THREADS; // минимальный шаг
    private double number; //шаг внутри отрезка
	private double NumberOfSteps; // число сделанных шагов

	
    public Integral(double NumberOfSteps) {
		this.NumberOfSteps = NumberOfSteps;	//передача значения в поток
    }
	
	@Override
    public void run() {
		double turn = 0;
		for(int i = 0; i < NUMBER_OF_THREADS+1; i++) 
		{	
			this.number = A + NumberOfSteps + this.step * i ;
			double result = Math.sqrt(this.number);
			//System.out.println("Шаг= " + this.number + " Результат= " + result);
			this.sum = this.sum + result*this.step ;
		}
		this.NumberOfSteps = this.NumberOfSteps + A;
		System.out.println("Сумма= " + this.sum + "Поток номер = " + this.NumberOfSteps);
		return;
    }
	
	public double getSum() {
		return sum; //возвращает значение потока в главный поток
    }
 
    public static void main(String[] args) {
		
		double NumberOfSteps = 0 ;
		double finish = 0;
		for(int i = 0 ; i < NUMBER_OF_THREADS+1 ; i++)
			{
				Integral integral = new Integral(NumberOfSteps);
				integral.start();//запуск потока
				try 
				{
					integral.sleep(2000);//жду выполнение потока
				} 
				catch(InterruptedException e){}
				
				NumberOfSteps = NumberOfSteps + (B - A)/NUMBER_OF_THREADS;//меняю пределы интегрированния следущего отрезка
				finish = finish + integral.getSum(); //складываю результаты потоков			
			}
			System.out.println(">>>>>" + finish + "<<<<<"); //вывод конечного результата 
    }
}