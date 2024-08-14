package eg.boe.banqueofegypt.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler
    public String handleException(BusinessException e, Model model) {
        model.addAttribute("code", e.getCode());
        model.addAttribute("msg", e.getMessage());
        return "error";
    }
}
