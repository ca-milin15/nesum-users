package com.nesum.nesum.shared.infrastructure;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TestConfig {
    
    public SystemMessage.Error initializeSystemMessage() {
        var systemMessage = new SystemMessage();
        var error = new SystemMessage.Error();
        error.setEmailConstraintError("Se produjo un en la transacción.");
        error.setGeneralTransactionalError("El correo ya registrado.");
        systemMessage.setError(error);
        return systemMessage.getError();
    }
}
