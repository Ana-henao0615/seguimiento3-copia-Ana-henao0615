package model;

public class Cliente {
    
    private String nombreCompleto;
    private int edad;
    private String cedula;
    private Cuenta[] cuentas;
    private int numCuentas;

    public static final int MAX_CUENTAS = 10;


    public Cliente(String nombreCompleto, int edad, String cedula){

    this.nombreCompleto=nombreCompleto;
    this.edad=edad;
    this.cedula=cedula;
    this.cuentas = new Cuenta[MAX_CUENTAS]; 
    }
     public boolean agregarCuenta(Cuenta cuenta) {

     for (int i = 0; i < numCuentas; i++) {
            if (cuentas[i].getNumeroCuenta().equals(cuenta.getNumeroCuenta()) &&
                cuentas[i].getNombreBanco().equalsIgnoreCase(cuenta.getNombreBanco())) {
                return false; 
            }
        }

        if (numCuentas < MAX_CUENTAS) {
            cuentas[numCuentas] = cuenta;
            numCuentas++;
            return true;
        }
        return false;
    }
    
     public Cuenta buscarCuenta(String numeroCuenta, String nombreBanco) {
        for (int i = 0; i < numCuentas; i++) {
            if (cuentas[i].getNumeroCuenta().equals(numeroCuenta) &&
                cuentas[i].getNombreBanco().equalsIgnoreCase(nombreBanco)) {
                return cuentas[i];
            }
        }
        return null;
    }

    public String getCuentasList() {
        if (numCuentas == 0) {
            return "El cliente no tiene cuentas registradas.";
        }
        String lista = "";
        for (int i = 0; i < numCuentas; i++) {
            lista = lista + (i+1) + ". " + cuentas[i].toString() + "\n";
        }
        return lista;
    }

    public String toString() {
        String info = "Cliente: " + nombreCompleto + " - Cédula: " + cedula + " - Edad: " + edad + "\nCuentas:\n";
        info = info + getCuentasList();
        return info;
    }

  public int getNumCuentas(){ 
        return numCuentas;
    }

  public String getCedula(){
    return cedula;
  }

  public Cuenta[] getCuentas() {
    return cuentas;
}

}

