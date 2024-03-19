package e2e.steps.example;

import org.junit.jupiter.api.Test;

public class Lilia {
	// MISSION 1: Sumar 2 variables
	// MISSION 2: Concatenar 2 String (Nombre y Apellido) "" + ""

	public static Integer num1 = 9;
	public static Float num2 = 17.5f;
	public static Float sum = num1 + num2;
	public static String cadena1 = " Obi-Wan ";
	public static String cadena2 = "Kenobi";

	// Crear un metodo para realizar eso
	public Float sumNumbers(Integer number1, Float number2) {
		return number1 + number2;
	}

	public String concatStrings(String c1, String c2) {
		return c1 + c2;
	}

	// Imprimir los resultados en la Consola con System.out.print.
	@Test
	public void ImprimirResultador() {
		Float result = sumNumbers(num1, num2);
		System.out.println("El resultado es:" + result);

		String concat = concatStrings(cadena1, cadena2);
		System.out.println("El jedi master" + concat);
	}

}
