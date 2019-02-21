public class Integral extends Thread{

	public static final int NUMBER_OF_THREADS = 10; //число потоков
	public static final double A = -1; //предел интегрированния
	public static final double B = 1; //предел интегрированния
	public static final double DELTA = 0.0001; // минимальная разница между значениями
	
	//private boolean nextThread = false ; // Условие выхода из цикла
    private double sum = 0; // Результат суммы в каждом отрезке
    //private static double step = (B - A)/NUMBER_OF_THREADS/NUMBER_OF_THREADS; // минимальный шаг
    private double number; //шаг внутри отрезка
	private double numberOfSteps=0; // число сделанных шагов
	public static double finish ;
	private final Object lock = new Object();


    public Integral(double numberOfSteps) {
		this.numberOfSteps = numberOfSteps;	//передача значения в поток
    }
	
	 public void finish() {
		System.out.println(">>>>>" + this.finish + "<<<<<"); //вывод конечного результата 
    }
	
	public double myMethod(double sum) {
		synchronized (lock) {
		return this.finish = this.finish + this.sum;
		}
	}
	
	@Override
    public void run() {
		
		boolean nextThread = false ;
		double step = (B - A)/NUMBER_OF_THREADS/NUMBER_OF_THREADS;
		double turn = NUMBER_OF_THREADS; // Кол-во отрезков , на которые разбивается поток
		
		while(nextThread == false){//выполняется пока условие DELTA не соблюдается 
		this.sum=0;
		for(int i = 0; i < turn; i++){	
			this.number = A + numberOfSteps + step * i ;
			double result = Math.pow(this.number ,2); //здесь задается интегральная функция 
			double result2 = Math.pow(this.number + step ,2);
			nextThread = true ;
			if(Math.abs(result2-result)>DELTA){ //проверка условия DELTA
			nextThread = false ;
			}
		this.sum = this.sum + result;
		}
		turn = turn + 1;
		step = (B - A)/NUMBER_OF_THREADS/turn;
		}
		this.sum = this.sum * step;
		this.numberOfSteps = this.numberOfSteps + A;
		System.out.println("Сумма = " + this.sum + " Ходы FOR = " + turn);
	    myMethod(this.sum);
    }
	
	
	public double getSum() {
		return sum; //возвращает значение потока в главный поток
    }
	
 
    public static void main(String[] args) {
		double numberOfSteps = 0 ;
		//double finish = 0;
		Integral myThreads[] = new Integral[NUMBER_OF_THREADS];
		for(int i = 0 ; i < NUMBER_OF_THREADS; i++)
			{
				Integral integral = new Integral(numberOfSteps);
				integral.start();//запуск потока
				myThreads[i] = integral;
				numberOfSteps = numberOfSteps + (B - A)/NUMBER_OF_THREADS;//меняю пределы интегрированния следущего отрезка
				//finish = finish + integral.getSum(); //складываю результаты потоков
			}
			for (int j = 0; j < NUMBER_OF_THREADS; j++) {
				try{
					myThreads[j].join();
				}catch(InterruptedException e){}
			}
			//for (int j = 0; j < NUMBER_OF_THREADS; j++) {
			//		
			//finish = finish + myThreads[j].getSum();
			//}
			myThreads[0].finish();
    }
}