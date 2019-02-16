public class Integral extends Thread{

	public static final double NUMBER_OF_THREADS = 10; //число потоков
	public static final double A = -1; //предел интегрированния
	public static final double B = 1; //предел интегрированния
	public static final double DELTA = 0.01; // минимальная разница между значениями
	private boolean nextThread = false ;
    private double sum = 0; // Результат суммы в каждом отрезке
    private static double step = (B - A)/NUMBER_OF_THREADS/NUMBER_OF_THREADS; // минимальный шаг
    private double number; //шаг внутри отрезка
	private double numberOfSteps=0; // число сделанных шагов

    public Integral(double numberOfSteps) {
		this.numberOfSteps = numberOfSteps;	//передача значения в поток
    }
	
	@Override
    public void run() {
		double turn = NUMBER_OF_THREADS;
		while(this.nextThread == false){
		this.sum=0;
		for(int i = 0; i < turn ; i++){	
			this.number = A + numberOfSteps + this.step * i ;
			double result = Math.pow(this.number , 2);
			double result2 = Math.pow(this.number + this.step , 2);
			//System.out.println("Шаг= " + this.number + " Результат= " + result + " Delta= " + Math.abs(result2-result));
			this.nextThread = true ;
			if(Math.abs(result2-result)>DELTA){
			this.nextThread = false ;
			}
		this.sum = this.sum + result;
		}
		turn = turn + 1;
		this.step = (B - A)/NUMBER_OF_THREADS/turn;
		}
		this.sum = this.sum * this.step;
		this.numberOfSteps = this.numberOfSteps + A;
		System.out.println("Сумма = " + this.sum + " Ходы FOR = " + turn);
    }
	
	public double getSum() {
		return sum; //возвращает значение потока в главный поток
    }
 
    public static void main(String[] args) {
		
		double numberOfSteps = 0 ;
		double finish = 0;
		for(int i = 0 ; i < NUMBER_OF_THREADS; i++)
			{
				Integral integral = new Integral(numberOfSteps);
				integral.start();//запуск потока
				numberOfSteps = numberOfSteps + (B - A)/NUMBER_OF_THREADS;//меняю пределы интегрированния следущего отрезка
				while(integral.isAlive()){}
				finish = finish + integral.getSum(); //складываю результаты потоков
			}
			System.out.println(">>>>>" + finish + "<<<<<"); //вывод конечного результата 
    }
}