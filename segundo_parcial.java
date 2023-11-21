package MetodosNumericos;

public class EjercicioPrincipal {

		   // Función cuadrática que representa el costo total
		static double costoTotal(double x) {
			return 2 * x * x - 30 * x + 100;
		}

		// Método de interpolación parabólica
		static double interpolacionParabolica(double x0, double x1, double x2, double tol) {
			// Calcular los valores de la función en los puntos dados
			double fx0 = costoTotal(x0);
			double fx1 = costoTotal(x1);
			double fx2 = costoTotal(x2);

			// Calcular los coeficientes de la interpolación parabólica
			double a0 = fx0;
			double a1 = (fx1 - fx0) / (x1 - x0);
			double a2 = ((fx2 - fx1) / (x2 - x1) - a1) / (x2 - x0);

			// Calcular el valor óptimo de x utilizando la interpolación parabólica
			double x = 0.5 * (x0 + x1 - a1 / a2);
			double fx = costoTotal(x);

			// Iterar hasta que la diferencia entre x y x1 sea menor que la tolerancia especificada
			while (Math.abs(x - x1) > tol) {
				// Actualizar los puntos y valores de la función
				x0 = x1;
				x1 = x2;
				x2 = x;
				fx0 = fx1;
				fx1 = fx2;
				fx2 = fx;

				// Recalcular los coeficientes de la interpolación parabólica
				a0 = fx0;
				a1 = (fx1 - fx0) / (x1 - x0);
				a2 = ((fx2 - fx1) / (x2 - x1) - a1) / (x2 - x0);

				// Calcular el nuevo valor óptimo de x
				x = 0.5 * (x0 + x1 - a1 / a2);
				fx = costoTotal(x);
			}

			// Devolver el valor óptimo de x
			return x;
		}

		public static void main(String[] args) {
			// Puntos iniciales para la interpolación parabólica
			double x0 = 5.0;
			double x1 = 10.0;
			double x2 = 15.0;

			// Tolerancia para la convergencia
			double tol = 1e-6;

			// Aplicar interpolación parabólica para encontrar el valor óptimo de x
			double resultado = interpolacionParabolica(x0, x1, x2, tol);

			// Calcular el costo total óptimo
			double costoOptimo = costoTotal(resultado);

			// Mostrar el resultado
			System.out.println("La cantidad óptima a producir es x = " + resultado);
			System.out.println("El costo total mínimo es C(x) = " + costoOptimo);
		}
