package com.portex.miexperiencia;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import java.util.function.Supplier;

public class BasePrueba {

    private static final String PERO_FUE_LANZADA = " Pero fue lanzada ";
    private static final String SE_ESPERABA_LA_EXCEPCION = "Se esperaba la excepcion ";

    public static <T> void assertThrows(Supplier<T> supplier, Class<? extends Exception> exception, String message) {
        Exception thrownException = Assertions.assertThrows(exception, supplier::get);
        Assertions.assertTrue(thrownException.getMessage().contains(message),
                SE_ESPERABA_LA_EXCEPCION + exception.getCanonicalName() + PERO_FUE_LANZADA
                        + thrownException.getClass().getCanonicalName());
    }

    public static void assertThrows(Executable executable, Class<? extends Exception> exception, String message) {
        Exception thrownException = Assertions.assertThrows(exception, executable);
        Assertions.assertTrue(thrownException.getMessage().contains(message),
                SE_ESPERABA_LA_EXCEPCION + exception.getCanonicalName() + PERO_FUE_LANZADA
                        + thrownException.getClass().getCanonicalName());
    }


}
