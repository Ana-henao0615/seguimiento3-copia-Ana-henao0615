package model;

public class Cuenta {

    private String nombreBanco;
    private TipoCuenta tipoCuenta;
    private double saldo;
    private String numeroCuenta;

    public Cuenta(String numeroCuenta,String nombreBanco, TipoCuenta tipoCuenta, double saldoInicial){
        if (saldoInicial < 0) {
            saldoInicial = 0;
        } 
        this.numeroCuenta=numeroCuenta;
        this.nombreBanco = nombreBanco;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldoInicial;
       
    }

    public boolean depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            return true;
        }
        return false;
    }

     public boolean retirar(double monto) {
        if (monto > 0 && saldo >= monto) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    public String toString() {
        return "Numero de cuenta: " + numeroCuenta +"Banco: " + nombreBanco + ", Tipo: " + tipoCuenta + ", Saldo: " + saldo;
    }

     public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

     public double getSaldo() {
        return saldo;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta; 
    }

}


