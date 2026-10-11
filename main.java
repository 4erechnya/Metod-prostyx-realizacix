import java.util.Scanner;

class SimpleIteration {

    static double f(double x) {
        return x * x * x - x - 2;
    }

    static double phi(double x) {
        return Math.cbrt(x + 2);
    }

    static void simpleIteration(double x0, double eps, int maxIter) {
        System.out.println("\n=== Метод простых итераций ===");
        System.out.printf("%-6s %-15s %-15s %-15s%n",
                "Итер", "x", "phi(x)", "|x_new - x|");

        double x = x0;
        double xNew;
        int iter = 0;
        boolean converged = false;

        while (iter < maxIter) {
            iter++;
            xNew = phi(x);

            System.out.printf("%-6d %-15.8f %-15.8f %-15.8f%n",
                    iter, x, xNew, Math.abs(xNew - x));

            if (Math.abs(xNew - x) < eps) {
                System.out.println("Достигнута заданная точность.");
                x = xNew;
                converged = true;
                break;
            }
            x = xNew;
        }

        if (!converged) {
            System.out.println("⚠ Точность не достигнута за " + maxIter + " итераций.");
        }

        System.out.printf("Найденный корень: x = %.8f%n", x);
        System.out.printf("Значение f(x) = %.8f%n", f(x));
        System.out.printf("Количество итераций: %d%n", iter);
    }

    public static void main(String[] args) {
        // ===== Параметры заданы прямо в коде =====
        double a = 1.0;
        double b = 2.0;
        double eps = 0.0001;
        double x0 = 1.5;
        int maxIter = 100;

        System.out.println("Решение уравнения: x^3 - x - 2 = 0");
        System.out.println("Метод простых итераций. Отрезок: [" + a + "; " + b + "]");

        if (f(a) * f(b) > 0) {
            System.out.println("\n⚠ Внимание: на концах отрезка функция не меняет знак!");
        }

        // Проверка условия сходимости |phi'(x)| < 1
        double dPhi = 1.0 / (3 * Math.cbrt(Math.pow(x0 + 2, 2)));
        System.out.printf("|phi'(x0)| = %.6f %s%n", Math.abs(dPhi),
                Math.abs(dPhi) < 1 ? "(< 1 — сходимость есть)" : "(>= 1 — может не сойтись!)");

        simpleIteration(x0, eps, maxIter);
    }
}
