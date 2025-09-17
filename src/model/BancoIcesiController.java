package model;

/**
 * Clase que controla las operaciones principales del sistema bancario.
 */

public class BancoIcesiController {

    private Cliente[] clientes;
    private int numClientes;

    public BancoIcesiController(int cantidadClientes) {
        clientes = new Cliente[cantidadClientes];
        numClientes = 0;
    }

/**
     * Descripción: Este método se encarga de registrar un nuevo cliente en el sistema. (Modificador)
     * pre: El arreglo de clientes ha sido inicializado y el controlador ha sido creado.
     * @param nombre != null, nombre del cliente.
     * @param edad > 0, edad del cliente.
     * @param cedula != null, identificador único del cliente.
     * pos: El sistema queda con un nuevo cliente siempre y cuando la cédula no exista previamente,
     *      la edad sea mayor a 0 y existan posiciones disponibles en el arreglo.
     * @return boolean true si el cliente fue registrado, false en caso contrario.
     */

    public boolean registrarCliente(String nombre, int edad, String cedula) {

        if (edad <= 0) {
        return false;
    }

      for (int i = 0; i < numClientes; i++) {
            if (clientes[i].getCedula().equals(cedula)) {
                return false;
            }}

        if (numClientes < clientes.length) {
            clientes[numClientes] = new Cliente(nombre, edad, cedula);
            numClientes++;
            return true;
        }
        return false;
    }
    
 /**
     * Descripción: Busca un cliente en el sistema a partir de su cédula.(Analizador)
     * pre: El arreglo de clientes ha sido inicializado.
     * @param cedula != null, número de cédula a buscar.
     * pos: No modifica el estado del sistema.
     * @return Cliente encontrado o null si no existe.
     */


    public Cliente buscarCliente(String cedula) {
        for (int i = 0; i < numClientes; i++) {
            if (clientes[i].getCedula().equals(cedula)) {
                return clientes[i];
            }
        }
        return null;
    }

    private TipoCuenta escogerCuenta(int opcion){
        if (opcion == 1) {
            return TipoCuenta.AHORROS;
        } else {
            return TipoCuenta.CORRIENTE;
        }
    }

/**
     * Descripción: Agrega una cuenta bancaria a un cliente existente.(Modificador)
     * pre: El cliente debe existir en el sistema.
     * @param cedula != null, cédula del cliente.
     * @param numeroCuenta != null, número único de la cuenta.
     * @param nombreBanco != null, nombre del banco de la cuenta.
     * @param tipoOpcion entero (1 = Ahorros, 2 = Corriente).
     * @param saldoInicial >= 0, saldo inicial de la cuenta.
     * pos: El cliente queda con una nueva cuenta siempre que no exista otra con el mismo número y banco,
     *      y el cliente no haya alcanzado el máximo de cuentas permitidas.
     * @return boolean true si la cuenta fue creada, false en caso contrario.
     */

     public boolean agregarCuentaCliente(String cedula, String numeroCuenta, String nombreBanco, int tipoOpcion, double saldoInicial) {
        Cliente c = buscarCliente(cedula);
        if (c != null) {
            TipoCuenta tipoCuenta = escogerCuenta(tipoOpcion);
            Cuenta cuenta = new Cuenta(numeroCuenta, nombreBanco, tipoCuenta, saldoInicial);
            return c.agregarCuenta(cuenta);
        }
        return false;
    }
        
/**
     * Descripción: Deposita dinero en una cuenta bancaria de un cliente.(Modificador)
     * pre: El cliente y la cuenta deben existir en el sistema.
     * @param cedula != null, cédula del cliente.
     * @param numeroCuenta != null, número de la cuenta.
     * @param nombreBanco != null, nombre del banco de la cuenta.
     * @param monto > 0, cantidad de dinero a depositar.
     * pos: El saldo de la cuenta aumenta en el monto especificado si el cliente y la cuenta existen.
     * @return boolean true si el depósito fue exitoso, false en caso contrario.
     */

      public boolean depositarDineroCuenta(String cedula, String numeroCuenta, String nombreBanco, double monto) {
        
         if (monto <= 0) {
            return false;
        }

        Cliente c = buscarCliente(cedula);
        if (c != null) {
            Cuenta cuenta = c.buscarCuenta(numeroCuenta, nombreBanco);
            if (cuenta != null) {
                return cuenta.depositar(monto);
            }
        }
        return false;
    }


 /**
     * Descripción: Retira dinero de una cuenta bancaria de un cliente.(Modificador)
     * pre: El cliente y la cuenta deben existir en el sistema.
     * @param cedula != null, cédula del cliente.
     * @param numeroCuenta != null, número de la cuenta.
     * @param nombreBanco != null, nombre del banco de la cuenta.
     * @param monto > 0, cantidad de dinero a retirar.
     * pos: El saldo de la cuenta disminuye en el monto indicado siempre y cuando el cliente exista,
     *      la cuenta sea válida y tenga fondos suficientes.
     * @return boolean true si el retiro fue exitoso, false en caso contrario.
     */
    
    public boolean retirarDineroCuenta(String cedula, String numeroCuenta, String nombreBanco, double monto) {

        if (monto <= 0) {
            return false;
        }
        Cliente c = buscarCliente(cedula);
        if (c != null) {
            Cuenta cuenta = c.buscarCuenta(numeroCuenta, nombreBanco);
            if (cuenta != null) {
                return cuenta.retirar(monto);
            }
        }
        return false;
    }
    
 /**
     * Descripción: Consulta la información de un cliente registrado en el sistema.(Analizador)
     * pre: El arreglo de clientes ha sido inicializado.
     * @param cedula != null, cédula del cliente.
     * pos: No modifica el estado del sistema.
     * @return String con la información del cliente o un mensaje de error si no existe.
     */

    public String consultarCliente(String cedula){
        Cliente c = buscarCliente(cedula);
        if (c != null) {
            return c.toString();
        }
        return "---CLIENTE NO ENCONTRADO, REVISE LOS DATOS INGRESADOS---";
    }

    
 /**
     * Descripción: Calcula el saldo total de todas las cuentas en el sistema.(Analizador)
     * pre: El arreglo de clientes ha sido inicializado.
     * pos: No modifica el estado del sistema.
     * @return double con el saldo total acumulado en todas las cuentas de todos los clientes.
     */

public double consultarSaldoTotalSistema() {
    double total = 0;
    for (int i = 0; i < numClientes; i++) {
        Cliente c = clientes[i];
        for (int j = 0; j < c.getNumCuentas(); j++) {
            total += c.getCuentas()[j].getSaldo(); 
        }
    }
    return total;
}
}