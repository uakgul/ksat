package com.example.ksat.exception;

public class InstallmentNotFoundException extends TaskHandlerException{
    public InstallmentNotFoundException() {
        super("Installment not found");
    }
}
