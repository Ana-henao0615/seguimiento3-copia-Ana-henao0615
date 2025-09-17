package ui;

import java.util.Scanner;
import model.BancoIcesiController;

public class BancoIcesiUI {


    public static Scanner reader;
    public static BancoIcesiController bancoApp;

    public static void main(String[] args) {
       reader = new Scanner(System.in);
        bancoApp = new BancoIcesiController(50);
        menu();
    }
    

    public static void menu() {

        System.out.println("Bienvenido a BancoIcesi");

        int option = 0;
        do {
            System.out.println("\nMenu Principal");
            System.out.println("-".repeat(50));
            System.out.println("Digite alguna de las siguientes opciones");
            System.out.println("1) Registrar cliente");
            System.out.println("2) Asignar cuenta bancaria a cliente");
            System.out.println("3) Depositar dinero en cuenta bancaria de un cliente");
            System.out.println("4) Retirar dinero de una cuenta bancaria de un cliente");
            System.out.println("5) Consultar cliente por numero de cedula");
            System.out.println("6) Consultar el saldo total de todas las cuentas");
            System.out.println("0) Salir del sistema");
            option = reader.nextInt();

            switch (option) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    asignarCuentaBancariaCliente();
                    break;
                case 3:
                    depositarDineroCuenta();
                    break;
                case 4:
                    retirarDineroCuenta();
                    break;
                case 5:
                    consultarCliente();
                    break;
                case 6:
                    consultarSaldoTotal();
                    break;
                case 0:
                    System.out.println("\n---SALIENDO---");
                    break;
                default:
                    System.out.println("\nOpcion invalida. Intente nuevamente.");
                    break;
            }

        } while (option != 0);

    }

   public static void registrarCliente() {

    System.out.println("\n--- Registrar cliente ---");
    reader.nextLine(); 
    System.out.print("Ingrese nombre completo: ");
    String nombre = reader.nextLine();
    System.out.print("Ingrese edad: ");
    int edad = reader.nextInt();
    reader.nextLine(); 
    System.out.print("Ingrese cédula: ");
    String cedula = reader.nextLine();

    boolean registrado = bancoApp.registrarCliente(nombre, edad, cedula);
    if (registrado) {
        System.out.println("Cliente registrado con éxito.");
    } else {
        System.out.println("\n---Error: no se pudo registrar el cliente (cédula duplicada, capacidad llen o error en la edad)");
    }
}

    public static void asignarCuentaBancariaCliente() {

        System.out.println("\n--- Crear cuenta ---");
       reader.nextLine(); 
        System.out.print("Ingrese cédula del cliente: ");
        String cedula = reader.nextLine();
        System.out.print("Ingrese número de cuenta: ");
        String numeroCuenta = reader.nextLine();
        System.out.print("Ingrese nombre del banco: ");
        String banco = reader.nextLine();
        System.out.println("Seleccione tipo de cuenta:(1 o 2)");
        System.out.println("1. Ahorros");
        System.out.println("2. Corriente");
        int tipo = reader.nextInt();
        System.out.print("Ingrese saldo inicial: ");
        double saldo = reader.nextDouble();
        reader.nextLine();

        boolean agregada= bancoApp.agregarCuentaCliente(cedula, numeroCuenta, banco, tipo, saldo);
        if (agregada){
            System.out.println("Cuenta creada con éxito.");
        } else {
            System.out.println("\n --- Error: no se pudo crear la cuenta cliente no encontrado, cuenta duplicada o límite alcanzado---");
        }
    }

    public static void depositarDineroCuenta() {

         System.out.println("\n--- Depositar ---");
         reader.nextLine(); 
        System.out.print("Ingrese cédula del cliente: ");
        String cedula = reader.nextLine();
        System.out.print("Ingrese número de cuenta: ");
        String numeroCuenta = reader.nextLine();
        System.out.print("Ingrese nombre del banco: ");
        String banco = reader.nextLine();
        System.out.print("Ingrese monto a depositar: ");
        double monto = reader.nextDouble();
        reader.nextLine();

        boolean depositado=bancoApp.depositarDineroCuenta(cedula, numeroCuenta, banco, monto);
        if (depositado){
            System.out.println("Depósito realizado con éxito.");
        } else {
            System.out.println("\n ---Error: no se pudo realizar el deposito (cedula, numero de cuenta o nombre de banco incorrectos, monto invalido)---");
        }
    }


    public static void retirarDineroCuenta() {

        System.out.println("\n--- Retirar ---");
        reader.nextLine(); 
        System.out.print("Ingrese cédula del cliente: ");
        String cedula = reader.nextLine();
        System.out.print("Ingrese número de cuenta: ");
        String numeroCuenta = reader.nextLine();
        System.out.print("Ingrese nombre del banco: ");
        String banco = reader.nextLine();
        System.out.print("Ingrese monto a retirar: ");
        double monto = reader.nextDouble();
        reader.nextLine();

        boolean retirado = bancoApp.retirarDineroCuenta(cedula, numeroCuenta, banco, monto);
        if (retirado){
            System.out.println("Retiro realizado con éxito.");
        } else {
            System.out.println("\n ---Error: no se pudo realizar el retiro (cedula, numero de cuenta o nombre de banco incorrectos, monto invalido)---");
    }
}

      public static void consultarCliente() {
        System.out.println("\n--- Consultar cliente ---");
        reader.nextLine(); 
        System.out.print("Ingrese cédula del cliente: ");
        String cedula = reader.nextLine();
        String info = bancoApp.consultarCliente(cedula);
        System.out.println(info);
    }


    public static void consultarSaldoTotal() {

        System.out.println("\n--- Saldo total del sistema ---");
        double total = bancoApp.consultarSaldoTotalSistema();
        System.out.println("Saldo total en el sistema: " + total);
    }
}

