
import acm.io.IOConsole;

public class Methods {
	/**
	 * Prints the given name and surname 10 times.
	 *
	 * @param console The UseMethods object for console output.
	 * @param name    The name to be printed.
	 * @param surname The surname to be printed.
	 */
	public void printNames(UseMethods console, String name, String surname) {
		for (int i = 0; i < 10; i++) {
			console.println("Name: " + name + " Surname: " + surname);
		}
	}
	/**
	 * Prints the square of the first ten positive integers.
	 *
	 * @param console The UseMethods object for console output.
	 */
	public void squareTen(UseMethods console) {
		console.println("Таблиця квадратів перших десяти цілих позитивних чисел:");
		console.println("Число | Квадрат");
		console.println("-------------");

		for (int i = 1; i <= 10; i++) {
			int square = i * i;
			console.println(i + "     | " + square);
		}
	}
	/**
	 * Prints the square of the first 5 even numbers.
	 *
	 * @param console The UseMethods object for console output.
	 */
	public void square5(UseMethods console) {
		console.println("Таблиця квадратів перших 5 парних чисел:");
		console.println("Число | Квадрат");
		console.println("-------------");

		for (int i = 1; i <= 10; i += 2) {
			int square = i * i;
			console.println(i + "     | " + square);
		}
	}
	/**
	 * Prints the sum of the first n natural numbers.
	 *
	 * @param console The UseMethods object for console output.
	 * @param n       The value of n for the sum.
	 */
	public void sumN(UseMethods console,int n) {
		console.println("Сума чисел n:");
		console.println("-------------");
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += i;
			console.println("Sum: " + sum);
		}

	}
	/**
	 * Prints the sum of the first n odd numbers.
	 *
	 * @param console The UseMethods object for console output.
	 * @param n       The value of n for the sum.
	 */
	public void sumOdd(UseMethods console,int n) {
		console.println("Calculate sum of {1, 3, 5... n}: ");
		console.println("-------------");
		int sum = 0;
		for (int i = 1; i <= n*2; i +=2) {
			sum += i;
			console.println("Sum: " + sum);
		}

	}

	/**
	 * Prints the sum of the series {1, 1/2, 1/3, ..., 1/n}.
	 *
	 * @param console The UseMethods object for console output.
	 * @param n       The value of n for the series.
	 */
	public void sum1n(UseMethods console,double n) {
		console.println("Calculate sum of {1, 1/2, 1/3...1/n}: ");
		console.println("-------------");
		double sum = 0;
		for (double i = 1; i <= n; i++) {
			double drob = 1 / i;
			sum += drob;
		}
		console.println("Sum: " + sum);
	}
	/**
	 * Prints the square of 2 to the power of i for i from 0 to 10.
	 *
	 * @param console The UseMethods object for console output.
	 */
	public void square2(UseMethods console) {
		console.println("Таблиця квадратів 2:");
		console.println("Число | Квадрат");
		console.println("-------------");
		for (int i = 0; i <= 10; i++) {
			console.println("2^"+i + "  " + Math.pow(2, i));
		}
	}
	/**
	 * Prints the factorial of the given number n.
	 *
	 * @param console The UseMethods object for console output.
	 * @param n       The value of n for the factorial.
	 */
	public void factorial(UseMethods console,int n) {
		console.println("Factorial of n:");
		console.println("-------------");
		if(n==0)
			console.println("Factorial "+ 1);
		double sum = 1;
		for (int i = 1; i <= n; i++) {
			sum *= i;
		}
		console.println("Factorial "+ sum);
	}
	/**
	 * Prints the result of the function y = -2.4x^2 + 5x - 3 for x from -2 to 2 in steps of 0.5.
	 *
	 * @param console The UseMethods object for console output.
	 * @param n       Unused parameter in the original code.
	 */
	public void function(UseMethods console) {
		console.println("Calculate y=-2.4x^2+5x-3:");
		console.println("-------------");
		double sum = 1;
		for (double i = -2; i <= 2; i += 0.5) {
			sum = -2.4 * i * i + 5 * i - 3;
			console.println("Sum: " + sum);
		}
	}
	/**
	 * Generates 10 random integers between 1 and 10, prints them, and calculates the sum and average.
	 *
	 * @param console The UseMethods object for console output.
	 */
	public void randomAvg(UseMethods console) {
		double sum = 0;
		for (double i = 0; i < 10; i++) {
			int n = ((int) ((Math.random() * 10))) + 1;
			sum += n;
			console.println(((int) (i + 1)) + ". " + n);
		}
		console.println("sum = " + sum);
		console.println("average = " + sum / 10);
	}

	/**
	 * Calculates and displays the price of apples based on weight.
	 *
	 * @param console The UseMethods object for console input/output.
	 * @param n       The weight of apples.
	 */
	public void price(UseMethods console, double n) {
		console.println("Price of apples:");
		console.println("-------------");
        double sum =0;
        for (int i = 1; i <=10; i++) {
            sum+=0.1*+n;
            console.println((i*100) +"gr. cost " + sum + " hrn.");
        }

    }
	/**
	 * Conducts a random math quiz and evaluates the user's performance.
	 *
	 * @param console The UseMethods object for console input/output.
	 */
	public void rndMath(UseMethods console) {

        int count=0;
        for (int i = 1; i <=10; i++) {
            if (Math.random()>0.5){
                int a = (int) (Math.random()*99);
                int possible = 100-a;
                int b = (int) (Math.random()*possible);
                console.print(i+". "+a + "+" +b+ " = ");
                if (console.readInt()==a+b){
                    count++;
                    console.println("Right");
                }else{
                	console.println("Wrong");
                }
            }else {
                int a = (int) (Math.random()*100);
                int b = (int) (Math.random()*a);
                console.print(i+". "+a + "-" +b+ " = ");
                if (console.readInt()==a-b){
                    count++;
                    console.println("Right");
                }else{
                	console.println("Wrong");
                }
            }
        }
        console.println();
        console.println("Your score - " + count);
        if (count>9){
        	console.println("Perfect");
        }else if (count>7){
        	console.println("Well");
        }else if (count>5){
        	console.println("so-so");
        }else console.println("Awful");
    }
	/**
	 * Reads positive numbers from the user, calculates the sum and average,
	 * and displays the results.
	 *
	 * @param console The UseMethods object for console input/output.
	 */
	public void sumEntered(UseMethods console) {

        int count = 0;
        int sum = 0;

        console.println("Введіть позитивні числа (для завершення введіть 0):");

        while (true) {
            int number = console.readInt();

            if (number == 0) {
                break; 
            } else if (number > 0) {
                sum += number; 
                count++; 
            }
        }

        if (count > 0) {
            double average = (double) sum / count; 
            console.println("Сума введених чисел: " + sum);
            console.println("Середнє арифметичне: " + average);
        } else {
            console.println("Не введено жодного позитивного числа.");
        }
	}
	/**
	 * Finds the maximum positive number entered by the user.
	 *
	 * @param console The UseMethods object for console input/output.
	 */
	public void max(UseMethods console) {

        int max=Integer.MIN_VALUE;

        console.println("Введіть позитивні числа для max (для завершення введіть 0):");

        while (true) {
            int number = console.readInt();

            if (number == 0) {
                break; 
            } else if (number > max&&number>0) {
                max= number; 
            }
        }

        if (max != Integer.MIN_VALUE){ 
        	console.println("Max: "+max);
        } 
        else
        	console.println("No numbers entered" );
        
	}
	/**
	 * Checks if a given number is a prime number.
	 *
	 * @param console The UseMethods object for console input/output.
	 * @param n       The number to check for primality.
	 * @return True if the number is prime; otherwise, false.
	 */
	public boolean Task15(UseMethods console,int n) {
		for (int i = 2; i<=Math.sqrt(n); i++) {
            if (n%i==0){
                return false;
            }
        }
        return true;
	}
	/**
	 * Implements a number-guessing game where the user needs to guess a random number.
	 *
	 * @param console The UseMethods object for console input/output.
	 */
	 public void Task16(UseMethods console) {
		 	int guessNum = (int) (Math.random()*100)+1;
	        int num = 0;

	        for (int i = 1; i<=7; i++) {
	        	console.print("Try "+i+": ");
	             num = console.readInt();
	            if (num<1||num>100){
	            	console.println("Input number must be from 1 to 100");
	                i--;
	            }else
	            if (num>guessNum){
	            	console.println("Less");
	            }else if (num<guessNum){
	            	console.println("More");
	            }else {
	            	console.println("YOU WON. In" + i + " attemps");
	                return;
	            }
	        }
	        if (num!=guessNum){
	        	console.println("You lose");
	        }

	    }
	
	
	
	

}
