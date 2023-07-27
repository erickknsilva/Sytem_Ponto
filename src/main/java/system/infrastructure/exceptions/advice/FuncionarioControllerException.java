package system.infrastructure.exceptions.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import system.controller.FuncionarioController;
import system.controller.PontoController;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ControllerAdvice(assignableTypes = FuncionarioController.class)
public @interface FuncionarioControllerException {

}
