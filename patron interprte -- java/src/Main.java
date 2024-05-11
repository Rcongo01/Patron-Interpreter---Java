import java.util.Scanner;

// Paso 1: Interfaz ExpresionCajero
interface ExpresionCajero {
    double interpretar(Contexto contexto);
}

// Paso 2: Clase Contexto
class Contexto {
    private double saldo;

    public Contexto(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

// Paso 3: Clase ConsultarSaldo
class ConsultarSaldo implements ExpresionCajero {
    @Override
    public double interpretar(Contexto contexto) {
        return contexto.getSaldo();
    }
}

// Paso 4: Clase DepositarDinero
class DepositarDinero implements ExpresionCajero {
    private double monto;

    public DepositarDinero(double monto) {
        this.monto = monto;
    }

    @Override
    public double interpretar(Contexto contexto) {
        double nuevoSaldo = contexto.getSaldo() + monto;
        contexto.setSaldo(nuevoSaldo);
        return nuevoSaldo;
    }
}

// Paso 5: Clase RetirarDinero
class RetirarDinero implements ExpresionCajero {
    private double monto;

    public RetirarDinero(double monto) {
        this.monto = monto;
    }

    @Override
    public double interpretar(Contexto contexto) {
        double saldoActual = contexto.getSaldo();
        if (saldoActual >= monto) {
            double nuevoSaldo = saldoActual - monto;
            contexto.setSaldo(nuevoSaldo);
            return nuevoSaldo;
        } else {
            return -1; // Saldo insuficiente
        }
    }
}

// Clase principal para probar el código
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Contexto contexto = new Contexto(1000); // Saldo inicial

        while (true) {
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            ExpresionCajero operacion = null;

            switch (opcion) {
                case 1:
                    operacion = new ConsultarSaldo();
                    break;
                case 2:
                    System.out.print("Ingrese la cantidad a depositar: ");
                    double deposito = scanner.nextDouble();
                    operacion = new DepositarDinero(deposito);
                    break;
                case 3:
                    System.out.print("Ingrese la cantidad a retirar: ");
                    double retiro = scanner.nextDouble();
                    operacion = new RetirarDinero(retiro);
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida");
                    continue;
            }

            double nuevoSaldo = operacion.interpretar(contexto);
            if (nuevoSaldo >= 0) {
                System.out.println("Nuevo saldo: " + nuevoSaldo);
            } else {
                System.out.println("Saldo insuficiente");
            }
        }
    }
}
