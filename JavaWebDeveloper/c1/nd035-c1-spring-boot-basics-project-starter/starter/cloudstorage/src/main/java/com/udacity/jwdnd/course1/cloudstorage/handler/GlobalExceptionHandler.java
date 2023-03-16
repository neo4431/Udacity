package com.udacity.jwdnd.course1.cloudstorage.handler;

import com.udacity.jwdnd.course1.cloudstorage.exception.AccessDeniedException;
import com.udacity.jwdnd.course1.cloudstorage.exception.DataNotFoundException;
import com.udacity.jwdnd.course1.cloudstorage.exception.SameNameException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler({ DataNotFoundException.class, AccessDeniedException.class, SameNameException.class })
        public String handleBusinessException(Exception e, Model model) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/result";
        }

        @ExceptionHandler(Exception.class)
        public String handleUnwantedException(Exception e, Model model, HttpServletResponse response) {
            Object status = response.getStatus();
            if (status != null) {
                int statusCode = Integer.parseInt(status.toString());

                if (statusCode == HttpStatus.NOT_FOUND.value()) {
                    model.addAttribute("errorMessage", "The page you are looking for does not exist!");
                } else if (statusCode == HttpStatus.FORBIDDEN.value() || statusCode == HttpStatus.UNAUTHORIZED.value()) {
                    model.addAttribute("errorMessage", "You don't have permission!!!");
                } else {
                    model.addAttribute("errorMessage", "There is an error.");
                }
            }
            return "/result";
        }
}
