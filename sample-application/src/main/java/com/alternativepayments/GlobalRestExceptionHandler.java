package com.alternativepayments;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.alternativepayments.http.error.AlternativePaymentException;

/**
 * Global exception handler.
 */
@ControllerAdvice
public class GlobalRestExceptionHandler {

    /**
     * Handle API exceptions and show them on error view.
     *
     * @param req HTTP request
     * @param e exception triggered error handler
     * @return Error view with exception fields.
     */
    @ExceptionHandler(value = AlternativePaymentException.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error/error");
        return mav;
    }

}
