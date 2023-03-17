package ie.umbrella.db.bikes.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DublinBikeControllerAdvice {
    @ExceptionHandler(ServiceLayerException.class)
    public ModelAndView handleServiceLayerException(ServiceLayerException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", e.getMessage());
        mav.setViewName("error");
        return mav;
    }
}
