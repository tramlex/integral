import java.io.*;
import java.util.Date;

public class Integral
{
    public static double InFunction(double x) //Подынтегральная функция
    {
        return Math.sqrt(2 - x*x);
    }
//Весь участок [a,b] делим на n равных частей
 
    public static double CalcIntegral(double a, double b, int n)
    {
 
        double result = 0;
        double h = (b - a) / n; //шаг разбиения отрезка [a;b].
 
        for(int i = 0; i < n; i++)
        {
            result += InFunction(a + h*(i + 0.5)); //Определяем значение yi подынтегральной функции f(x) в каждой части деления.
        }
        result *= h;
        return result;
    }
 
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Date currentTime = new Date(); //начало отсчета  времени
 
        System.out.println("Введите кол-во интервалов разбиения:" );
		int n = Integer.parseInt(reader.readLine());
        double a = 1;
        double b = Math.sqrt(2);
        System.out.println("Результат: "+ CalcIntegral(a,b,n));
        Date newTime = new Date(); //закончили отсчет времени
        long msDelay = newTime.getTime() - currentTime.getTime(); //вычислили разницу между начальным и конечным временем
        System.out.println("Программа работала: " + msDelay + " миллисекунд");
    }
}